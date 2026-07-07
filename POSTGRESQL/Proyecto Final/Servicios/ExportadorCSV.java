package Servicios;

import Modelos.Alumno;
import Modelos.Grupo;
import Modelos.Profesor;

import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ExportadorCSV {

    //====================================================
    // ALUMNOS
    //====================================================

    public static void exportarAlumnos() throws Exception {

        AlumnoService service = new AlumnoService();

        List<Alumno> alumnos = service.obtenerTodos();

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("Reporte_Alumnos.csv"));

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = chooser.getSelectedFile();

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {

            pw.println("CI,Nombre,Grado,Grupo,Sexo");

            for (Alumno a : alumnos) {

                pw.printf(
                        "%s,%s,%s,%d,%s%n",
                        a.getCi(),
                        "\"" + a.getNombre1() + " " + a.getApellido1() + "\"",
                        a.getGrado(),
                        a.getCodigoGrupo(),
                        a.getSexo()
                );

            }

        }

        JOptionPane.showMessageDialog(
                null,
                "CSV generado correctamente."
        );

        Desktop.getDesktop().open(archivo);

    }

    //====================================================
    // PROFESORES
    //====================================================

    public static void exportarProfesores() throws Exception {

        ProfesorService service = new ProfesorService();

        List<Profesor> profesores = service.obtenerTodos();

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new File("Reporte_Profesores.csv")
        );

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = chooser.getSelectedFile();

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {

            pw.println("CI,Nombre,Edad,Sexo,Categoria,Cargo");

            for (Profesor p : profesores) {

                pw.printf(
                        "%s,\"%s\",%d,%s,%s,%s%n",
                        p.getCi(),
                        p.getNombreCompleto(),
                        p.getEdad(),
                        p.getSexo(),
                        p.getCategoriaDocente(),
                        p.getCargo()
                );

            }

        }

        JOptionPane.showMessageDialog(
                null,
                "CSV generado correctamente."
        );

        Desktop.getDesktop().open(archivo);

    }

    //====================================================
    // GRUPOS
    //====================================================

    public static void exportarGrupos() throws Exception {

        GrupoService service = new GrupoService();

        List<Grupo> grupos = service.obtenerGrupos();

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new File("Reporte_Grupos.csv")
        );

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = chooser.getSelectedFile();

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {

            pw.println("ID,Codigo,Nombre,Escuela");

            for (Grupo g : grupos) {

                pw.printf(
                        "%d,%s,\"%s\",%d%n",
                        g.getIdGrupo(),
                        g.getCodigoGrupo(),
                        g.getNombre(),
                        g.getEscuelaId()
                );

            }

        }

        JOptionPane.showMessageDialog(
                null,
                "CSV generado correctamente."
        );

        Desktop.getDesktop().open(archivo);

    }

}