package Servicios;

import DAO.AsignaturaDAO;
import Modelos.Asignatura;

import java.sql.SQLException;
import java.util.List;

public class AsignaturaService {

    private final AsignaturaDAO dao;

    public AsignaturaService() {
        dao = new AsignaturaDAO();
    }

    /*
     * REGISTRAR
     */
    public void registrar(Asignatura asignatura) throws SQLException {

        if (asignatura == null) {
            throw new IllegalArgumentException("La asignatura no puede ser nula.");
        }

        if (asignatura.getNombre() == null ||
                asignatura.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de la asignatura es obligatorio."
            );
        }

        dao.insertar(asignatura);
    }

    /*
     * LISTAR
     */
    public List<Asignatura> obtenerTodas() throws SQLException {
        return dao.obtenerTodas();
    }

    /*
     * BUSCAR
     */
    public Asignatura obtenerPorCodigo(int codigoAsignatura)
            throws SQLException {

        return dao.obtenerPorCodigo(codigoAsignatura);
    }

    /*
     * ELIMINAR
     */
    public void eliminar(int codigoAsignatura)
            throws SQLException {

        boolean eliminado = dao.eliminar(codigoAsignatura);

        if (!eliminado) {
            throw new IllegalArgumentException(
                    "La asignatura no existe."
            );
        }
    }

    /*
     * ACTUALIZAR
     */
    public void actualizar(Asignatura asignatura)
            throws SQLException {

        if (asignatura == null) {
            throw new IllegalArgumentException("La asignatura no puede ser nula.");
        }

        if (asignatura.getNombre() == null ||
                asignatura.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre de la asignatura es obligatorio."
            );
        }

        boolean actualizado = dao.actualizar(asignatura);

        if (!actualizado) {
            throw new IllegalArgumentException(
                    "La asignatura no existe."
            );
        }
    }
    /*
     * BUSCAR POR CÓDIGO
     */
    public Asignatura buscarPorCodigo(int codigoAsignatura)
            throws SQLException {

        return dao.obtenerPorCodigo(codigoAsignatura);

    }
}