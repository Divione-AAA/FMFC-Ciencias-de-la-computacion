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
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static String obtenerRol(Connection conn, String usuario, String contrasena) throws SQLException {
        String sql = "SELECT rol FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuario);
        ps.setString(2, contrasena);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("rol");
        } else {
            return null; // o lanzar excepción si prefieres forzar manejo de error
        }
    }
}
