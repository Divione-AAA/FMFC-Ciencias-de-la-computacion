package Servicios;

import DAO.EscuelaDAO;
import DAO.GrupoDAO;
import Modelos.Escuela;
import Modelos.Grupo;

import java.sql.SQLException;
import java.util.List;

public class GrupoService {

    private final GrupoDAO grupoDAO;
    private final EscuelaDAO escuelaDAO;

    public GrupoService() {

        grupoDAO = new GrupoDAO();
        escuelaDAO = new EscuelaDAO();

    }

    /*
        REGISTRAR
     */

    public void registrarGrupo(
            Grupo grupo
    ) throws Exception {

        validarGrupo(grupo);

        Escuela escuela =
                escuelaDAO.obtenerPorCodigo(
                        grupo.getCodigoEscuela()
                );

        if (escuela == null) {

            throw new Exception(
                    "La escuela no existe."
            );

        }

        grupoDAO.insertar(grupo);

    }

    /*
        LISTAR
     */

    public List<Grupo> obtenerGrupos()
            throws SQLException {

        return grupoDAO.obtenerTodos();

    }

    /*
        BUSCAR
     */

    public Grupo buscarGrupo(
            int codigoGrupo
    ) throws SQLException {

        return grupoDAO.obtenerPorId(
                codigoGrupo
        );

    }

    /*
        LISTAR POR ESCUELA
     */

    public List<Grupo> obtenerPorEscuela(
            int codigoEscuela
    ) throws SQLException {

        return grupoDAO.obtenerPorEscuela(
                codigoEscuela
        );

    }

    /*
        ACTUALIZAR
     */

    public void actualizarGrupo(
            Grupo grupo
    ) throws Exception {

        validarGrupo(grupo);

        Escuela escuela =
                escuelaDAO.obtenerPorCodigo(
                        grupo.getCodigoEscuela()
                );

        if (escuela == null) {

            throw new Exception(
                    "La escuela no existe."
            );

        }

        boolean actualizado =
                grupoDAO.actualizar(grupo);

        if (!actualizado) {

            throw new Exception(
                    "Grupo no encontrado."
            );

        }

    }

    /*
        ELIMINAR
     */

    public void eliminarGrupo(
            int codigoGrupo
    ) throws Exception {

        boolean eliminado =
                grupoDAO.eliminar(
                        codigoGrupo
                );

        if (!eliminado) {

            throw new Exception(
                    "Grupo no encontrado."
            );

        }

    }

    /*
        VALIDACIONES
     */

    private void validarGrupo(
            Grupo grupo
    ) throws Exception {

        if (grupo == null) {

            throw new Exception(
                    "El grupo es obligatorio."
            );

        }

        if (
                grupo.getNombre() == null ||
                        grupo.getNombre().isBlank()
        ) {

            throw new Exception(
                    "El nombre del grupo es obligatorio."
            );

        }

        if (
                grupo.getCodigoEscuela() <= 0
        ) {

            throw new Exception(
                    "Debe seleccionar una escuela."
            );

        }

    }

}