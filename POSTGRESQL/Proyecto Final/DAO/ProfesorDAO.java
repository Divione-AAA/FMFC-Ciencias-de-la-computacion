package DAO;

import Database.ConnectionDB;
import Modelos.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {

    public void insertar(
            Profesor profesor
    ) throws SQLException {

        String sql = """
            INSERT INTO profesor(
                ci,
                nombre1,
                nombre2,
                apellido1,
                apellido2,
                edad,
                sexo,
                color_piel,
                direccion_particular,
                telefono_particular,
                municipio,
                integracion_politica,
                centro_graduacion,
                anio_graduacion,
                anio_inicio_trabajo,
                procedencia,
                categoria_docente,
                categoria_cientifica,
                mision_internacionalista,
                es_cuadro,
                cargo,
                nivel_ensenanza,
                se_supera,
                ultima_evaluacion_profesional
            )
            VALUES
            (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        """;

        try (
                Connection conn =
                        ConnectionDB.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, profesor.getCi());

            stmt.setString(2, profesor.getNombre1());

            stmt.setString(3, profesor.getNombre2());

            stmt.setString(4, profesor.getApellido1());

            stmt.setString(5, profesor.getApellido2());

            stmt.setInt(6, profesor.getEdad());

            stmt.setString(7, profesor.getSexo());

            stmt.setString(8, profesor.getColorPiel());

            stmt.setString(9, profesor.getDireccionParticular());

            stmt.setString(10, profesor.getTelefonoParticular());

            stmt.setString(11, profesor.getMunicipio());

            stmt.setString(12, profesor.getIntegracionPolitica());

            stmt.setString(13, profesor.getCentroGraduacion());

            stmt.setInt(14, profesor.getAnioGraduacion());

            stmt.setInt(15, profesor.getAnioInicioTrabajo());

            stmt.setString(16, profesor.getProcedencia());

            stmt.setString(17, profesor.getCategoriaDocente());

            stmt.setString(18, profesor.getCategoriaCientifica());

            stmt.setBoolean(19, profesor.isMisionInternacionalista());

            stmt.setBoolean(20, profesor.isEsCuadro());

            stmt.setString(21, profesor.getCargo());

            stmt.setString(22, profesor.getNivelEnsenanza());

            stmt.setBoolean(23, profesor.isSeSupera());

            stmt.setString(24, profesor.getUltimaEvaluacionProfesional());

            stmt.executeUpdate();

        }

    }

    public boolean eliminar(
            String ci
    ) throws SQLException {

        String sql =
                "DELETE FROM profesor WHERE ci=?";

        try (
                Connection conn =
                        ConnectionDB.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, ci);

            return stmt.executeUpdate() > 0;

        }

    }

    public List<String> obtenerTodos()
            throws SQLException {

        List<String> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM profesor";

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
                        rs.getString("nombre1")
                                + " "
                                + rs.getString("apellido1")
                );

            }

        }

        return lista;

    }

}