package UI.Escuela;

import DAO.EscuelaDAO;
import Modelos.Escuela;
import java.awt.*;
import javax.swing.*;

public class PanelAddEscuela extends JPanel {

    private final JTextField campoCodigo = new JTextField();
    private final JTextField campoNombre = new JTextField();
    private final JTextField campoDireccion = new JTextField();
    private final JTextField campoTelefono = new JTextField();
    private final JTextField campoTipo = new JTextField();

    public PanelAddEscuela() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registrar Escuela", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        formulario.add(crearFila("Código escuela:", campoCodigo));
        formulario.add(crearFila("Nombre:", campoNombre));
        formulario.add(crearFila("Dirección:", campoDireccion));
        formulario.add(crearFila("Teléfono:", campoTelefono));
        formulario.add(crearFila("Tipo:", campoTipo));

        JButton guardar = new JButton("Guardar escuela");
        guardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        guardar.addActionListener(e -> guardarEscuela());
        formulario.add(Box.createVerticalStrut(20));
        formulario.add(guardar);

        add(formulario, BorderLayout.CENTER);
    }

    private JPanel crearFila(String texto, JComponent campo) {
        JPanel fila = new JPanel(new BorderLayout(10, 0));
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setPreferredSize(new Dimension(180, 25));
        campo.setPreferredSize(new Dimension(300, 35));
        campo.setMaximumSize(new Dimension(300, 35));
        fila.add(etiqueta, BorderLayout.WEST);
        fila.add(campo, BorderLayout.CENTER);
        fila.setMaximumSize(new Dimension(500, 45));
        return fila;
    }

    private void guardarEscuela() {
        try {
            int codigo = Integer.parseInt(campoCodigo.getText().trim());
            String nombre = campoNombre.getText().trim();
            String direccion = campoDireccion.getText().trim();
            String telefono = campoTelefono.getText().trim();
            String tipo = campoTipo.getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre de la escuela es obligatorio.");
                return;
            }

            Escuela escuela = new Escuela(codigo, nombre, direccion, telefono, tipo);
            new EscuelaDAO().insertar(escuela);
            JOptionPane.showMessageDialog(this, "Escuela registrada correctamente.");
            limpiar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El código de escuela debe ser numérico.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void limpiar() {
        campoCodigo.setText("");
        campoNombre.setText("");
        campoDireccion.setText("");
        campoTelefono.setText("");
        campoTipo.setText("");
    }
}