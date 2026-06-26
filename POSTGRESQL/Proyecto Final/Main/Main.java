package Main;

import com.formdev.flatlaf.FlatDarkLaf;
import Database.ConnectionDB;
import java.sql.Connection;
import DAO.*;
import Modelos.*;

public class Main {
    public static void main(String[] args) throws Exception {

        try (Connection conn = ConnectionDB.getConnection()){
            System.out.println( "Conectado a PostgreSQL");
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        try{
            FlatDarkLaf.setup();
        } catch (Exception e){
            e.printStackTrace();
        }

        //Prueba del primer DAO
        /*EscuelaDAO dao =new EscuelaDAO();

        dao.insertar(
                new Escuela(
                        1,
                        "Escuela Central",
                        "Camagüey",
                        "32223344",
                        "EDJA"
                )
        );

        System.out.println(dao.obtenerTodas().size());*/

        //new Dashboard();
    }
}