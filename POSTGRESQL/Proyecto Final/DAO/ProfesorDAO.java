package DAO;

import Database.ConnectionDB;
import Modelos.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {

    // ===========================
    // INSERTAR
    // ===========================

    public void insertar(Profesor profesor) throws SQLException {

        String sql = """
            INSERT INTO profesor(
                ci,
                nombre1ro,
                nombre2do,
                apellido1ro,
                apellido2do,
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

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    // ===========================
    // ELIMINAR
    // ===========================

    public boolean eliminar(String ci) throws SQLException {

        String sql = "DELETE FROM profesor WHERE ci=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ci);

            return stmt.executeUpdate() > 0;
        }
    }

    // ===========================
    // OBTENER TODOS
    // ===========================

    public List<Profesor> obtenerTodos() throws SQLException {

        List<Profesor> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM profesor
                ORDER BY apellido1ro, nombre1ro
                """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                lista.add(construirProfesor(rs));

            }
        }

        return lista;
    }

    // ===========================
    // BUSCAR POR CI
    // ===========================

    public Profesor obtenerPorCI(String ci) throws SQLException {

        String sql = """
                SELECT *
                FROM profesor
                WHERE ci = ?
                """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ci);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return construirProfesor(rs);
            }

        }

        return null;
    }

    // ===========================
    // CONSTRUIR OBJETO
    // ===========================

    private Profesor construirProfesor(ResultSet rs) throws SQLException {

        return new Profesor(

                rs.getString("ci"),

                rs.getString("nombre1ro"),

                rs.getString("nombre2do"),

                rs.getString("apellido1ro"),

                rs.getString("apellido2do"),

                rs.getInt("edad"),

                rs.getString("sexo"),

                rs.getString("color_piel"),

                rs.getString("direccion_particular"),

                rs.getString("telefono_particular"),

                rs.getString("municipio"),

                rs.getString("integracion_politica"),

                rs.getString("centro_graduacion"),

                rs.getInt("anio_graduacion"),

                rs.getInt("anio_inicio_trabajo"),

                rs.getString("procedencia"),

                rs.getString("categoria_docente"),

                rs.getString("categoria_cientifica"),

                rs.getBoolean("mision_internacionalista"),

                rs.getBoolean("es_cuadro"),

                rs.getString("cargo"),

                rs.getString("nivel_ensenanza"),

                rs.getBoolean("se_supera"),

                rs.getString("ultima_evaluacion_profesional")
        );
    }

}