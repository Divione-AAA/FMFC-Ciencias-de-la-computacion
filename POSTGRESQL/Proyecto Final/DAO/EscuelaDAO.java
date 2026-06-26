package DAO;

import Database.ConnectionDB;
import Modelos.Escuela;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscuelaDAO {

    /*
        INSERTAR
     */
    public void insertar(Escuela escuela) throws SQLException {

        String sql = """
            INSERT INTO escuela
            (
                codigo_escuela,
                nombre,
                direccion,
                telefono,
                tipo
            )
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, escuela.getCodigoEscuela());
            stmt.setString(2, escuela.getNombre());
            stmt.setString(3, escuela.getDireccion());
            stmt.setString(4, escuela.getTelefono());
            stmt.setString(5, escuela.getTipo());
            stmt.executeUpdate();
        }
    }

    /*
        OBTENER POR ID
     */
    public Escuela obtenerPorCodigo(int codigo) throws SQLException {

        String sql = "SELECT * FROM escuela WHERE codigo_escuela=?";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Escuela(
                        rs.getInt("codigo_escuela"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("tipo")
                );
            }
        }
        return null;
    }

    /*
        LISTAR
     */
    public List<Escuela> obtenerTodas() throws SQLException {

        List<Escuela> lista = new ArrayList<>();

        String sql = "SELECT * FROM escuela ORDER BY nombre";

        try(Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                lista.add(
                        new Escuela(
                                rs.getInt("codigo_escuela"),
                                rs.getString("nombre"),
                                rs.getString("direccion"),
                                rs.getString("telefono"),
                                rs.getString("tipo")
                        )
                );
            }
        }
        return lista;
    }

    /*
        ACTUALIZAR
     */
    public boolean actualizar(Escuela escuela) throws SQLException {

        String sql = """
                UPDATE escuela
                SET
                    nombre=?,
                    direccion=?,
                    telefono=?,
                    tipo=?
                WHERE codigo_escuela=?
                """;

        try(Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, escuela.getNombre());
            stmt.setString(2, escuela.getDireccion());
            stmt.setString(3, escuela.getTelefono());
            stmt.setString(4, escuela.getTipo());
            stmt.setInt(5, escuela.getCodigoEscuela());

            return stmt.executeUpdate() > 0;
        }
    }

    /*
        ELIMINAR
     */
    public boolean eliminar(int codigo) throws SQLException {

        String sql = "DELETE FROM escuela WHERE codigo_escuela=?";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, codigo);
            return stmt.executeUpdate() > 0;
        }
    }
}