import java.sql.*;

public class Conexion {
    private Connection con;

    public Conexion(String user, String pass) throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost/empresa";
        con = DriverManager.getConnection(url, user, pass);
    }

    public Connection getConexion() {
        return con;
    }

    public void cerrarConexion() throws SQLException {
        if (con != null) con.close();
    }
}
