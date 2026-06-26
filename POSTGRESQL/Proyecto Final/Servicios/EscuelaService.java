package Servicios;

import DAO.EscuelaDAO;
import Modelos.Escuela;

import java.sql.SQLException;
import java.util.List;

public class EscuelaService {

    private final EscuelaDAO escuelaDAO;

    public EscuelaService() {
        this.escuelaDAO = new EscuelaDAO();
    }

    // Registrar escuela
    public void registrarEscuela(Escuela escuela) throws SQLException {

        if (escuela.getNombre().isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de la escuela es obligatorio."
            );
        }

        if (escuela.getDireccion().isBlank()) {
            throw new IllegalArgumentException(
                    "La dirección es obligatoria."
            );
        }

        escuelaDAO.insertar(escuela);
    }

    // Obtener todas
    public List<Escuela> obtenerEscuelas() throws SQLException {
        return escuelaDAO.obtenerTodas();
    }

    // Buscar por código
    public Escuela buscarEscuela(int codigo)
            throws SQLException {

        return escuelaDAO.obtenerPorCodigo(codigo);
    }

    // Eliminar
    public void eliminarEscuela(int codigo)
            throws SQLException {

        escuelaDAO.eliminar(codigo);
    }

    // Actualizar
    public void actualizarEscuela(Escuela escuela)
            throws SQLException {

        escuelaDAO.actualizar(escuela);
    }
}
