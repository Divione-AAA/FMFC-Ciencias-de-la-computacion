package UI.Dashboard;

import UI.Alumno.PanelAlumno;
import UI.Profesor.PanelProfesor;
import UI.Escuela.PanelEscuela;
import UI.Grupo.PanelGrupo;
import UI.Asignatura.PanelAsignatura;
import UI.Matricula.PanelMatricula;
import UI.Imparte.PanelImparte;
import UI.Reportes.PanelReportes;

import javax.swing.*;
import java.awt.*;

public class MenuLateral extends JPanel {

    public MenuLateral(
            Dashboard dash
    ) {

        setPreferredSize(
                new Dimension(
                        260,
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
                        15,
                        15,
                        15,
                        15
                )
        );

        // BOTONES

        JButton inicio =
                crearBoton(
                        "🏠 Inicio"
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

        JButton asignaturas =
                crearBoton(
                        "📚 Asignaturas"
                );

        JButton matriculas =
                crearBoton(
                        "📝 Matrículas"
                );

        JButton imparte =
                crearBoton(
                        "🎓 Imparte"
                );

        JButton reportes =
                crearBoton(
                        "📊 Reportes"
                );

        // EVENTOS

        inicio.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new HomePanel()
                        )
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

        matriculas.addActionListener(
                e ->
                        dash.cambiarPanel(
                                new PanelMatricula()
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

        // AÑADIR

        add(inicio);

        add(alumnos);

        add(profesores);

        add(escuelas);

        add(grupos);

        add(asignaturas);

        add(matriculas);

        add(imparte);

        add(reportes);
    }

    private JButton crearBoton(
            String texto
    ) {

        JButton b =
                new JButton(
                        texto
                );

        b.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        b.setFocusPainted(
                false
        );

        b.putClientProperty(
                "JButton.arc",
                20
        );

        return b;
    }

}