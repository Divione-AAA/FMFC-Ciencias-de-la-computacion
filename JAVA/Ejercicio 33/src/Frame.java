import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame {
    public void ventana(){
        JFrame frame = new JFrame();
        frame.setSize(720,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Calculo de area");
        frame.setLayout(null);

        JTextField texto = new JTextField();
        JButton boton = new JButton("calcular");
        JLabel res = new JLabel();

        texto.setBounds(50, 30, 300, 40);
        boton.setBounds(50, 90, 300, 40);
        res.setBounds(50,150,300,40);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double r = Double.parseDouble(texto.getText());
                    Double f;
                    Circulo c = new Circulo(r);
                    f = c.getArea();
                    res.setText(f.toString());
                }catch (Exception ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.add(res);
        frame.add(boton);
        frame.add(texto);
        frame.setVisible(true);

    }
}
