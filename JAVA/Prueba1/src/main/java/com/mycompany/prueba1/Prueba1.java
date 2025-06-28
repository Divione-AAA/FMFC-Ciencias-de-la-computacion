/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prueba1;

/**
 *
 * @author Hector David
 */
public class Prueba1 {
    private static NewJFrame t = new NewJFrame();
    private static NewJFrame1 t1 = new NewJFrame1();
    public static void main(String[] args) {  
        t.setVisible(true);
        t.setSize(360,360);
    }
    
    public static void t2(){
       
        t1.setVisible(true);
    }
    
    public static void t1(int p1,int p2){
        t.show(p1,p2);
        t.setVisible(true);
    }
    
}
