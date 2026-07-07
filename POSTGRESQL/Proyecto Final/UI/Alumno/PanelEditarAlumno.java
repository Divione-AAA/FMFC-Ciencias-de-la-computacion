package UI.Alumno;

import Modelos.Alumno;
import Servicios.AlumnoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PanelEditarAlumno extends JPanel {

    private final AlumnoService alumnoService;

    // Componentes de búsqueda
    private JTextField txtBuscarCI;
    private JButton btnBuscar;

    // Componentes del formulario
    private JTextField txtNombre1, txtNombre2, txtApellido1, txtApellido2;
    private JTextField txtSexo, txtColorPiel, txtMunicipio, txtConsejoPopular;
    private JTextField txtGrado, txtRegimen, txtSesion, txtEstatus, txtEspecialidad;
    private JTextField txtProcPadre, txtProcMadre, txtDireccion, txtTelefono, txtGrupoId;
    private JSpinner spinFechaNacimiento;
    private JButton btnGuardar;

    public PanelEditarAlumno() {
        this.alumnoService = new AlumnoService();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel Superior: Búsqueda ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Alumno a Editar"));

        panelBusqueda.add(new JLabel("CI del Alumno:"));
        txtBuscarCI = new JTextField(15);
        panelBusqueda.add(txtBuscarCI);

        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // --- Panel Central: Formulario ---
        JPanel panelFormulario = new JPanel(new GridLayout(10, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Alumno"));

        txtNombre1 = new JTextField(); panelFormulario.add(new JLabel("Primer Nombre (*):")); panelFormulario.add(txtNombre1);
        txtNombre2 = new JTextField(); panelFormulario.add(new JLabel("Segundo Nombre:")); panelFormulario.add(txtNombre2);
        txtApellido1 = new JTextField(); panelFormulario.add(new JLabel("Primer Apellido:")); panelFormulario.add(txtApellido1);
        txtApellido2 = new JTextField(); panelFormulario.add(new JLabel("Segundo Apellido:")); panelFormulario.add(txtApellido2);

        spinFechaNacimiento = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinFechaNacimiento, "yyyy-MM-dd");
        spinFechaNacimiento.setEditor(dateEditor);
        panelFormulario.add(new JLabel("Fecha Nacimiento:")); panelFormulario.add(spinFechaNacimiento);

        txtSexo = new JTextField(); panelFormulario.add(new JLabel("Sexo:")); panelFormulario.add(txtSexo);
        txtColorPiel = new JTextField(); panelFormulario.add(new JLabel("Color Piel:")); panelFormulario.add(txtColorPiel);
        txtMunicipio = new JTextField(); panelFormulario.add(new JLabel("Municipio:")); panelFormulario.add(txtMunicipio);
        txtConsejoPopular = new JTextField(); panelFormulario.add(new JLabel("Consejo Popular:")); panelFormulario.add(txtConsejoPopular);
        txtGrado = new JTextField(); panelFormulario.add(new JLabel("Grado:")); panelFormulario.add(txtGrado);
        txtRegimen = new JTextField(); panelFormulario.add(new JLabel("Régimen:")); panelFormulario.add(txtRegimen);
        txtSesion = new JTextField(); panelFormulario.add(new JLabel("Sesión:")); panelFormulario.add(txtSesion);
        txtEstatus = new JTextField(); panelFormulario.add(new JLabel("Estatus:")); panelFormulario.add(txtEstatus);
        txtEspecialidad = new JTextField(); panelFormulario.add(new JLabel("Especialidad:")); panelFormulario.add(txtEspecialidad);
        txtProcPadre = new JTextField(); panelFormulario.add(new JLabel("Proc. Social Padre:")); panelFormulario.add(txtProcPadre);
        txtProcMadre = new JTextField(); panelFormulario.add(new JLabel("Proc. Social Madre:")); panelFormulario.add(txtProcMadre);
        txtDireccion = new JTextField(); panelFormulario.add(new JLabel("Dirección:")); panelFormulario.add(txtDireccion);
        txtTelefono = new JTextField(); panelFormulario.add(new JLabel("Teléfono:")); panelFormulario.add(txtTelefono);
        txtGrupoId = new JTextField(); panelFormulario.add(new JLabel("ID Grupo (*):")); panelFormulario.add(txtGrupoId);

        // Deshabilitar formulario inicialmente hasta que se busque un alumno
        setFormularioHabilitado(false);

        add(new JScrollPane(panelFormulario), BorderLayout.CENTER);

        // --- Panel Inferior: Botón Guardar ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setEnabled(false);
        panelBotones.add(btnGuardar);
        add(panelBotones, BorderLayout.SOUTH);

        // --- Eventos ---
        btnBuscar.addActionListener(e -> buscarAlumno());
        btnGuardar.addActionListener(e -> guardarCambios());
    }

    private void setFormularioHabilitado(boolean habilitado) {
        txtNombre1.setEnabled(habilitado); txtNombre2.setEnabled(habilitado);
        txtApellido1.setEnabled(habilitado); txtApellido2.setEnabled(habilitado);
        spinFechaNacimiento.setEnabled(habilitado); txtSexo.setEnabled(habilitado);
        txtColorPiel.setEnabled(habilitado); txtMunicipio.setEnabled(habilitado);
        txtConsejoPopular.setEnabled(habilitado); txtGrado.setEnabled(habilitado);
        txtRegimen.setEnabled(habilitado); txtSesion.setEnabled(habilitado);
        txtEstatus.setEnabled(habilitado); txtEspecialidad.setEnabled(habilitado);
        txtProcPadre.setEnabled(habilitado); txtProcMadre.setEnabled(habilitado);
        txtDireccion.setEnabled(habilitado); txtTelefono.setEnabled(habilitado);
        txtGrupoId.setEnabled(habilitado);
    }

    private void buscarAlumno() {
        String ci = txtBuscarCI.getText().trim();
        if (ci.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un CI.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Alumno alumno = alumnoService.buscarPorCI(ci);
            if (alumno != null) {
                txtNombre1.setText(alumno.getNombre1());
                txtNombre2.setText(alumno.getNombre2());
                txtApellido1.setText(alumno.getApellido1());
                txtApellido2.setText(alumno.getApellido2());
                spinFechaNacimiento.setValue(alumno.getFechaNacimiento());
                txtSexo.setText(alumno.getSexo());
                txtColorPiel.setText(alumno.getColorPiel());
                txtMunicipio.setText(alumno.getMunicipio());
                txtConsejoPopular.setText(alumno.getConsejoPopular());
                txtGrado.setText(alumno.getGrado());
                txtRegimen.setText(alumno.getRegimen());
                txtSesion.setText(alumno.getSesion());
                txtEstatus.setText(alumno.getEstatusAlumno());
                txtEspecialidad.setText(alumno.getEspecialidad());
                txtProcPadre.setText(alumno.getProcedenciaSocialPadre());
                txtProcMadre.setText(alumno.getProcedenciaSocialMadre());
                txtDireccion.setText(alumno.getDireccion());
                txtTelefono.setText(alumno.getTelefono());
                txtGrupoId.setText(String.valueOf(alumno.getCodigoGrupo()));

                setFormularioHabilitado(true);
                btnGuardar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún alumno con ese CI.", "No Encontrado", JOptionPane.INFORMATION_MESSAGE);
                setFormularioHabilitado(false);
                btnGuardar.setEnabled(false);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambios() {
        try {
            String ci = txtBuscarCI.getText().trim();
            int grupoId = Integer.parseInt(txtGrupoId.getText().trim());

            Alumno alumnoActualizado = new Alumno(
                    ci,
                    txtNombre1.getText().trim(),
                    txtNombre2.getText().trim(),
                    txtApellido1.getText().trim(),
                    txtApellido2.getText().trim(),
                    (Date) spinFechaNacimiento.getValue(),
                    txtSexo.getText().trim(),
                    txtColorPiel.getText().trim(),
                    txtMunicipio.getText().trim(),
                    txtConsejoPopular.getText().trim(),
                    txtGrado.getText().trim(),
                    txtRegimen.getText().trim(),
                    txtSesion.getText().trim(),
                    txtEstatus.getText().trim(),
                    txtEspecialidad.getText().trim(),
                    txtProcPadre.getText().trim(),
                    txtProcMadre.getText().trim(),
                    txtDireccion.getText().trim(),
                    txtTelefono.getText().trim(),
                    grupoId
            );

            alumnoService.actualizarAlumno(alumnoActualizado);
            JOptionPane.showMessageDialog(this, "Alumno actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar y resetear
            setFormularioHabilitado(false);
            btnGuardar.setEnabled(false);
            txtBuscarCI.setText("");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El ID del Grupo debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}