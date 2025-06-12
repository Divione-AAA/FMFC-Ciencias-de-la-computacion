package Main;

import java.sql.*;

public class SQLite {

    private static void  crearTablaUsers() throws SQLException {

        String crearUsuarios = """
            CREATE TABLE IF NOT EXISTS usuarios (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre_usuario TEXT UNIQUE NOT NULL,
            contrasena TEXT NOT NULL,
            rol TEXT NOT NULL CHECK (rol IN ('admin', 'invitado'))
        );
    """;
        Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
        Statement stmt = conn.createStatement();
        stmt.execute(crearUsuarios);
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:data.db"; // esto crea el archivo si no existe
        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Conectado a Main.SQLite!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        crearTablaUsers();
    }
}
