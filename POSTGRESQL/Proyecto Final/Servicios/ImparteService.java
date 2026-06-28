package Servicios;

import DAO.ImparteDAO;
import Modelos.Imparte;

import java.sql.SQLException;
import java.util.List;

public class ImparteService {

    private final ImparteDAO dao;

    public ImparteService(){

        dao =
                new ImparteDAO();

    }

    //ASIGNAR PROFESOR A GRUPO

    public void asignar(
            Imparte imparte
    ) throws SQLException{

        dao.insertar(
                imparte
        );

    }

    //OBTENER TODO

    public List<Imparte>
    obtenerTodo()
            throws SQLException{

        return dao.obtenerTodos();

    }

    //BUSCAR POR PROFESOR

    public List<Imparte>
    obtenerPorProfesor(
            int idProfesor
    )
            throws SQLException{

        return dao.obtenerPorProfesor(
                idProfesor
        );

    }

    //BUSCAR POR GRUPO

    public List<Imparte>
    obtenerPorGrupo(
            int grupo
    )
            throws SQLException{

        return dao.obtenerPorGrupo(
                grupo
        );

    }

    //ELIMINAR

    public boolean eliminar(
            int id
    )
            throws SQLException{

        return dao.eliminar(
                id
        );

    }

    //CONSULTA COMPLETA

    public List<String>
    obtenerVistaCompleta()
            throws SQLException{

        return dao.obtenerVistaCompleta();

    }

}