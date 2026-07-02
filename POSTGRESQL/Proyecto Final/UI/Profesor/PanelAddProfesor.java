package UI.Profesor;

import DAO.ProfesorDAO;
import Modelos.Profesor;
import java.awt.*;
import javax.swing.*;

public class PanelAddProfesor extends JPanel {

    private final JTextField campoCi = new JTextField();
    private final JTextField campoNombre1 = new JTextField();
    private final JTextField campoNombre2 = new JTextField();
    private final JTextField campoApellido1 = new JTextField();
    private final JTextField campoApellido2 = new JTextField();
    private final JTextField campoEdad = new JTextField();
    private final JTextField campoSexo = new JTextField();
    private final JTextField campoColorPiel = new JTextField();
    private final JTextField campoDireccion = new JTextField();
    private final JTextField campoTelefono = new JTextField();
    private final JTextField campoMunicipio = new JTextField();
    private final JTextField campoIntegracionPolitica = new JTextField();
    private final JTextField campoCentroGraduacion = new JTextField();
    private final JTextField campoAnioGraduacion = new JTextField();
    private final JTextField campoAnioInicioTrabajo = new JTextField();
    private final JTextField campoProcedencia = new JTextField();
    private final JTextField campoCategoriaDocente = new JTextField();
    private final JTextField campoCategoriaCientifica = new JTextField();
    private final JCheckBox checkMisionInternacionalista = new JCheckBox("Sí");
    private final JCheckBox checkEsCuadro = new JCheckBox("Sí");
    private final JTextField campoCargo = new JTextField();
    private final JTextField campoNivelEnsenanza = new JTextField();
    private final JCheckBox checkSeSupera = new JCheckBox("Sí");
    private final JTextField campoUltimaEvaluacion = new JTextField();

    public PanelAddProfesor() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registrar Profesor", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        formulario.add(crearFila("CI:", campoCi));
        formulario.add(crearFila("Primer nombre:", campoNombre1));
        formulario.add(crearFila("Segundo nombre:", campoNombre2));
        formulario.add(crearFila("Primer apellido:", campoApellido1));
        formulario.add(crearFila("Segundo apellido:", campoApellido2));
        formulario.add(crearFila("Edad:", campoEdad));
        formulario.add(crearFila("Sexo:", campoSexo));
        formulario.add(crearFila("Color de piel:", campoColorPiel));
        formulario.add(crearFila("Dirección particular:", campoDireccion));
        formulario.add(crearFila("Teléfono particular:", campoTelefono));
        formulario.add(crearFila("Municipio:", campoMunicipio));
        formulario.add(crearFila("Integración política:", campoIntegracionPolitica));
        formulario.add(crearFila("Centro de graduación:", campoCentroGraduacion));
        formulario.add(crearFila("Año graduación:", campoAnioGraduacion));
        formulario.add(crearFila("Año inicio trabajo:", campoAnioInicioTrabajo));
        formulario.add(crearFila("Procedencia:", campoProcedencia));
        formulario.add(crearFila("Categoría docente:", campoCategoriaDocente));
        formulario.add(crearFila("Categoría científica:", campoCategoriaCientifica));
        formulario.add(crearFilaBoolean("Misión internacionalista:", checkMisionInternacionalista));
        formulario.add(crearFilaBoolean("Es cuadro:", checkEsCuadro));
        formulario.add(crearFila("Cargo:", campoCargo));
        formulario.add(crearFila("Nivel enseñanza:", campoNivelEnsenanza));
        formulario.add(crearFilaBoolean("Se supera:", checkSeSupera));
        formulario.add(crearFila("Última evaluación profesional:", campoUltimaEvaluacion));

        JButton guardar = new JButton("Guardar profesor");
        guardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        guardar.addActionListener(e -> guardarProfesor());
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

    private JPanel crearFilaBoolean(String texto, JCheckBox checkBox) {
        JPanel fila = new JPanel(new BorderLayout(10, 0));
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setPreferredSize(new Dimension(220, 25));
        fila.add(etiqueta, BorderLayout.WEST);
        fila.add(checkBox, BorderLayout.CENTER);
        fila.setMaximumSize(new Dimension(650, 45));
        return fila;
    }

    private void guardarProfesor() {
        try {
            String ci = campoCi.getText().trim();
            String nombre1 = campoNombre1.getText().trim();
            String nombre2 = campoNombre2.getText().trim();
            String apellido1 = campoApellido1.getText().trim();
            String apellido2 = campoApellido2.getText().trim();
            int edad = Integer.parseInt(campoEdad.getText().trim());
            String sexo = campoSexo.getText().trim();
            String colorPiel = campoColorPiel.getText().trim();
            String direccion = campoDireccion.getText().trim();
            String telefono = campoTelefono.getText().trim();
            String municipio = campoMunicipio.getText().trim();
            String integracionPolitica = campoIntegracionPolitica.getText().trim();
            String centroGraduacion = campoCentroGraduacion.getText().trim();
            int anioGraduacion = Integer.parseInt(campoAnioGraduacion.getText().trim());
            int anioInicioTrabajo = Integer.parseInt(campoAnioInicioTrabajo.getText().trim());
            String procedencia = campoProcedencia.getText().trim();
            String categoriaDocente = campoCategoriaDocente.getText().trim();
            String categoriaCientifica = campoCategoriaCientifica.getText().trim();
            boolean misionInternacionalista = checkMisionInternacionalista.isSelected();
            boolean esCuadro = checkEsCuadro.isSelected();
            String cargo = campoCargo.getText().trim();
            String nivelEnsenanza = campoNivelEnsenanza.getText().trim();
            boolean seSupera = checkSeSupera.isSelected();
            String ultimaEvaluacion = campoUltimaEvaluacion.getText().trim();
            if (ci.isEmpty() || nombre1.isEmpty() || apellido1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "CI, primer nombre y primer apellido son obligatorios.");
                return;
            }

            Profesor profesor = new Profesor(
                    ci,
                    nombre1,
                    nombre2,
                    apellido1,
                    apellido2,
                    edad,
                    sexo,
                    colorPiel,
                    direccion,
                    telefono,
                    municipio,
                    integracionPolitica,
                    centroGraduacion,
                    anioGraduacion,
                    anioInicioTrabajo,
                    procedencia,
                    categoriaDocente,
                    categoriaCientifica,
                    misionInternacionalista,
                    esCuadro,
                    cargo,
                    nivelEnsenanza,
                    seSupera,
                    ultimaEvaluacion
            );

            new ProfesorDAO().insertar(profesor);
            JOptionPane.showMessageDialog(this, "Profesor registrado correctamente.");
            limpiar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad, años y código de escuela deben ser numéricos.");
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
        campoEdad.setText("");
        campoSexo.setText("");
        campoColorPiel.setText("");
        campoDireccion.setText("");
        campoTelefono.setText("");
        campoMunicipio.setText("");
        campoIntegracionPolitica.setText("");
        campoCentroGraduacion.setText("");
        campoAnioGraduacion.setText("");
        campoAnioInicioTrabajo.setText("");
        campoProcedencia.setText("");
        campoCategoriaDocente.setText("");
        campoCategoriaCientifica.setText("");
        checkMisionInternacionalista.setSelected(false);
        checkEsCuadro.setSelected(false);
        campoCargo.setText("");
        campoNivelEnsenanza.setText("");
        checkSeSupera.setSelected(false);
        campoUltimaEvaluacion.setText("");
    }
}