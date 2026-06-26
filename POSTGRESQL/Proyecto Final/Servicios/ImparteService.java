package Servicios;

import DAO.ImparteDAO;
import Modelos.Imparte;

import java.sql.SQLException;
import java.util.List;

public class ImparteService {

    private final ImparteDAO dao;

    public ImparteService() {
        dao =
                new ImparteDAO();
    }

    public void asignar(
            Imparte i
    ) throws SQLException {

        dao.insertar(i);
    }

    public List<Imparte>
    obtenerTodo()
            throws SQLException {

        return dao.obtenerTodos();
    }

    public void eliminar(
            String profesor,
            int grupo,
            int asignatura
    ) throws SQLException {

        dao.eliminar(
                profesor,
                grupo,
                asignatura
        );
    }
}