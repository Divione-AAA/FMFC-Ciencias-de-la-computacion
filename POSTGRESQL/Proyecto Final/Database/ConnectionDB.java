package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/edja_camaguey";
    private static final String USER = "postgres";
    private static final String PASSWORD = "bloodlust";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL no encontrado.");
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}