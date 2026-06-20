import java.sql.*;

public class Manipuladora {

    private Conexion c;
    private CallableStatement stmt;

    public Manipuladora(Conexion c) {
        this.c = c;
    }

    public Trabajador buscar(String id_trabajador) throws SQLException {
        stmt = c.getConexion().prepareCall("{ call ObtNombre(?, ?) }");//llama al procedimiento almacenado
        stmt.setString(1, id_trabajador);//pasa el valor de id_trabajador
        stmt.registerOutParameter(2, Types.VARCHAR);//registra el valor de nombre
        stmt.execute();//ejecuta el procedimiento

        String nombre = stmt.getString(2);//recupera el valor de nombre
        return new Trabajador(id_trabajador, nombre, 0, "", "");//crea un objeto de tipo Trabajador con los valores recuperados
    }
    //cerrar el objeto de tipo conexion
    public void cerrar() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        c.cerrarConexion();
    }
}
