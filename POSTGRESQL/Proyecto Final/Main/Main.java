package Main;

import com.formdev.flatlaf.FlatDarkLaf;
import Database.ConnectionDB;
import java.sql.Connection;
import javax.swing.*;

import UI.Dashboard.Dashboard;

public class Main {
    public static void main(String[] args) throws Exception {

        try (Connection conn = ConnectionDB.getConnection()){
            System.out.println("Conectado a PostgreSQL");
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        try{

            FlatDarkLaf.setup();

            UIManager.put(
                    "Button.arc",
                    20
            );

            UIManager.put(
                    "Component.arc",
                    18
            );

            SwingUtilities.invokeLater(
                    Dashboard::new
            );

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}