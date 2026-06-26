package Servicios;

import DAO.GrupoDAO;
import DAO.EscuelaDAO;

import Modelos.Grupo;
import Modelos.Escuela;

import java.sql.SQLException;
import java.util.List;

public class GrupoService {

    private final GrupoDAO grupoDAO;
    private final EscuelaDAO escuelaDAO;

    public GrupoService() {
        grupoDAO = new GrupoDAO();
        escuelaDAO = new EscuelaDAO();
    }

    // Registrar grupo
    public void registrarGrupo(Grupo grupo)
            throws SQLException {

        Escuela escuela =
                escuelaDAO.obtenerPorCodigo(
                        grupo.getCodigoEscuela()
                );

        if (escuela == null) {
            throw new IllegalArgumentException(
                    "La escuela no existe."
            );
        }

        grupoDAO.insertar(grupo);
    }

    // Obtener todos
    public List<Grupo> obtenerGrupos()
            throws SQLException {

        return grupoDAO.obtenerTodos();
    }

    // Buscar
    public Grupo buscarGrupo(int codigo)
            throws SQLException {

        return grupoDAO.obtenerPorId(codigo);
    }

    // Obtener grupos de escuela
    public List<Grupo> obtenerPorEscuela(
            int codigoEscuela
    ) throws SQLException {

        return grupoDAO.obtenerPorEscuela(
                codigoEscuela
        );
    }

    // Eliminar
    public void eliminarGrupo(
            int codigoGrupo
    ) throws SQLException {

        grupoDAO.eliminar(codigoGrupo);
    }

    // Actualizar
    public void actualizarGrupo(
            Grupo grupo
    ) throws SQLException {

        grupoDAO.actualizar(grupo);
    }
}