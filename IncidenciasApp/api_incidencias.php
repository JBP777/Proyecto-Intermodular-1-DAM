<?php
// Cabeceras CORS y JSON
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json; charset=UTF-8");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    exit(0);
}

// Configuración de la base de datos — cámbiala por tus datos de XAMPP
$host     = 'localhost';
$dbname   = 'incidencias';   // <-- nombre de tu base de datos
$username = 'root';
$password = '';              // <-- tu contraseña (vacía por defecto en XAMPP)

try {
    $conexion = new PDO(
        "mysql:host=$host;dbname=$dbname;charset=utf8",
        $username,
        $password
    );
    $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $conexion->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Error de conexión: " . $e->getMessage()]);
    exit;
}

// Leer el parámetro "accion" de la URL: ?accion=ranking o ?accion=stats
$accion = isset($_GET['accion']) ? $_GET['accion'] : '';

switch ($accion) {

    // -------------------------------------------------------------------
    // RANKING: colaboradores ordenados por total de incidencias resueltas
    // -------------------------------------------------------------------
case 'ranking':
    try {
        $query = "
            SELECT
                u.nombre_usuario  AS usuario,
                u.email,
                c.total_resueltas AS incidenciasresueltas,
                c.valoracion_media
            FROM colaborador c
            JOIN usuario u ON c.usuario = u.nombre_usuario
            ORDER BY c.total_resueltas DESC
            LIMIT 3
        ";
        $stmt = $conexion->prepare($query);
        $stmt->execute();
        $ranking = $stmt->fetchAll();
        http_response_code(200);
        echo json_encode($ranking);
    } catch (PDOException $e) {
        http_response_code(500);
        echo json_encode(["error" => "Error al obtener el ranking: " . $e->getMessage()]);
    }
    break;

    // -------------------------------------------------------------------
    // STATS: totales para las tarjetas de estadísticas
    // -------------------------------------------------------------------
    case 'stats':
        try {
            // Total de incidencias
            $stmtTotal = $conexion->prepare("SELECT COUNT(*) AS total FROM INCIDENCIA WHERE estado = 'Abierta'");
            $stmtTotal->execute();
            $totalIncidencias = $stmtTotal->fetch()['total'];

            // Total de colaboradores activos
            $stmtColab = $conexion->prepare("SELECT COUNT(*) AS total FROM COLABORADOR");
            $stmtColab->execute();
            $totalColaboradores = $stmtColab->fetch()['total'];

            // Incidencias cerradas (estado = 'Cerrada')
            $stmtCerradas = $conexion->prepare("SELECT COUNT(*) AS total FROM INCIDENCIA WHERE estado = 'Cerrada'");
            $stmtCerradas->execute();
            $incidenciasCerradas = $stmtCerradas->fetch()['total'];

            http_response_code(200);
            echo json_encode([
                "totalincidencias"    => (int)$totalIncidencias,
                "totalcolaboradores"  => (int)$totalColaboradores,
                "incidenciascerradas" => (int)$incidenciasCerradas
            ]);
        } catch (PDOException $e) {
            http_response_code(500);
            echo json_encode(["error" => "Error al obtener estadísticas: " . $e->getMessage()]);
        }
        break;

    // -------------------------------------------------------------------
    // Acción no reconocida
    // -------------------------------------------------------------------
    default:
        http_response_code(400);
        echo json_encode(["error" => "Acción no válida. Usa ?accion=ranking o ?accion=stats"]);
        break;
}