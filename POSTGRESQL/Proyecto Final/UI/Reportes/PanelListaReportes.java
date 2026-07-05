package UI.Reportes;

import Modelos.Alumno;
import Modelos.Grupo;
import Servicios.AlumnoService;
import Servicios.GrupoService;
import Servicios.ProfesorService;
import Modelos.Profesor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelListaReportes extends JPanel {

    public PanelListaReportes() {

        setLayout(new GridLayout(1, 3, 20, 20));

        JButton alumnos = crearBoton("📚 Alumnos");
        JButton profesores = crearBoton("👨 Profesores");
        JButton grupos = crearBoton("🏫 Grupos");

        alumnos.addActionListener(e -> reporteAlumnos());
        profesores.addActionListener(e -> reporteProfesores());
        grupos.addActionListener(e -> reporteGrupos());

        add(alumnos);
        add(profesores);
        add(grupos);
    }

    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        boton.putClientProperty("JButton.arc", 20);

        return boton;
    }

    //=========================================================
    // REPORTE DE ALUMNOS
    //=========================================================

    private void reporteAlumnos() {

        try {

            AlumnoService service = new AlumnoService();

            List<Alumno> lista = service.obtenerTodos();

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("CI");
            modelo.addColumn("Nombre");
            modelo.addColumn("Grado");
            modelo.addColumn("Grupo");

            for (Alumno a : lista) {

                modelo.addRow(new Object[]{
                        a.getCi(),
                        a.getNombre1() + " " + a.getApellido1(),
                        a.getGrado(),
                        a.getCodigoGrupo()
                });

            }

            mostrarTabla("Listado de Alumnos", modelo);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

    }

    //=========================================================
// REPORTE DE PROFESORES
//=========================================================

    private void reporteProfesores() {

        try {

            ProfesorService service =
                    new ProfesorService();

            List<Profesor> lista =
                    service.obtenerTodos();

            DefaultTableModel modelo =
                    new DefaultTableModel();

            modelo.addColumn("CI");
            modelo.addColumn("Nombre");
            modelo.addColumn("Categoría");
            modelo.addColumn("Municipio");
            modelo.addColumn("Cargo");

            for (Profesor p : lista) {

                modelo.addRow(new Object[]{

                        p.getCi(),

                        p.getNombreCompleto(),

                        p.getCategoriaDocente(),

                        p.getMunicipio(),

                        p.getCargo()

                });

            }

            mostrarTabla(
                    "Listado de Profesores",
                    modelo
            );

        }

        catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );

        }

    }

    //=========================================================
    // REPORTE DE GRUPOS
    //=========================================================

    private void reporteGrupos() {

        try {

            GrupoService service = new GrupoService();

            List<Grupo> lista = service.obtenerGrupos();

            DefaultTableModel modelo = new DefaultTableModel();

            modelo.addColumn("ID");
            modelo.addColumn("Código");
            modelo.addColumn("Nombre");
            modelo.addColumn("Escuela");

            for (Grupo g : lista) {

                modelo.addRow(new Object[]{
                        g.getIdGrupo(),
                        g.getCodigoGrupo(),
                        g.getNombre(),
                        g.getEscuelaId()
                });

            }

            mostrarTabla("Listado de Grupos", modelo);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());

        }

    }

    //=========================================================
    // MOSTRAR TABLA
    //=========================================================

    private void mostrarTabla(String titulo, DefaultTableModel modelo) {

        JTable tabla = new JTable(modelo);

        tabla.setRowHeight(24);

        JScrollPane scroll = new JScrollPane(tabla);

        JFrame ventana = new JFrame(titulo);

        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(800, 500);
        ventana.setLocationRelativeTo(null);

        ventana.add(scroll);

        ventana.setVisible(true);

    }

}