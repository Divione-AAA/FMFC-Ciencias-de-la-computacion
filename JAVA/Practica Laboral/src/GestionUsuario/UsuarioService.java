package GestionUsuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    public static ArrayList<String> obtenerTodos(Connection conn) {
        ArrayList<String> listaUsuarios = new ArrayList<>();
        String sql = "SELECT nombre_usuario FROM usuarios";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaUsuarios.add(rs.getString("nombre_usuario"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }

        return listaUsuarios;
    }

    public static boolean crearUsuario(Connection conn, String nombre, String contrasena, String rol) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, rol) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);
            stmt.setString(3, rol);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
            return false;
        }
    }

    public static List<String> obtenerNombresUsuarios(Connection conn) {
        List<String> usuarios = new ArrayList<>();
        String sql = "SELECT nombre_usuario FROM usuarios";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(rs.getString("nombre_usuario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public static String obtenerRolUsuario(Connection conn, String nombreUsuario) {
        String rol = "";
        String sql = "SELECT rol FROM usuarios WHERE nombre_usuario = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rol = rs.getString("rol");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rol;
    }

    public static boolean actualizarNombreUsuario(Connection conn, String usuarioActual, String nuevoNombre) {
        String sql = "UPDATE usuarios SET nombre_usuario = ? WHERE nombre_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoNombre);
            ps.setString(2, usuarioActual);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarContrasena(Connection conn, String nombreUsuario, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE nombre_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevaContrasena);
            ps.setString(2, nombreUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarUsuario(Connection conn, String nombreUsuario) {
        String sql = "DELETE FROM usuarios WHERE nombre_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
