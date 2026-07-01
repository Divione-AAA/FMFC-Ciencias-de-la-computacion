package UI.Dashboard;

import UI.Alumno.PanelAlumno;
import UI.Asignatura.PanelAsignatura;
import UI.Escuela.PanelEscuela;
import UI.Grupo.PanelGrupo;
import UI.Imparte.PanelImparte;
import UI.Matricula.PanelMatricula;
import UI.Profesor.PanelProfesor;
import UI.Reportes.PanelReportes;

import javax.swing.*;
import java.awt.*;

public class MenuLateral extends JPanel {

    public MenuLateral(
            Dashboard dash
    ) {

        setPreferredSize(
                new Dimension(
                        270,
                        0
                )
        );

        setLayout(
                new GridLayout(
                        10,
                        1,
                        10,
                        10
                )
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        15,
                        20,
                        15
                )
        );

        JButton alumnos =
                crearBoton(
                        "👨 Alumnos"
                );

        JButton profesores =
                crearBoton(
                        "👩 Profesores"
                );

        JButton escuelas =
                crearBoton(
                        "🏫 Escuelas"
                );

        JButton grupos =
                crearBoton(
                        "👥 Grupos"
                );

        JButton matriculas =
                crearBoton(
                        "📝 Matrículas"
                );

        JButton asignaturas =
                crearBoton(
                        "📚 Asignaturas"
                );

        JButton imparte =
                crearBoton(
                        "🎓 Imparte"
                );

        JButton reportes =
                crearBoton(
                        "📊 Reportes"
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

        matriculas.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelMatricula()
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

        add(alumnos);

        add(profesores);

        add(escuelas);

        add(grupos);

        add(matriculas);

        add(asignaturas);

        add(imparte);

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

        boton.setPreferredSize(
                new Dimension(
                        230,
                        55
                )
        );

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