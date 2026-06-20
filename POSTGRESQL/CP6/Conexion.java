import java.sql.*;

public class Conexion {
    private Connection con;

    public Conexion(String user, String pass) throws Exception {
        Class.forName("org.postgresql.Driver");//carga el driver de PostgreSQL
        String url = "jdbc:postgresql://localhost/empresa";//url de la base de datos
        con = DriverManager.getConnection(url, user, pass);//conexion a la base de datos
    }

    public Connection getConexion() {
        return con;
    }

    public void cerrarConexion() throws SQLException {
        if (con != null) con.close();
    }
}
