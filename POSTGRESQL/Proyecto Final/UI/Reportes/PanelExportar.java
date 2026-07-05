package UI.Reportes;

import javax.swing.*;
import java.awt.*;

public class PanelExportar extends JPanel {

    private JComboBox<String> comboReporte;
    private JComboBox<String> comboFormato;
    private JButton botonExportar;

    public PanelExportar() {

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));

        JLabel titulo = new JLabel("Exportación de Reportes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(30));

        panel.add(new JLabel("Seleccione el reporte"));
        panel.add(Box.createVerticalStrut(5));

        comboReporte = new JComboBox<>();

        comboReporte.addItem("Alumnos");
        comboReporte.addItem("Profesores");
        comboReporte.addItem("Grupos");

        comboReporte.setMaximumSize(new Dimension(300,35));

        panel.add(comboReporte);

        panel.add(Box.createVerticalStrut(20));

        panel.add(new JLabel("Formato"));

        panel.add(Box.createVerticalStrut(5));

        comboFormato = new JComboBox<>();

        comboFormato.addItem("PDF");
        comboFormato.addItem("CSV");

        comboFormato.setMaximumSize(new Dimension(300,35));

        panel.add(comboFormato);

        panel.add(Box.createVerticalStrut(35));

        botonExportar = new JButton("Exportar");

        botonExportar.putClientProperty("JButton.arc",20);
        botonExportar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonExportar.addActionListener(e -> exportar());

        panel.add(botonExportar);

        add(panel);

    }

    private void exportar() {

        String reporte = comboReporte.getSelectedItem().toString();
        String formato = comboFormato.getSelectedItem().toString();

        try {

            if(formato.equals("PDF")){

                switch (reporte){

                    case "Alumnos":
                        //ExportadorPDF.exportarAlumnos();
                        break;

                    case "Profesores":
                        //ExportadorPDF.exportarProfesores();
                        break;

                    case "Grupos":
                        //ExportadorPDF.exportarGrupos();
                        break;
                }

            }else{

                switch (reporte){

                    case "Alumnos":
                        //ExportadorCSV.exportarAlumnos();
                        break;

                    case "Profesores":
                        //ExportadorCSV.exportarProfesores();
                        break;

                    case "Grupos":
                        //ExportadorCSV.exportarGrupos();
                        break;
                }

            }

            JOptionPane.showMessageDialog(
                    this,
                    "Reporte exportado correctamente."
            );

        }catch(Exception ex){

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }
}