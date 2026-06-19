import java.sql.*;

public class Manipuladora {

    private Conexion c;
    private PreparedStatement stmt;
    private ResultSet rs;

    public Manipuladora(Conexion c) {
        this.c = c;
    }

    public Trabajador buscar(String id_trabajador) throws SQLException {

        stmt = c.getConexion().prepareStatement("select * from ObtNombre(?)");
        stmt.setString(1, id_trabajador);
        rs = stmt.executeQuery();
        rs.next();
        String n = rs.getString(1);
        String o = rs.getString(2);
        Trabajador t = new Trabajador(id, n, 0, o, "");
        return t;
    }
    public void cerrar() throws SQLException{
        c.cerrarConexion();
        if (stmt!=null)
            stmt.close();
        if (rs!=null)
            rs.close();
    }
}
