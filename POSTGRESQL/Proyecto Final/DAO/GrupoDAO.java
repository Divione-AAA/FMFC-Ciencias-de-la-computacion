package DAO;

import Database.ConnectionDB;
import Modelos.Grupo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

        //INSERTAR
    public void insertar(
            Grupo grupo
    ) throws SQLException {

        String sql = """
            INSERT INTO grupo
            (
                codigo_grupo,
                nombre,
                escuela_id
            )
            VALUES (?, ?, ?)
        """;

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, grupo.getCodigoGrupo());
            stmt.setString(2, grupo.getNombre());
            stmt.setInt(3, grupo.getEscuelaId());
            stmt.executeUpdate();
        }
    }

        //BUSCAR
    public Grupo obtenerPorId(int id) throws SQLException {

        String sql = "SELECT * FROM grupo WHERE id=?";
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return construirGrupo(rs);
            }
        }
        return null;
    }

        //LISTAR
    public List<Grupo> obtenerTodos() throws SQLException {

        List<Grupo> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupo";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(
                        construirGrupo(rs)
                );
            }
        }

        return lista;

    }

        //LISTAR POR ESCUELA
    public List<Grupo> obtenerPorEscuela(int escuela) throws SQLException {

        List<Grupo> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM grupo
                WHERE escuela_id=?
                """;

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, escuela);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(construirGrupo(rs));
            }
        }
        return lista;
    }

        //ACTUALIZAR
    public boolean actualizar(Grupo grupo) throws SQLException {

        String sql = """
            UPDATE grupo
            SET
                codigo_grupo=?,
                nombre=?,
                escuela_id=?
            WHERE id=?
        """;

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt= conn.prepareStatement(sql)) {

            stmt.setString(1,grupo.getCodigoGrupo());
            stmt.setString(2,grupo.getNombre());
            stmt.setInt(3,grupo.getEscuelaId());
            stmt.setInt(4,grupo.getIdGrupo());

            return stmt.executeUpdate() > 0;
        }
    }

        //ELIMINAR
    public boolean eliminar(int id) throws SQLException {

        String sql = "DELETE FROM grupo WHERE id=?";

        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,id);
            return stmt.executeUpdate() > 0;
        }
    }

        //MAPPER
    private Grupo construirGrupo(ResultSet rs) throws SQLException {

        return new Grupo(
                rs.getInt("id"),
                rs.getString("codigo_grupo"),
                rs.getString("nombre"),
                rs.getInt("escuela_id")
        );
    }
}
