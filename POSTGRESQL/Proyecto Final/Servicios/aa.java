package Servicios;

import Modelos.Profesor;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

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

}