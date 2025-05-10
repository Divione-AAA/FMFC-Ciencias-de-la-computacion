import UI.GestionUsuario.LoginUI;
import com.formdev.flatlaf.FlatLightLaf;
import GestionUsuario.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        try {
            FlatLightLaf.setup();
            Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
            Crearuser.crearUsuario(conn, "Admin", "1234", "admin");
            conn.close();
            LoginUI.main(args);
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
