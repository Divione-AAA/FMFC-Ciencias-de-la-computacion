import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class FlatLaf {
    public static void main(String[] args) {
        try {
            // Estilo moderno FlatLaf
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar FlatLaf");
        }
    }
}
