package UI.Ayuda;

import javax.swing.*;
import java.awt.*;

public class PanelWellcome extends JPanel {

    public PanelWellcome(String mensaje) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Imagen del manatí
        JLabel imagenLabel = new JLabel();
        ImageIcon iconoOriginal = new ImageIcon("src/resources/logo.png");
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon iconoRedimensionado = new ImageIcon(imagenEscalada);
        imagenLabel.setIcon(iconoRedimensionado);
        imagenLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        imagenLabel.setAlignmentY(Component.TOP_ALIGNMENT);

        // Contenedor para el texto de la derecha
        JPanel textoPanel = new JPanel();
        textoPanel.setLayout(new BoxLayout(textoPanel, BoxLayout.Y_AXIS));
        textoPanel.setBackground(new Color(245, 245, 245));
        textoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Texto principal de bienvenida
        JLabel titulo = new JLabel("¡Bienvenido al Sistema de Monitoreo Ambiental!");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Mensaje corto
        JTextArea textoBienvenida = new JTextArea(mensaje);
        textoBienvenida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textoBienvenida.setLineWrap(true);
        textoBienvenida.setWrapStyleWord(true);
        textoBienvenida.setEditable(false);
        textoBienvenida.setBackground(new Color(245, 245, 245));
        textoBienvenida.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Mensaje largo con capacidades del sistema
        JTextArea descripcionApp = new JTextArea(
                "Esta aplicación te permite:\n\n" +
                        "• Administrar usuarios con roles de administrador o invitado.\n" +
                        "• Configurar sensores para la recolección de datos ambientales.\n" +
                        "• Visualizar datos por sensor o por entorno.\n" +
                        "• Generar alertas con métodos configurables.\n" +
                        "• Exportar, revisar y analizar tendencias de los datos.\n" +
                        "• Facilitar el trabajo científico y académico con datos en tiempo real.\n\n" +
                        "Todo esto está diseñado para ser accesible, flexible y eficiente, permitiendo una interacción clara con los datos ambientales recolectados."
        );
        descripcionApp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descripcionApp.setLineWrap(true);
        descripcionApp.setWrapStyleWord(true);
        descripcionApp.setEditable(false);
        descripcionApp.setBackground(new Color(245, 245, 245));
        descripcionApp.setAlignmentX(Component.LEFT_ALIGNMENT);

        textoPanel.add(titulo);
        textoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textoPanel.add(textoBienvenida);
        textoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        textoPanel.add(descripcionApp);

        // Agregar componentes al panel principal
        add(imagenLabel);
        add(textoPanel);
    }
}
