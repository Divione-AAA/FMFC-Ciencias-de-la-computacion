package Servicios;

import DAO.ProfesorDAO;
import Modelos.Profesor;

import java.sql.SQLException;
import java.util.List;

public class ProfesorService {

    private final ProfesorDAO profesorDAO;

    public ProfesorService() {

        profesorDAO =
                new ProfesorDAO();

    }

    /*
        REGISTRAR
     */

    public void registrar(
            Profesor profesor
    ) throws Exception {

        validarProfesor(
                profesor
        );

        profesorDAO
                .insertar(
                        profesor
                );

    }

    /*
        LISTAR
     */

    public List<String> obtenerTodos()
            throws SQLException {

        return profesorDAO
                .obtenerTodos();
    }

    /*
        ELIMINAR
     */

    public void eliminar(
            String ci
    ) throws Exception {

        boolean eliminado =
                profesorDAO
                        .eliminar(ci);

        if (!eliminado) {

            throw new Exception(
                    "Profesor no encontrado."
            );

        }

    }

    /*
        VALIDACIONES
     */

    private void validarProfesor(
            Profesor profesor
    ) throws Exception {

        if (
                profesor.getCi() == null ||
                        profesor.getCi().isBlank()
        ) {

            throw new Exception(
                    "CI obligatorio."
            );

        }

        if (
                profesor.getNombre1()
                        .isBlank()
        ) {

            throw new Exception(
                    "Nombre obligatorio."
            );

        }

        if (
                profesor.getCodigoEscuela()
                        <= 0
        ) {

            throw new Exception(
                    "Debe asociarse a una escuela."
            );

        }

    }

}