import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        JFrame v = new JFrame();
        try {
            v.setTitle("ejemplo");
            v.setSize(720, 480);
            v.setLocation(100, 200);
            v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Image g = Toolkit.getDefaultToolkit().getImage("D:/TEMPORAL/icono.jpg");
            v.setIconImage(g);
        }catch (Exception e){
            e.printStackTrace();
        }
        v.setVisible(true);
    }
}