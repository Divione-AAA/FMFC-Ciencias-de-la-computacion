package Servicios;

import DAO.AlumnoDAO;
import Modelos.Alumno;

import java.sql.SQLException;
import java.util.List;

public class AlumnoService {

    private final AlumnoDAO alumnoDAO;

    public AlumnoService() {
        alumnoDAO = new AlumnoDAO();
    }

    /*
        REGISTRAR
     */

    public void registrarAlumno(
            Alumno alumno
    ) throws Exception {

        validarAlumno(alumno);

        Alumno existente =
                alumnoDAO.buscarPorCI(
                        alumno.getCi()
                );

        if (existente != null) {

            throw new Exception(
                    "Ya existe un alumno con ese CI."
            );

        }

        alumnoDAO.insertar(
                alumno
        );

    }

    /*
        LISTAR
     */

    public List<Alumno> obtenerTodos()
            throws SQLException {

        return alumnoDAO
                .obtenerTodos();

    }

    /*
        BUSCAR
     */

    public Alumno buscarPorCI(
            String ci
    ) throws SQLException {

        return alumnoDAO
                .buscarPorCI(ci);

    }

    /*
        ELIMINAR
     */

    public void eliminar(
            String ci
    ) throws Exception {

        boolean eliminado =
                alumnoDAO
                        .eliminar(ci);

        if (!eliminado) {

            throw new Exception(
                    "Alumno no encontrado."
            );

        }

    }

    /*
        VALIDACIONES
     */

    private void validarAlumno(
            Alumno alumno
    ) throws Exception {

        if (
                alumno.getCi() == null ||
                        alumno.getCi().isBlank()
        ) {

            throw new Exception(
                    "CI obligatorio."
            );

        }

        if (
                alumno.getNombre1()
                        .isBlank()
        ) {

            throw new Exception(
                    "Nombre obligatorio."
            );

        }

        if (
                alumno. getCodigoGrupo()
                        <= 0
        ) {

            throw new Exception(
                    "Debe pertenecer a un grupo."
            );

        }

    }

}