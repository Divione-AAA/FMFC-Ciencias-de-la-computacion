package Servicios;

import DAO.AsignaturaDAO;
import Modelos.Asignatura;
import java.sql.SQLException;
import java.util.List;

public class AsignaturaService {

    private final AsignaturaDAO dao;

    public AsignaturaService() {
        dao =
                new AsignaturaDAO();
    }

    public void registrar(
            Asignatura a
    ) throws SQLException {

        if(
                a.getNombre()
                        .isBlank()
        ){

            throw new IllegalArgumentException(
                    "Nombre obligatorio"
            );
        }

        if (a.getCodigoAsignatura() == null || a.getCodigoAsignatura().isBlank()) {
            throw new IllegalArgumentException("Código de asignatura obligatorio");
        }

        dao.insertar(a);
    }

    public List<Asignatura>
    obtenerTodas()
            throws SQLException {

        return dao.obtenerTodas();
    }
}