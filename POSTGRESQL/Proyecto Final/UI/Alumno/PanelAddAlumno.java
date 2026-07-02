package UI.Alumno;

import DAO.AlumnoDAO;
import Modelos.Alumno;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class PanelAddAlumno extends JPanel {

    private final JTextField campoCi = new JTextField();
    private final JTextField campoNombre1 = new JTextField();
    private final JTextField campoNombre2 = new JTextField();
    private final JTextField campoApellido1 = new JTextField();
    private final JTextField campoApellido2 = new JTextField();
    private final JTextField campoFechaNacimiento = new JTextField();
    private final JTextField campoSexo = new JTextField();
    private final JTextField campoColorPiel = new JTextField();
    private final JTextField campoMunicipio = new JTextField();
    private final JTextField campoConsejoPopular = new JTextField();
    private final JTextField campoGrado = new JTextField();
    private final JTextField campoRegimen = new JTextField();
    private final JTextField campoSesion = new JTextField();
    private final JTextField campoEstadoAlumno = new JTextField();
    private final JTextField campoEspecialidad = new JTextField();
    private final JTextField campoProcedenciaPadre = new JTextField();
    private final JTextField campoProcedenciaMadre = new JTextField();
    private final JTextField campoDireccion = new JTextField();
    private final JTextField campoTelefono = new JTextField();
    private final JTextField campoCodigoGrupo = new JTextField();

    public PanelAddAlumno() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registrar Alumno", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        formulario.add(crearFila("CI:", campoCi));
        formulario.add(crearFila("Primer nombre:", campoNombre1));
        formulario.add(crearFila("Segundo nombre:", campoNombre2));
        formulario.add(crearFila("Primer apellido:", campoApellido1));
        formulario.add(crearFila("Segundo apellido:", campoApellido2));
        formulario.add(crearFila("Fecha nacimiento (yyyy-MM-dd):", campoFechaNacimiento));
        formulario.add(crearFila("Sexo:", campoSexo));
        formulario.add(crearFila("Color de piel:", campoColorPiel));
        formulario.add(crearFila("Municipio:", campoMunicipio));
        formulario.add(crearFila("Consejo popular:", campoConsejoPopular));
        formulario.add(crearFila("Grado:", campoGrado));
        formulario.add(crearFila("Régimen:", campoRegimen));
        formulario.add(crearFila("Sesión:", campoSesion));
        formulario.add(crearFila("Estatus:", campoEstadoAlumno));
        formulario.add(crearFila("Especialidad:", campoEspecialidad));
        formulario.add(crearFila("Procedencia padre:", campoProcedenciaPadre));
        formulario.add(crearFila("Procedencia madre:", campoProcedenciaMadre));
        formulario.add(crearFila("Dirección:", campoDireccion));
        formulario.add(crearFila("Teléfono:", campoTelefono));
        formulario.add(crearFila("Código grupo:", campoCodigoGrupo));

        JButton guardar = new JButton("Guardar alumno");
        guardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        guardar.addActionListener(e -> guardarAlumno());
        formulario.add(Box.createVerticalStrut(20));
        formulario.add(guardar);

        JScrollPane scroll = new JScrollPane(formulario);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearFila(String texto, JComponent campo) {
        JPanel fila = new JPanel(new BorderLayout(10, 0));
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setPreferredSize(new Dimension(220, 25));
        campo.setPreferredSize(new Dimension(320, 35));
        campo.setMaximumSize(new Dimension(320, 35));
        fila.add(etiqueta, BorderLayout.WEST);
        fila.add(campo, BorderLayout.CENTER);
        fila.setMaximumSize(new Dimension(650, 45));
        return fila;
    }

    private void guardarAlumno() {
        try {
            String ci = campoCi.getText().trim();
            String nombre1 = campoNombre1.getText().trim();
            String nombre2 = campoNombre2.getText().trim();
            String apellido1 = campoApellido1.getText().trim();
            String apellido2 = campoApellido2.getText().trim();
            String fechaTexto = campoFechaNacimiento.getText().trim();
            String sexo = campoSexo.getText().trim();
            String colorPiel = campoColorPiel.getText().trim();
            String municipio = campoMunicipio.getText().trim();
            String consejoPopular = campoConsejoPopular.getText().trim();
            String grado = campoGrado.getText().trim();
            String regimen = campoRegimen.getText().trim();
            String sesion = campoSesion.getText().trim();
            String estadoAlumno = campoEstadoAlumno.getText().trim();
            String especialidad = campoEspecialidad.getText().trim();
            String procedenciaPadre = campoProcedenciaPadre.getText().trim();
            String procedenciaMadre = campoProcedenciaMadre.getText().trim();
            String direccion = campoDireccion.getText().trim();
            String telefono = campoTelefono.getText().trim();
            int codigoGrupo = Integer.parseInt(campoCodigoGrupo.getText().trim());

            if (ci.isEmpty() || nombre1.isEmpty() || apellido1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "CI, primer nombre y primer apellido son obligatorios.");
                return;
            }

            Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaTexto);

            Alumno alumno = new Alumno(
                    ci,
                    nombre1,
                    nombre2,
                    apellido1,
                    apellido2,
                    fechaNacimiento,
                    sexo,
                    colorPiel,
                    municipio,
                    consejoPopular,
                    grado,
                    regimen,
                    sesion,
                    estadoAlumno,
                    especialidad,
                    procedenciaPadre,
                    procedenciaMadre,
                    direccion,
                    telefono,
                    codigoGrupo
            );

            new AlumnoDAO().insertar(alumno);
            JOptionPane.showMessageDialog(this, "Alumno registrado correctamente.");
            limpiar();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener el formato yyyy-MM-dd.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El código de grupo debe ser numérico.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void limpiar() {
        campoCi.setText("");
        campoNombre1.setText("");
        campoNombre2.setText("");
        campoApellido1.setText("");
        campoApellido2.setText("");
        campoFechaNacimiento.setText("");
        campoSexo.setText("");
        campoColorPiel.setText("");
        campoMunicipio.setText("");
        campoConsejoPopular.setText("");
        campoGrado.setText("");
        campoRegimen.setText("");
        campoSesion.setText("");
        campoEstadoAlumno.setText("");
        campoEspecialidad.setText("");
        campoProcedenciaPadre.setText("");
        campoProcedenciaMadre.setText("");
        campoDireccion.setText("");
        campoTelefono.setText("");
        campoCodigoGrupo.setText("");
    }
}