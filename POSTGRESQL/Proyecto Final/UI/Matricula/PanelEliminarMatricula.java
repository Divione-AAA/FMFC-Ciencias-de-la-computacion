package UI.Matricula;

import Servicios.MatriculaService;

import javax.swing.*;
import java.awt.*;

public class PanelEliminarMatricula extends JPanel {

    public PanelEliminarMatricula() {

        setLayout(
                new FlowLayout()
        );

        JTextField id =
                new JTextField(
                        10
                );

        JButton eliminar =
                new JButton(
                        "Eliminar"
                );

        eliminar.addActionListener(
                e -> {

                    try {

                        new MatriculaService()
                                .eliminar(

                                        Integer.parseInt(
                                                id.getText()
                                        )

                                );

                        JOptionPane.showMessageDialog(
                                this,
                                "Eliminada."
                        );

                    }

                    catch (Exception ex){

                        JOptionPane.showMessageDialog(
                                this,
                                ex.getMessage()
                        );

                    }

                }
        );

        add(
                new JLabel(
                        "ID:"
                )
        );

        add(id);

        add(eliminar);

    }

}