package DAO;

import Database.ConnectionDB;
import Modelos.Alumno;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlumnoDAO {

    public void insertar(Alumno alumno) throws SQLException {

        String sql = """
            INSERT INTO alumno(
                ci,
                nombre1,
                nombre2,
                apellido1,
                apellido2,
                fecha_nacimiento,
                sexo,
                color_piel,
                municipio,
                consejo_popular,
                grado,
                regimen,
                sesion,
                estado_alumno,
                especialidad,
                procedencia_social_padre,
                procedencia_social_madre,
                direccion,
                telefono,
                codigo_grupo
            )
            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        """;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, alumno.getCi());
            stmt.setString(2, alumno.getNombre1());
            stmt.setString(3, alumno.getNombre2());

            stmt.setString(4, alumno.getApellido1());
            stmt.setString(5, alumno.getApellido2());

            stmt.setDate(
                    6,
                    new java.sql.Date(
                            alumno.getFechaNacimiento().getTime()
                    )
            );

            stmt.setString(7, alumno.getSexo());

            stmt.setString(8, alumno.getColorPiel());

            stmt.setString(9, alumno.getMunicipio());

            stmt.setString(10, alumno.getConsejoPopular());

            stmt.setString(11, alumno.getGrado());

            stmt.setString(12, alumno.getRegimen());

            stmt.setString(13, alumno.getSesion());

            stmt.setString(14, alumno.getEstadoAlumno());

            stmt.setString(15, alumno.getEspecialidad());

            stmt.setString(16, alumno.getProcedenciaSocialPadre());

            stmt.setString(17, alumno.getProcedenciaSocialMadre());

            stmt.setString(18, alumno.getDireccion());

            stmt.setString(19, alumno.getTelefono());

            stmt.setInt(20, alumno.getCodigoGrupo());

            stmt.executeUpdate();

        }

    }

    public Alumno buscarPorCI(String ci)
            throws SQLException {

        String sql =
                "SELECT * FROM alumno WHERE ci=?";

        try (
                Connection conn =
                        ConnectionDB.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(1, ci);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                return construirAlumno(rs);

            }

        }

        return null;

    }

    public List<Alumno> obtenerTodos()
            throws SQLException {

        List<Alumno> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM alumno";

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
                        construirAlumno(rs)
                );

            }

        }

        return lista;

    }

    public boolean eliminar(
            String ci
    ) throws SQLException {

        String sql =
                "DELETE FROM alumno WHERE ci=?";

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

    private Alumno construirAlumno(
            ResultSet rs
    ) throws SQLException {

        return new Alumno(

                rs.getString("ci"),

                rs.getString("nombre1"),

                rs.getString("nombre2"),

                rs.getString("apellido1"),

                rs.getString("apellido2"),

                new Date(
                        rs.getDate(
                                "fecha_nacimiento"
                        ).getTime()
                ),

                rs.getString("sexo"),

                rs.getString("color_piel"),

                rs.getString("municipio"),

                rs.getString("consejo_popular"),

                rs.getString("grado"),

                rs.getString("regimen"),

                rs.getString("sesion"),

                rs.getString("estado_alumno"),

                rs.getString("especialidad"),

                rs.getString("procedencia_social_padre"),

                rs.getString("procedencia_social_madre"),

                rs.getString("direccion"),

                rs.getString("telefono"),

                rs.getInt("codigo_grupo")

        );

    }

}