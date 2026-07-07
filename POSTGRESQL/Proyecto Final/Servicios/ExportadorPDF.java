package Servicios;

import Modelos.Alumno;
import Modelos.Grupo;
import Modelos.Profesor;
import Servicios.AlumnoService;
import Servicios.GrupoService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ExportadorPDF {

    public static void exportarProfesores() throws Exception {

        ProfesorService service = new ProfesorService();

        List<Profesor> profesores = service.obtenerTodos();

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new File("Reporte_Profesores.pdf")
        );

        int opcion = chooser.showSaveDialog(null);

        if (opcion != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = chooser.getSelectedFile();

        Document documento = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(archivo)
        );

        documento.open();

        Font titulo =
                new Font(
                        Font.HELVETICA,
                        18,
                        Font.BOLD
                );

        Paragraph encabezado =
                new Paragraph(
                        "REPORTE DE PROFESORES",
                        titulo
                );

        encabezado.setAlignment(Element.ALIGN_CENTER);

        documento.add(encabezado);

        documento.add(new Paragraph(" "));

        documento.add(
                new Paragraph(
                        "Fecha: " + LocalDate.now()
                )
        );

        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(6);

        tabla.setWidthPercentage(100);

        tabla.setWidths(
                new float[]{
                        2.3f,
                        4f,
                        2f,
                        2f,
                        3f,
                        3f
                }
        );

        agregarCabecera(tabla, "CI");
        agregarCabecera(tabla, "Nombre");
        agregarCabecera(tabla, "Edad");
        agregarCabecera(tabla, "Sexo");
        agregarCabecera(tabla, "Categoría");
        agregarCabecera(tabla, "Cargo");

        for (Profesor p : profesores) {

            tabla.addCell(p.getCi());

            tabla.addCell(p.getNombreCompleto());

            tabla.addCell(String.valueOf(p.getEdad()));

            tabla.addCell(p.getSexo());

            tabla.addCell(p.getCategoriaDocente());

            tabla.addCell(p.getCargo());

        }

        documento.add(tabla);

        documento.close();

        JOptionPane.showMessageDialog(
                null,
                "PDF generado correctamente."
        );

        Desktop.getDesktop().open(archivo);

    }

    private static void agregarCabecera(
            PdfPTable tabla,
            String texto
    ) {

        Font fuente =
                new Font(
                        Font.HELVETICA,
                        12,
                        Font.BOLD
                );

        PdfPCell celda =
                new PdfPCell(
                        new Phrase(texto, fuente)
                );

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(8);

        tabla.addCell(celda);

    }
    public static void exportarAlumnos() throws Exception {

        AlumnoService service = new AlumnoService();

        List<Alumno> alumnos = service.obtenerTodos();

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("Reporte_Alumnos.pdf"));

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = chooser.getSelectedFile();

        Document documento = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(archivo)
        );

        documento.open();

        Font titulo = new Font(Font.HELVETICA,18,Font.BOLD);

        Paragraph encabezado =
                new Paragraph(
                        "REPORTE DE ALUMNOS",
                        titulo
                );

        encabezado.setAlignment(Element.ALIGN_CENTER);

        documento.add(encabezado);

        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Fecha: " + LocalDate.now()));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(5);

        tabla.setWidthPercentage(100);

        tabla.setWidths(new float[]{
                2.5f,
                4f,
                2f,
                2f,
                2f
        });

        agregarCabecera(tabla,"CI");
        agregarCabecera(tabla,"Nombre");
        agregarCabecera(tabla,"Grado");
        agregarCabecera(tabla,"Grupo");
        agregarCabecera(tabla,"Sexo");

        for(Alumno a : alumnos){

            tabla.addCell(a.getCi());

            tabla.addCell(
                    a.getNombre1()
                            + " "
                            + a.getApellido1()
            );

            tabla.addCell(a.getGrado());

            tabla.addCell(
                    String.valueOf(
                            a.getCodigoGrupo()
                    )
            );

            tabla.addCell(a.getSexo());

        }

        documento.add(tabla);

        documento.close();

        JOptionPane.showMessageDialog(
                null,
                "PDF generado correctamente."
        );

        Desktop.getDesktop().open(archivo);

    }
    public static void exportarGrupos() throws Exception {

        GrupoService service = new GrupoService();

        List<Grupo> grupos = service.obtenerGrupos();

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new File("Reporte_Grupos.pdf")
        );

        if(chooser.showSaveDialog(null)!=JFileChooser.APPROVE_OPTION){
            return;
        }

        File archivo = chooser.getSelectedFile();

        Document documento = new Document(PageSize.A4);

        PdfWriter.getInstance(
                documento,
                new FileOutputStream(archivo)
        );

        documento.open();

        Font titulo =
                new Font(
                        Font.HELVETICA,
                        18,
                        Font.BOLD
                );

        Paragraph encabezado =
                new Paragraph(
                        "REPORTE DE GRUPOS",
                        titulo
                );

        encabezado.setAlignment(
                Element.ALIGN_CENTER
        );

        documento.add(encabezado);

        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Fecha: " + LocalDate.now()));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(4);

        tabla.setWidthPercentage(100);

        tabla.setWidths(new float[]{
                1.5f,
                2f,
                3f,
                2f
        });

        agregarCabecera(tabla,"ID");
        agregarCabecera(tabla,"Código");
        agregarCabecera(tabla,"Nombre");
        agregarCabecera(tabla,"Escuela");

        for(Grupo g : grupos){

            tabla.addCell(
                    String.valueOf(
                            g.getIdGrupo()
                    )
            );

            tabla.addCell(
                    g.getCodigoGrupo()
            );

            tabla.addCell(
                    g.getNombre()
            );

            tabla.addCell(
                    String.valueOf(
                            g.getEscuelaId()
                    )
            );

        }

        documento.add(tabla);

        documento.close();

        JOptionPane.showMessageDialog(
                null,
                "PDF generado correctamente."
        );

        Desktop.getDesktop().open(archivo);

    }

}