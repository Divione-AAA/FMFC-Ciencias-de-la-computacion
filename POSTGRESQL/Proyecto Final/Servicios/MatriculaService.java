package Servicios;

import DAO.MatriculaDAO;
import Modelos.Matricula;

import java.sql.SQLException;
import java.util.List;

public class MatriculaService {

    private final MatriculaDAO matriculaDAO;

    public MatriculaService() {
        matriculaDAO = new MatriculaDAO();
    }

    // Registrar matrícula
    public void registrarMatricula(
            Matricula matricula
    ) throws SQLException {

        validarMatricula(matricula);

        matriculaDAO.insertar(matricula);
    }

    // Obtener todas
    public List<Matricula> obtenerTodas()
            throws SQLException {

        return matriculaDAO.obtenerTodas();
    }

    // Obtener por alumno
    public List<Matricula> obtenerPorAlumno(
            String ci
    ) throws SQLException {

        if (ci == null || ci.isBlank()) {
            throw new IllegalArgumentException(
                    "Debe indicar el CI del alumno."
            );
        }

        return matriculaDAO.obtenerPorAlumno(ci);
    }

    // Eliminar matrícula
    public void eliminar(
            int id
    ) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID inválido."
            );
        }

        matriculaDAO.eliminar(id);
    }

    // Actualizar
    public void actualizar(
            Matricula matricula
    ) throws SQLException {

        validarMatricula(matricula);

        matriculaDAO.actualizar(matricula);
    }

    // Validaciones
    private void validarMatricula(
            Matricula m
    ) {

        if (m == null) {
            throw new IllegalArgumentException(
                    "La matrícula no puede ser nula."
            );
        }

        if (
                m.getCiAlumno() == null ||
                        m.getCiAlumno().isBlank()
        ) {

            throw new IllegalArgumentException(
                    "CI obligatorio."
            );
        }

        if (
                m.getPeriodoAcademico() == null ||
                        m.getPeriodoAcademico().isBlank()
        ) {

            throw new IllegalArgumentException(
                    "Periodo académico obligatorio."
            );
        }

        if (
                m.getGrado() == null ||
                        m.getGrado().isBlank()
        ) {

            throw new IllegalArgumentException(
                    "Grado obligatorio."
            );
        }

        if (
                m.getCodigoGrupo() <= 0
        ) {

            throw new IllegalArgumentException(
                    "Grupo inválido."
            );
        }
    }
}