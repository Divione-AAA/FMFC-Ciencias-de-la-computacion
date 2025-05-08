package GestionUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static boolean login(Connection conn, String usuario, String contrasena) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuario);
        ps.setString(2, contrasena); // si está cifrada, deberías cifrar la entrada también
        ResultSet rs = ps.executeQuery();
        return rs.next(); // devuelve true si hay coincidencia
    }
}
