import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static Long fact(Long n, JFrame frame){
        if(n<0){
            JOptionPane.showMessageDialog(frame,"Valor negativo, invalido");
            return (long) -1;
        }
       return n>0 ? n*fact(n-1,frame) : 1;
    }

    private static Long fibo(Long n){
        if(n<0) return (long) 0;
        if(n==1) return (long) 1;
        return fibo(n-1) + fibo(n-2);
    }

    public static void main(String[] args) {

        //frame
        JFrame frame = new JFrame("Calculo de Factorial");
        frame.setSize(720,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //label
        JLabel label = new JLabel();
        label.setText("Valor");
        label.setBounds(500,50,100,30);

        //entrada
        JTextField entrada = new JTextField();
        entrada.setBounds(75,50,100,30);


        //boton
        JButton boton = new JButton();
        boton.setText("Factorial");
        boton.setBounds((frame.getWidth() - 100)/2, frame.getHeight() - 120, 100, 40);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long n = Long.parseLong(entrada.getText());
                Long f = fact(n,frame);
                label.setText(f.toString());
            }
        });

        //boton 2
        JButton boton2 = new JButton();
        boton2.setText("Fibonnacci");
        boton2.setBounds(200, frame.getHeight() - 120, 100, 40);
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long n = Long.parseLong(entrada.getText());
                Long f = fibo(n);
                label.setText(f.toString());
            }
        });

        //sobretexto
        JLabel t1 = new JLabel();
        t1.setBounds(75,20,100,30);
        t1.setText("Valor");

        //segundo sobretexto
        JLabel t2 = new JLabel();
        t2.setBounds(500,20,100,30);
        t2.setText("Resultado");

        //add al Frame
        frame.add(label);
        frame.add(t2);
        frame.add(t1);
        frame.add(boton);
        frame.add(boton2);
        frame.add(entrada);
        frame.setVisible(true);
    }
}