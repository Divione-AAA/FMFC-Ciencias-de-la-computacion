package Main;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class FlatLaf {
    public static void main(String[] args) {
        try {
            // Estilo moderno Main.FlatLaf
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar Main.FlatLaf");
        }
    }
}
