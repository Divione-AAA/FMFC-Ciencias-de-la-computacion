package UI;

import javax.swing.*;
import java.awt.*;

public class PanelAbout extends JPanel {

    public PanelAbout(String s) {
        setLayout(new BorderLayout(20, 0)); // espacio horizontal entre paneles
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel izquierdo con descripción
        JTextArea descripcion = new JTextArea();
        descripcion.setText("""
                Esta aplicación de monitoreo ambiental ha sido diseñada como parte del Proyecto Científico de la Práctica Laboral 2025. Su objetivo principal es facilitar la supervisión y análisis de variables ambientales tales como temperatura, humedad, gases contaminantes y otros indicadores relevantes mediante sensores distribuidos en distintos entornos físicos.

                Mediante esta plataforma, se pueden registrar, visualizar y analizar datos en tiempo real, además de generar alertas automatizadas ante condiciones anómalas. La información recopilada permite tomar decisiones fundamentadas sobre gestión ambiental, prevención de riesgos ecológicos y mejora de condiciones en zonas críticas, como laboratorios, centros de investigación, invernaderos o áreas urbanas con alto impacto ambiental.

                La importancia de este tipo de herramientas tecnológicas radica en su capacidad para ofrecer una visión integral y continua del estado del entorno, apoyar políticas sostenibles y empoderar a comunidades e instituciones a través de la información. Esta aplicación incorpora roles de usuario, historial de datos, visualización gráfica y configuración personalizada, permitiendo una experiencia flexible, segura y moderna.

                Con un diseño visual moderno, accesible y modular, el sistema ha sido desarrollado en Java utilizando tecnologías como Swing, FlatLaf para interfaz moderna, y SQLite como base de datos embebida, lo cual asegura compatibilidad y portabilidad sin requerir instalaciones complejas.

                En resumen, se trata de una herramienta educativa, práctica y profesional que demuestra cómo desde la programación se puede contribuir significativamente a la protección del medio ambiente y al desarrollo tecnológico.
                """);
        descripcion.setWrapStyleWord(true);
        descripcion.setLineWrap(true);
        descripcion.setEditable(false);
        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descripcion.setBackground(new Color(248, 248, 248));
        JScrollPane scrollDesc = new JScrollPane(descripcion);
        scrollDesc.setPreferredSize(new Dimension(500, 400));

        // Panel derecho dividido en dos
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1, 0, 20));

        JTextArea integrantes = new JTextArea();
        integrantes.setText("""
                Integrantes del Proyecto:
                - Héctor Agüero
                - Osdani Broche
                - Abel Liván
                """);
        integrantes.setEditable(false);
        integrantes.setFont(new Font("Segoe UI", Font.BOLD, 14));
        integrantes.setBackground(new Color(235, 240, 250));

        JLabel infoFinal = new JLabel("<html><div style='text-align: center;'>Proyecto Científico<br>Práctica Laboral 2025</div></html>", SwingConstants.CENTER);
        infoFinal.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        infoFinal.setOpaque(true);
        infoFinal.setBackground(new Color(250, 250, 235));
        infoFinal.setPreferredSize(new Dimension(250, 60));

        panelDerecho.add(integrantes);
        panelDerecho.add(infoFinal);
        panelDerecho.setPreferredSize(new Dimension(250, 400));

        // Agregar a layout principal
        add(scrollDesc, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);
    }
}
