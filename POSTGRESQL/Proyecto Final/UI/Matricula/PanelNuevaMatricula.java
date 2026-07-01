package UI.Matricula;

import Modelos.Matricula;
import Servicios.MatriculaService;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class PanelNuevaMatricula extends JPanel {

    private final JTextField ci;
    private final JTextField periodo;
    private final JTextField condicion;
    private final JTextField grado;
    private final JTextField regimen;
    private final JTextField sesion;
    private final JTextField especialidad;
    private final JTextField grupo;

    public PanelNuevaMatricula() {

        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();

        panel.setLayout(
                new GridLayout(
                        8,
                        2,
                        10,
                        10
                )
        );

        ci=new JTextField();
        periodo=new JTextField();
        condicion=new JTextField();
        grado=new JTextField();
        regimen=new JTextField();
        sesion=new JTextField();
        especialidad=new JTextField();
        grupo=new JTextField();

        panel.add(new JLabel("CI Alumno"));
        panel.add(ci);

        panel.add(new JLabel("Periodo"));
        panel.add(periodo);

        panel.add(new JLabel("Condición"));

        panel.add(condicion);

        panel.add(new JLabel("Grado"));

        panel.add(grado);

        panel.add(new JLabel("Régimen"));

        panel.add(regimen);

        panel.add(new JLabel("Sesión"));

        panel.add(sesion);

        panel.add(new JLabel("Especialidad"));

        panel.add(especialidad);

        panel.add(new JLabel("Grupo"));

        panel.add(grupo);

        JButton guardar =
                new JButton(
                        "Registrar"
                );

        guardar.addActionListener(
                e ->
                        guardar()
        );

        add(panel);

        add(
                guardar
        );

    }

    private void guardar() {

        try {

            Matricula m =
                    new Matricula(

                            0,

                            ci.getText(),

                            periodo.getText(),

                            new Date(),

                            condicion.getText(),

                            grado.getText(),

                            regimen.getText(),

                            sesion.getText(),

                            especialidad.getText(),

                            Integer.parseInt(
                                    grupo.getText()
                            )
                    );

            new MatriculaService()
                    .registrarMatricula(
                            m
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Registrada."
            );

        }

        catch (Exception ex){

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );

        }

    }

}