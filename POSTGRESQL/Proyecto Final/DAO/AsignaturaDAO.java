package DAO;

import Database.ConnectionDB;
import Modelos.Asignatura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO {

    // INSERTAR
    public void insertar(Asignatura asignatura) throws SQLException {

        String sql = """
                INSERT INTO asignatura(nombre)
                VALUES(?)
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, asignatura.getNombre());

            stmt.executeUpdate();
        }
    }

    /*
     * BUSCAR POR CÓDIGO
     */
    public Asignatura obtenerPorCodigo(int codigoAsignatura)
            throws SQLException {

        String sql = """
            SELECT *
            FROM asignatura
            WHERE codigo_asignatura = ?
            """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigoAsignatura);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return construir(rs);
            }

        }

        return null;
    }

    // LISTAR
    public List<Asignatura> obtenerTodas() throws SQLException {

        List<Asignatura> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM asignatura
                ORDER BY nombre
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(construir(rs));
            }

        }

        return lista;
    }

    // ACTUALIZAR
    public boolean actualizar(Asignatura asignatura) throws SQLException {

        String sql = """
                UPDATE asignatura
                SET nombre = ?
                WHERE codigo_asignatura = ?
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, asignatura.getNombre());
            stmt.setInt(2, asignatura.getCodigoAsignatura());

            return stmt.executeUpdate() > 0;
        }
    }

    // ELIMINAR
    public boolean eliminar(int codigo) throws SQLException {

        String sql = """
                DELETE
                FROM asignatura
                WHERE codigo_asignatura = ?
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigo);

            return stmt.executeUpdate() > 0;
        }
    }

    // BUSCAR POR NOMBRE
    public List<Asignatura> buscar(String texto) throws SQLException {

        List<Asignatura> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM asignatura
                WHERE LOWER(nombre) LIKE LOWER(?)
                ORDER BY nombre
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, "%" + texto + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(construir(rs));
            }

        }

        return lista;
    }

    // MAPPER
    private Asignatura construir(ResultSet rs) throws SQLException {

        return new Asignatura(
                rs.getInt("codigo_asignatura"),
                rs.getString("nombre")
        );
    }



}