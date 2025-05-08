package GestionUsuario;
import java.sql.*;

public class Crearuser {
    public static void crearUsuario(Connection conn, String nombreUsuario, String contrasena, String rol) throws SQLException {
        // Verifica si el nombre de usuario ya existe
        String checkExistenceSQL = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkExistenceSQL)) {
            checkStmt.setString(1, nombreUsuario);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.getInt(1) > 0) {
                // Si ya existe, muestra un mensaje de error o realiza alguna acción
                System.out.println("El nombre de usuario ya está registrado.");
                return;  // Detener el proceso
            }
        }

        // Si no existe, inserta el nuevo usuario
        String insertSQL = "INSERT INTO usuarios (nombre_usuario, contrasena, rol) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
            insertStmt.setString(1, nombreUsuario);
            insertStmt.setString(2, contrasena);
            insertStmt.setString(3, rol);
            insertStmt.executeUpdate();
            System.out.println("Usuario creado exitosamente.");
        }
    }
}
