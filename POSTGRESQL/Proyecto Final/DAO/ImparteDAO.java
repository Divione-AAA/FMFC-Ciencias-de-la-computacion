package DAO;

import Database.ConnectionDB;
import Modelos.Imparte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImparteDAO {

    // INSERTAR
    public void insertar(Imparte imparte) throws SQLException {

        String sql = """
                INSERT INTO imparte
                (
                    id_profesor,
                    id_grupo,
                    codigo_asignatura
                )
                VALUES
                (
                    ?,
                    ?,
                    ?
                )
                """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, imparte.getIdProfesor());
            stmt.setInt(2, imparte.getIdGrupo());
            stmt.setInt(3, imparte.getIdAsignatura());

            stmt.executeUpdate();
        }
    }

    // LISTAR TODO
    public List<Imparte> obtenerTodos() throws SQLException {

        List<Imparte> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM imparte
                ORDER BY id_imparte
                """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(construir(rs));
            }
        }

        return lista;
    }

    // BUSCAR POR PROFESOR
    public List<Imparte> obtenerPorProfesor(String ciProfesor) throws SQLException {

        List<Imparte> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM imparte
                WHERE id_profesor=?
                """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ciProfesor);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(construir(rs));
            }
        }

        return lista;
    }

    // BUSCAR POR GRUPO
    public List<Imparte> obtenerPorGrupo(int grupo) throws SQLException {

        List<Imparte> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM imparte
                WHERE id_grupo=?
                """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, grupo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(construir(rs));
            }
        }

        return lista;
    }

    // ELIMINAR
    public boolean eliminar(int idImparte) throws SQLException {

        String sql = """
                DELETE
                FROM imparte
                WHERE id_imparte=?
                """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idImparte);

            return stmt.executeUpdate() > 0;
        }
    }

    // CONSULTA COMPLETA
    public List<String> obtenerVistaCompleta() throws SQLException {

        List<String> lista = new ArrayList<>();

        String sql = """
                SELECT
                    p.nombre1ro || ' ' || p.apellido1ro AS profesor,
                    a.nombre AS asignatura,
                    g.nombre AS grupo
                FROM imparte i
                INNER JOIN profesor p
                    ON i.id_profesor = p.ci
                INNER JOIN asignatura a
                    ON i.codigo_asignatura = a.codigo_asignatura
                INNER JOIN grupo g
                    ON i.id_grupo = g.codigo_grupo
                ORDER BY profesor
                """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(
                        rs.getString("profesor")
                                + " → "
                                + rs.getString("asignatura")
                                + " → Grupo "
                                + rs.getString("grupo")
                );
            }
        }

        return lista;
    }

    // CONSTRUCTOR
    private Imparte construir(ResultSet rs) throws SQLException {

        return new Imparte(

                rs.getInt("id_imparte"),

                rs.getString("id_profesor"),

                rs.getInt("id_grupo"),

                rs.getInt("codigo_asignatura")
        );
    }
}