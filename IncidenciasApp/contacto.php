<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
header("Content-Type: application/json; charset=UTF-8");

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    exit(0);
}

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(["error" => "Método no permitido"]);
    exit;
}

// Configuración BD
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
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Error de conexión: " . $e->getMessage()]);
    exit;
}

// Recoger y limpiar los datos del formulario
$nombre  = isset($_POST['nombre'])  ? htmlspecialchars(strip_tags(trim($_POST['nombre'])))  : '';
$email   = isset($_POST['email'])   ? htmlspecialchars(strip_tags(trim($_POST['email'])))   : '';
$asunto  = isset($_POST['asunto'])  ? htmlspecialchars(strip_tags(trim($_POST['asunto'])))  : '';
$mensaje = isset($_POST['mensaje']) ? htmlspecialchars(strip_tags(trim($_POST['mensaje']))) : '';

// Validar que no estén vacíos
if (empty($nombre) || empty($email) || empty($asunto) || empty($mensaje)) {
    http_response_code(400);
    echo json_encode(["error" => "Todos los campos son obligatorios"]);
    exit;
}

// Validar formato de email
if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    http_response_code(400);
    echo json_encode(["error" => "El email no tiene un formato válido"]);
    exit;
}

try {
    $query = "INSERT INTO contacto (nombre, email, asunto, mensaje) VALUES (:nombre, :email, :asunto, :mensaje)";
    $stmt  = $conexion->prepare($query);
    $stmt->bindParam(':nombre',  $nombre);
    $stmt->bindParam(':email',   $email);
    $stmt->bindParam(':asunto',  $asunto);
    $stmt->bindParam(':mensaje', $mensaje);
    $stmt->execute();

    http_response_code(201);
    echo json_encode(["ok" => true, "mensaje" => "Mensaje guardado correctamente"]);

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Error al guardar: " . $e->getMessage()]);
}
