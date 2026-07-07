package UI.Profesor;

import Modelos.Profesor;
import Servicios.ProfesorService;

import javax.swing.*;
import java.awt.*;

public class PanelEditarProfesor extends JPanel {

    private final ProfesorService profesorService;

    private JTextField txtBuscarCI;
    private JButton btnBuscar, btnGuardar;

    // Campos del formulario
    private JTextField txtNombre1, txtNombre2, txtApellido1, txtApellido2;
    private JTextField txtEdad, txtSexo, txtColorPiel, txtDireccion, txtTelefono, txtMunicipio;
    private JTextField txtIntegracion, txtCentroGrad, txtAnioGrad, txtAnioInicio, txtProcedencia;
    private JTextField txtCatDocente, txtCatCientifica, txtCargo, txtNivelEnsenanza, txtEvaluacion;
    private JCheckBox chkMision, chkCuadro, chkSupera;

    public PanelEditarProfesor() {
        this.profesorService = new ProfesorService();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Búsqueda Superior ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Profesor"));
        panelBusqueda.add(new JLabel("CI del Profesor:"));
        txtBuscarCI = new JTextField(12);
        panelBusqueda.add(txtBuscarCI);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);
        add(panelBusqueda, BorderLayout.NORTH);

        // --- Formulario Central con Scroll ---
        JPanel panelForm = new JPanel(new GridLayout(12, 4, 8, 8));
        panelForm.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        txtNombre1 = new JTextField(); txtNombre2 = new JTextField();
        txtApellido1 = new JTextField(); txtApellido2 = new JTextField();
        txtEdad = new JTextField(); txtSexo = new JTextField();
        txtColorPiel = new JTextField(); txtDireccion = new JTextField();
        txtTelefono = new JTextField(); txtMunicipio = new JTextField();
        txtIntegracion = new JTextField(); txtCentroGrad = new JTextField();
        txtAnioGrad = new JTextField(); txtAnioInicio = new JTextField();
        txtProcedencia = new JTextField(); txtCatDocente = new JTextField();
        txtCatCientifica = new JTextField(); txtCargo = new JTextField();
        txtNivelEnsenanza = new JTextField(); txtEvaluacion = new JTextField();

        chkMision = new JCheckBox("Misión Internacionalista");
        chkCuadro = new JCheckBox("Es Cuadro");
        chkSupera = new JCheckBox("Se Supera");

        panelForm.add(new JLabel("Primer Nombre (*):")); panelForm.add(txtNombre1);
        panelForm.add(new JLabel("Segundo Nombre:")); panelForm.add(txtNombre2);
        panelForm.add(new JLabel("Primer Apellido:")); panelForm.add(txtApellido1);
        panelForm.add(new JLabel("Segundo Apellido:")); panelForm.add(txtApellido2);
        panelForm.add(new JLabel("Edad:")); panelForm.add(txtEdad);
        panelForm.add(new JLabel("Sexo:")); panelForm.add(txtSexo);
        panelForm.add(new JLabel("Color Piel:")); panelForm.add(txtColorPiel);
        panelForm.add(new JLabel("Dirección:")); panelForm.add(txtDireccion);
        panelForm.add(new JLabel("Teléfono:")); panelForm.add(txtTelefono);
        panelForm.add(new JLabel("Municipio:")); panelForm.add(txtMunicipio);
        panelForm.add(new JLabel("Integración Pol.:")); panelForm.add(txtIntegracion);
        panelForm.add(new JLabel("Centro Graduación:")); panelForm.add(txtCentroGrad);
        panelForm.add(new JLabel("Año Graduación:")); panelForm.add(txtAnioGrad);
        panelForm.add(new JLabel("Año Inicio Trab.:")); panelForm.add(txtAnioInicio);
        panelForm.add(new JLabel("Procedencia:")); panelForm.add(txtProcedencia);
        panelForm.add(new JLabel("Cat. Docente:")); panelForm.add(txtCatDocente);
        panelForm.add(new JLabel("Cat. Científica:")); panelForm.add(txtCatCientifica);
        panelForm.add(new JLabel("Cargo:")); panelForm.add(txtCargo);
        panelForm.add(new JLabel("Nivel Enseñanza:")); panelForm.add(txtNivelEnsenanza);
        panelForm.add(new JLabel("Última Evaluación:")); panelForm.add(txtEvaluacion);

        panelForm.add(chkMision); panelForm.add(chkCuadro);
        panelForm.add(chkSupera); panelForm.add(new JLabel(""));

        setFormularioHabilitado(false);
        add(new JScrollPane(panelForm), BorderLayout.CENTER);

        // --- Botón Inferior ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setEnabled(false);
        panelBotones.add(btnGuardar);
        add(panelBotones, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarProfesor());
        btnGuardar.addActionListener(e -> guardarCambios());
    }

    private void setFormularioHabilitado(boolean b) {
        txtNombre1.setEnabled(b); txtNombre2.setEnabled(b); txtApellido1.setEnabled(b); txtApellido2.setEnabled(b);
        txtEdad.setEnabled(b); txtSexo.setEnabled(b); txtColorPiel.setEnabled(b); txtDireccion.setEnabled(b);
        txtTelefono.setEnabled(b); txtMunicipio.setEnabled(b); txtIntegracion.setEnabled(b); txtCentroGrad.setEnabled(b);
        txtAnioGrad.setEnabled(b); txtAnioInicio.setEnabled(b); txtProcedencia.setEnabled(b); txtCatDocente.setEnabled(b);
        txtCatCientifica.setEnabled(b); txtCargo.setEnabled(b); txtNivelEnsenanza.setEnabled(b); txtEvaluacion.setEnabled(b);
        chkMision.setEnabled(b); chkCuadro.setEnabled(b); chkSupera.setEnabled(b);
    }

    private void buscarProfesor() {
        String ci = txtBuscarCI.getText().trim();
        if (ci.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un CI para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Profesor p = profesorService.buscarPorCI(ci);
            if (p != null) {
                txtNombre1.setText(p.getNombre1()); txtNombre2.setText(p.getNombre2());
                txtApellido1.setText(p.getApellido1()); txtApellido2.setText(p.getApellido2());
                txtEdad.setText(String.valueOf(p.getEdad())); txtSexo.setText(p.getSexo());
                txtColorPiel.setText(p.getColorPiel()); txtDireccion.setText(p.getDireccionParticular());
                txtTelefono.setText(p.getTelefonoParticular()); txtMunicipio.setText(p.getMunicipio());
                txtIntegracion.setText(p.getIntegracionPolitica()); txtCentroGrad.setText(p.getCentroGraduacion());
                txtAnioGrad.setText(String.valueOf(p.getAnioGraduacion())); txtAnioInicio.setText(String.valueOf(p.getAnioInicioTrabajo()));
                txtProcedencia.setText(p.getProcedencia()); txtCatDocente.setText(p.getCategoriaDocente());
                txtCatCientifica.setText(p.getCategoriaCientifica()); txtCargo.setText(p.getCargo());
                txtNivelEnsenanza.setText(p.getNivelEnsenanza()); txtEvaluacion.setText(p.getUltimaEvaluacionProfesional());
                chkMision.setSelected(p.isMisionInternacionalista()); chkCuadro.setSelected(p.isEsCuadro());
                chkSupera.setSelected(p.isSeSupera());

                setFormularioHabilitado(true);
                btnGuardar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Profesor no localizado.", "Información", JOptionPane.INFORMATION_MESSAGE);
                setFormularioHabilitado(false); btnGuardar.setEnabled(false);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambios() {
        try {
            Profesor p = new Profesor(
                    txtBuscarCI.getText().trim(),
                    txtNombre1.getText().trim(), txtNombre2.getText().trim(),
                    txtApellido1.getText().trim(), txtApellido2.getText().trim(),
                    Integer.parseInt(txtEdad.getText().trim()), txtSexo.getText().trim(),
                    txtColorPiel.getText().trim(), txtDireccion.getText().trim(),
                    txtTelefono.getText().trim(), txtMunicipio.getText().trim(),
                    txtIntegracion.getText().trim(), txtCentroGrad.getText().trim(),
                    Integer.parseInt(txtAnioGrad.getText().trim()), Integer.parseInt(txtAnioInicio.getText().trim()),
                    txtProcedencia.getText().trim(), txtCatDocente.getText().trim(),
                    txtCatCientifica.getText().trim(), chkMision.isSelected(),
                    chkCuadro.isSelected(), txtCargo.getText().trim(),
                    txtNivelEnsenanza.getText().trim(), chkSupera.isSelected(),
                    txtEvaluacion.getText().trim()
            );

            profesorService.actualizar(p);
            JOptionPane.showMessageDialog(this, "Profesor modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            setFormularioHabilitado(false); btnGuardar.setEnabled(false); txtBuscarCI.setText("");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Verifique los campos numéricos (Edad, Años).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}