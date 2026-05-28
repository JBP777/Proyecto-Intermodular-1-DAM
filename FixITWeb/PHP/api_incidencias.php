<?php
// Respuestas JSON para las llamadas fetch del frontend.
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json; charset=UTF-8");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    exit(0);
}

// Datos de conexion a PostgreSQL.
$host     = 'localhost';
$dbname   = 'incidencias';
$port     = '7777';
$username = 'postgres';
$password = '12345';

try {
    $conexion = new PDO(
"pgsql:host=$host;port=$port;dbname=$dbname", $username, $password
    );
    $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $conexion->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Error de conexión: " . $e->getMessage()]);
    exit;
}

$accion = isset($_GET['accion']) ? $_GET['accion'] : '';

switch ($accion) {

// Devuelve los tres colaboradores con mas incidencias resueltas.
case 'ranking':
    try {
        $query = "
           SELECT * FROM vista_top_colaboradores LIMIT 3;
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

    // Devuelve los totales que se muestran en las tarjetas del ranking.
    case 'stats':
        try {
            $stmtTotal = $conexion->prepare("SELECT COUNT(*) AS total FROM INCIDENCIA WHERE estado = 'Abierta'");
            $stmtTotal->execute();
            $totalIncidencias = $stmtTotal->fetch()['total'];

            $stmtColab = $conexion->prepare("SELECT COUNT(*) AS total FROM COLABORADOR");
            $stmtColab->execute();
            $totalColaboradores = $stmtColab->fetch()['total'];

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

    // Cualquier otra accion se considera una peticion incorrecta.
    default:
        http_response_code(400);
        echo json_encode(["error" => "Acción no válida. Usa ?accion=ranking o ?accion=stats"]);
        break;
}
