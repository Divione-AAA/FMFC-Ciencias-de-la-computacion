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
                    id_profesor,
                    id_grupo,
                    id_asignatura
                )
                VALUES
                (
                    ?,
                    ?,
                    ?
                )
                """;

        try (Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql)){
            stmt.setInt(1,imparte.getIdProfesor());
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

    public List<Imparte> obtenerPorProfesor(int idProfesor) throws SQLException {

        List<Imparte> lista =new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM imparte
                WHERE id_profesor=?
                """;

        try (Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql)){

            stmt.setInt(1,idProfesor);

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
                WHERE id_grupo=?
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
                WHERE id_imparte=?
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
                p.nombres,
                p.apellidos,
                a.nombre AS asignatura,
                g.grupo
                FROM imparte i
                JOIN profesor p
                ON i.id_profesor=p.id_profesor
                JOIN asignatura a
                ON i.id_asignatura=a.id_asignatura
                JOIN grupo g
                ON i.id_grupo=g.id_grupo
                """;

        try (Connection conn =ConnectionDB.getConnection();PreparedStatement stmt =conn.prepareStatement(sql);ResultSet rs =stmt.executeQuery()
        ){

            while (rs.next()) {
                lista.add(
                        rs.getString("nombres")
                                + " "
                                + rs.getString("apellidos")
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
                rs.getInt("id_imparte"),
                rs.getInt("id_profesor"),
                rs.getInt("id_grupo"),
                rs.getInt("id_asignatura")
        );
    }
}