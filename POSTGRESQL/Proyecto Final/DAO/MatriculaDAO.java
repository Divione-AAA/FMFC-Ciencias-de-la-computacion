package DAO;

import Database.ConnectionDB;
import Modelos.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    // INSERTAR
    public void insertar(Matricula matricula)
            throws SQLException {

        String sql = """
            INSERT INTO matricula(
                ci_alumno,
                periodo_academico,
                fecha_matricula,
                condicion_academica,
                grado,
                regimen,
                sesion,
                especialidad,
                codigo_grupo
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    matricula.getCiAlumno()
            );

            stmt.setString(
                    2,
                    matricula.getPeriodoAcademico()
            );

            stmt.setDate(
                    3,
                    new java.sql.Date(
                            matricula.getFechaMatricula()
                                    .getTime()
                    )
            );

            stmt.setString(
                    4,
                    matricula.getCondicionAcademica()
            );

            stmt.setString(
                    5,
                    matricula.getGrado()
            );

            stmt.setString(
                    6,
                    matricula.getRegimen()
            );

            stmt.setString(
                    7,
                    matricula.getSesion()
            );

            stmt.setString(
                    8,
                    matricula.getEspecialidad()
            );

            stmt.setInt(
                    9,
                    matricula.getCodigoGrupo()
            );

            stmt.executeUpdate();
        }
    }


    // OBTENER TODAS
    public List<Matricula> obtenerTodas()
            throws SQLException {

        List<Matricula> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM matricula";

        try (
                Connection conn =
                        ConnectionDB.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                lista.add(
                        construirMatricula(rs)
                );
            }
        }

        return lista;
    }


    // OBTENER POR ALUMNO
    public List<Matricula> obtenerPorAlumno(
            String ci
    ) throws SQLException {

        List<Matricula> lista =
                new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM matricula
                WHERE ci_alumno=?
                """;

        try (
                Connection conn =
                        ConnectionDB.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, ci);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                lista.add(
                        construirMatricula(rs)
                );
            }
        }

        return lista;
    }


    // ACTUALIZAR
    public void actualizar(
            Matricula matricula
    ) throws SQLException {

        String sql =
                """
                UPDATE matricula
                SET
                periodo_academico=?,
                fecha_matricula=?,
                condicion_academica=?,
                grado=?,
                regimen=?,
                sesion=?,
                especialidad=?,
                codigo_grupo=?
                WHERE id=?
                """;

        try (
                Connection conn =
                        ConnectionDB.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    matricula.getPeriodoAcademico()
            );

            stmt.setDate(
                    2,
                    new java.sql.Date(
                            matricula
                                    .getFechaMatricula()
                                    .getTime()
                    )
            );

            stmt.setString(
                    3,
                    matricula.getCondicionAcademica()
            );

            stmt.setString(
                    4,
                    matricula.getGrado()
            );

            stmt.setString(
                    5,
                    matricula.getRegimen()
            );

            stmt.setString(
                    6,
                    matricula.getSesion()
            );

            stmt.setString(
                    7,
                    matricula.getEspecialidad()
            );

            stmt.setInt(
                    8,
                    matricula.getCodigoGrupo()
            );

            stmt.setInt(
                    9,
                    matricula.getId()
            );

            stmt.executeUpdate();
        }
    }


    // ELIMINAR
    public void eliminar(
            int id
    ) throws SQLException {

        String sql =
                """
                DELETE FROM matricula
                WHERE id=?
                """;

        try (
                Connection conn =
                        ConnectionDB.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    id
            );

            stmt.executeUpdate();
        }
    }


    // CONSTRUCTOR INTERNO
    private Matricula construirMatricula(
            ResultSet rs
    ) throws SQLException {

        return new Matricula(

                rs.getInt(
                        "id"
                ),

                rs.getString(
                        "ci_alumno"
                ),

                rs.getString(
                        "periodo_academico"
                ),

                rs.getDate(
                        "fecha_matricula"
                ),

                rs.getString(
                        "condicion_academica"
                ),

                rs.getString(
                        "grado"
                ),

                rs.getString(
                        "regimen"
                ),

                rs.getString(
                        "sesion"
                ),

                rs.getString(
                        "especialidad"
                ),

                rs.getInt(
                        "codigo_grupo"
                )
        );
    }

}