package UI.Dashboard;

import UI.Alumno.PanelAlumno;
import UI.Asignatura.PanelAsignatura;
import UI.Escuela.PanelEscuela;
import UI.Grupo.PanelGrupo;
import UI.Imparte.PanelImparte;
import UI.Matricula.PanelMatricula;
import UI.Profesor.PanelProfesor;
import UI.Reportes.PanelReportes;
import java.awt.*;
import javax.swing.*;

public class MenuLateral extends JPanel {

    public MenuLateral(
            Dashboard dash
    ) {

        setPreferredSize(new Dimension(230, 0));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(BorderFactory.createEmptyBorder(15, 12, 15, 12));

        JButton alumnos =
                crearBoton(
                        "Alumnos"
                );

        JButton profesores =
                crearBoton(
                        "Profesores"
                );

        JButton escuelas =
                crearBoton(
                        "Escuelas"
                );

        JButton grupos =
                crearBoton(
                        "Grupos"
                );

        JButton asignaturas =
                crearBoton(
                        "Asignaturas"
                );

        JButton imparte =
                crearBoton(
                        "Imparte"
                );

        JButton reportes =
                crearBoton(
                        "Reportes"
                );

        alumnos.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelAlumno()
                        )
        );

        profesores.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelProfesor()
                        )
        );

        escuelas.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelEscuela()
                        )
        );

        grupos.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelGrupo()
                        )
        );

        asignaturas.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelAsignatura()
                        )
        );

        imparte.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelImparte()
                        )
        );

        reportes.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelReportes()
                        )
        );

        add(Box.createVerticalStrut(6));
        add(alumnos);
        add(Box.createVerticalStrut(6));
        add(profesores);
        add(Box.createVerticalStrut(6));
        add(escuelas);
        add(Box.createVerticalStrut(6));
        add(grupos);
        add(Box.createVerticalStrut(6));
        add(Box.createVerticalStrut(6));
        add(asignaturas);
        add(Box.createVerticalStrut(6));
        add(imparte);
        add(Box.createVerticalStrut(6));
        add(reportes);

    }

    private JButton crearBoton(
            String texto
    ) {

        JButton boton =
                new JButton(
                        texto
                );

        boton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        boton.setFocusPainted(
                false
        );

        boton.setPreferredSize(new Dimension(200, 44));
        boton.setMaximumSize(new Dimension(200, 44));
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);

        boton.putClientProperty(
                "JButton.arc",
                20
        );

        boton.putClientProperty(
                "JButton.buttonType",
                "roundRect"
        );

        return boton;

    }

}