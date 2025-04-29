import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static void change(JPanel panel){
        Color randomColor = new Color(
                (int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256)
        );
        panel.setBackground(randomColor);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);
        frame.setLayout(null); // Diseño absoluto (debes manejar manualmente posiciones y tamaños)

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setBounds(180, 80, 360, 160); // setBounds(x, y, width, height)

        //cambiar color a rojo
        JButton boton1 = new JButton("Cambiar");
        boton1.setBounds(panel.getHeight()/2,panel.getWidth()/2,100,100);
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                change(panel);
            }
        });

        frame.add(panel);
        frame.add(boton1);
        frame.setVisible(true);
    }
}

/*        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long n = Long.parseLong(entrada.getText());
                Long f = fact(n,frame);
                label.setText(f.toString());
            }
        });
*/