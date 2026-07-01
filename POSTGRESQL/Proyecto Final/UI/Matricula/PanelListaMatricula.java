package UI.Matricula;

import Modelos.Matricula;
import Servicios.MatriculaService;

import javax.swing.*;
import java.awt.*;

public class PanelListaMatricula extends JPanel {

    private final DefaultListModel<String> modelo;

    public PanelListaMatricula() {

        setLayout(
                new BorderLayout()
        );

        modelo =
                new DefaultListModel<>();

        JList<String> lista =
                new JList<>(
                        modelo
                );

        add(
                new JScrollPane(
                        lista
                )
        );

        cargar();

    }

    private void cargar() {

        try {

            modelo.clear();

            for (

                    Matricula m :

                    new MatriculaService()
                            .obtenerTodas()

            ){

                modelo.addElement(
                        m.toString()
                );

            }

        }

        catch (Exception ex){

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );

        }

    }

}