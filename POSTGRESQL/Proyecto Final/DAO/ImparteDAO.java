package DAO;

import Database.ConnectionDB;
import Modelos.Imparte;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImparteDAO {

        //INSERTAR

    public void insertar(Imparte imparte) throws SQLException {

        String sql =
                """
                INSERT INTO imparte
                (
                    profesor_id,
                    grupo_id,
                    asignatura_id
                )
                VALUES
                (
                    ?,
                    ?,
                    ?
                )
                """;

        try (Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql)){
            stmt.setString(1,imparte.getIdProfesor());
            stmt.setInt(2,imparte.getIdGrupo());
            stmt.setInt(3,imparte.getIdAsignatura());
            stmt.executeUpdate();
        }
    }

        //LISTAR TODO

    public List<Imparte> obtenerTodos() throws SQLException {

        List<Imparte> lista = new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM imparte
                """;

        try (Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()){

            while (rs.next()) {
                lista.add(construir(rs));
            }
        }
        return lista;
    }

        //BUSCAR PROFESOR

    public List<Imparte> obtenerPorProfesor(String ciProfesor) throws SQLException {

        List<Imparte> lista =new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM imparte
                WHERE profesor_id=?
                """;

        try (Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql)){

            stmt.setString(1,ciProfesor);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(construir(rs));
            }
        }
        return lista;
    }

        //BUSCAR GRUPO

    public List<Imparte> obtenerPorGrupo(
            int grupo
    ) throws SQLException {

        List<Imparte> lista =new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM imparte
                WHERE grupo_id=?
                """;

        try (Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql)){

            stmt.setInt(1,grupo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                lista.add( construir(rs));
            }
        }
        return lista;
    }

        //ELIMINAR

    public boolean eliminar(int id) throws SQLException {

        String sql =
                """
                DELETE
                FROM imparte
                WHERE profesor_id=?
                """;

        try(Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql)){

            stmt.setInt(1,id);
            return stmt.executeUpdate() > 0;
        }
    }

        //CONSULTA COMPLETA

    public List<String> obtenerVistaCompleta()throws SQLException {

        List<String> lista =new ArrayList<>();

        String sql =
                """
                SELECT
                    p.nombre1 || ' ' || p.apellido1 AS profesor,
                    a.nombre AS asignatura,
                    g.codigo_grupo AS grupo
                FROM imparte i
                JOIN profesor p
                    ON i.profesor_id = p.id
                JOIN asignatura a
                    ON i.asignatura_id = a.id
                JOIN grupo g
                    ON i.grupo_id = g.id
                """;

        try (Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()
        ){

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

    private Imparte construir( ResultSet rs) throws SQLException {
        return new Imparte(
                0,
                rs.getString("profesor_id"),
                rs.getInt("grupo_id"),
                rs.getInt("asignatura_id")
        );
    }
}