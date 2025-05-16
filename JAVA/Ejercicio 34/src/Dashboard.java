import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        //Crearlo
        setLayout(null);
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Crear formularios
        JPanel abrir = new JPanel();
        JPanel guardar = new JPanel();
        JPanel cerrar = new JPanel();
        JPanel salir = new JPanel();
        JPanel cal =new JPanel();
        JPanel calPal =new JPanel();
        cardLayout = new CardLayout();
        main = new JPanel(cardLayout);

        add(main, BorderLayout.CENTER);

        setJMenuBar(crearMenu());

    }

    private JMenuBar crearMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fichero = new JMenu("Fichero");
        fichero.add(crearMenuItem())


    }

    private JMenuItem crearMenuItem(String s){

    }

    private

}
