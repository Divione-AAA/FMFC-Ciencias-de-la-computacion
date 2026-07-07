package Main;

import com.formdev.flatlaf.FlatDarkLaf;
import UI.Dashboard.Dashboard;
import UI.Login.DialogoLoginDB;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Configurar look and feel primero para que el Login también luzca moderno
            FlatDarkLaf.setup();
            UIManager.put("Button.arc", 20);
            UIManager.put("Component.arc", 18);

            // 2. Ejecutar la UI en el Event Dispatch Thread de Swing
            SwingUtilities.invokeLater(() -> {
                DialogoLoginDB login = new DialogoLoginDB();
                login.setVisible(true); // Se detiene aquí porque es modal

                // 3. Si se validó la conexión, abrimos el Dashboard
                if (login.isConectadoExitosamente()) {
                    new Dashboard();
                } else {
                    System.out.println("Aplicación cerrada: No se estableció conexión.");
                    System.exit(0);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}