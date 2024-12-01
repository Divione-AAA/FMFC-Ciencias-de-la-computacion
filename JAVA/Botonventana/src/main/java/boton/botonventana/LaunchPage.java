package boton.botonventana;
import javax.swing.*;
import java.awt.event.*;

public class LaunchPage implements ActionListener{
    
    JFrame frame = new JFrame();
    JButton button = new JButton("New Window");
    
    public LaunchPage(){
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        
        button.setFocusable(true);
        button.setBounds(100,160,200,40);
        button.addActionListener(this);
        
        frame.add(button);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
            NewWindows windows = new NewWindows();
        }
    }
}
