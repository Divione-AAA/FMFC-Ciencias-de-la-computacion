package UI.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HeaderPanel extends JPanel {

    private final JLabel fechaLabel;

    public HeaderPanel() {

        setLayout(new BorderLayout());

        setPreferredSize(
                new Dimension(
                        0,
                        90
                )
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        JLabel titulo =
                new JLabel(
                        """
                        Sistema Gestión EDJA
                        """
                );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        JLabel subtitulo =
                new JLabel(
                        "Dirección Municipal de Educación"
                );

        subtitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        JPanel izquierda =
                new JPanel();

        izquierda.setLayout(
                new BoxLayout(
                        izquierda,
                        BoxLayout.Y_AXIS
                )
        );

        izquierda.setOpaque(false);

        izquierda.add(titulo);

        izquierda.add(subtitulo);

        fechaLabel =
                new JLabel();

        fechaLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        actualizarFecha();

        Timer timer =
                new Timer(
                        1000,
                        e -> actualizarFecha()
                );

        timer.start();

        add(
                izquierda,
                BorderLayout.WEST
        );

        add(
                fechaLabel,
                BorderLayout.EAST
        );

        putClientProperty(
                "JComponent.roundRect",
                true
        );
    }

    private void actualizarFecha() {

        LocalDateTime ahora =
                LocalDateTime.now();

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm:ss"
                );

        fechaLabel.setText(
                ahora.format(
                        formato
                )
        );
    }

}