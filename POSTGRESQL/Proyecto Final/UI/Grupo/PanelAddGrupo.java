package UI.Grupo;

import DAO.GrupoDAO;
import Modelos.Grupo;
import java.awt.*;
import javax.swing.*;

public class PanelAddGrupo extends JPanel {

    private final JTextField campoModalidad = new JTextField();
    private final JTextField campoAmbito = new JTextField();
    private final JTextField campoNivel = new JTextField();
    private final JTextField campoCurso = new JTextField();
    private final JTextField campoGrupo = new JTextField();
    private final JTextField campoCodigoEscuela = new JTextField();

    public PanelAddGrupo() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registrar Grupo", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        formulario.add(crearFila("Modalidad:", campoModalidad));
        formulario.add(crearFila("Ámbito:", campoAmbito));
        formulario.add(crearFila("Nivel:", campoNivel));
        formulario.add(crearFila("Curso:", campoCurso));
        formulario.add(crearFila("Grupo:", campoGrupo));
        formulario.add(crearFila("Código escuela:", campoCodigoEscuela));

        JButton guardar = new JButton("Guardar grupo");
        guardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        guardar.addActionListener(e -> guardarGrupo());
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

    private void guardarGrupo() {
        try {
            String modalidad = campoModalidad.getText().trim();
            String ambito = campoAmbito.getText().trim();
            String nivel = campoNivel.getText().trim();
            String curso = campoCurso.getText().trim();
            String grupo = campoGrupo.getText().trim();
            int codigoEscuela = Integer.parseInt(campoCodigoEscuela.getText().trim());

            if (modalidad.isEmpty() || ambito.isEmpty() || nivel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Modalidad, ámbito y nivel son obligatorios.");
                return;
            }

            Grupo nuevoGrupo = new Grupo(0, modalidad, ambito, nivel, curso, grupo, codigoEscuela);
            new GrupoDAO().insertar(nuevoGrupo);
            JOptionPane.showMessageDialog(this, "Grupo registrado correctamente.");
            limpiar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El código de escuela debe ser numérico.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void limpiar() {
        campoModalidad.setText("");
        campoAmbito.setText("");
        campoNivel.setText("");
        campoCurso.setText("");
        campoGrupo.setText("");
        campoCodigoEscuela.setText("");
    }
}