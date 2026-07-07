package DAO;

import Database.ConnectionDB;
import Modelos.Grupo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    //====================================================
    // INSERTAR
    //====================================================

    public void insertar(Grupo grupo) throws SQLException {

        String sql = """
                INSERT INTO grupo
                (
                    nombre,
                    codigo_escuela
                )
                VALUES (?,?)
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, grupo.getNombre());
            stmt.setInt(2, grupo.getEscuelaId());

            stmt.executeUpdate();

        }

    }

    //====================================================
    // BUSCAR
    //====================================================

    public Grupo obtenerPorId(int codigoGrupo)
            throws SQLException {

        String sql = """
                SELECT *
                FROM grupo
                WHERE codigo_grupo = ?
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigoGrupo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return construirGrupo(rs);
            }

        }

        return null;

    }

    //====================================================
    // LISTAR
    //====================================================

    public List<Grupo> obtenerTodos()
            throws SQLException {

        List<Grupo> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM grupo
                ORDER BY codigo_grupo
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                lista.add(
                        construirGrupo(rs)
                );

            }

        }

        return lista;

    }

    //====================================================
    // LISTAR POR ESCUELA
    //====================================================

    public List<Grupo> obtenerPorEscuela(int codigoEscuela)
            throws SQLException {

        List<Grupo> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM grupo
                WHERE codigo_escuela = ?
                ORDER BY codigo_grupo
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigoEscuela);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                lista.add(
                        construirGrupo(rs)
                );

            }

        }

        return lista;

    }

    //====================================================
    // ACTUALIZAR
    //====================================================

    public boolean actualizar(Grupo grupo)
            throws SQLException {

        String sql = """
                UPDATE grupo
                SET
                    nombre = ?,
                    codigo_escuela = ?
                WHERE codigo_grupo = ?
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, grupo.getNombre());
            stmt.setInt(2, grupo.getEscuelaId());
            stmt.setInt(3, grupo.getIdGrupo());

            return stmt.executeUpdate() > 0;

        }

    }

    //====================================================
    // ELIMINAR
    //====================================================

    public boolean eliminar(int codigoGrupo)
            throws SQLException {

        String sql = """
                DELETE FROM grupo
                WHERE codigo_grupo = ?
                """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigoGrupo);

            return stmt.executeUpdate() > 0;

        }

    }

    //====================================================
    // MAPPER
    //====================================================

    private Grupo construirGrupo(ResultSet rs)
            throws SQLException {

        return new Grupo(

                rs.getInt("codigo_grupo"),

                String.valueOf(
                        rs.getInt("codigo_grupo")
                ),

                rs.getString("nombre"),

                rs.getInt("codigo_escuela")

        );

    }

}