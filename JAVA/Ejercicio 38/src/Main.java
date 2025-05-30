import javax.swing.*;

public class Main extends javax.swing.JFrame {
    private JLabel Label;
    private JTextField textField1;
    private JLabel Label2;
    private JTextField textField2;

    public Main() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Puntuacion");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

}
