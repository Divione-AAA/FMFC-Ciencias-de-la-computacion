/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafos;

public class Vertice {
    private String nombre;
    private int extra;
   

     public Vertice(String nom) {
        nombre = nom;
        extra=0;
    }

     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public int getExtra() {
        return extra;
    }

  
    public void setExtra(int extra) {
        this.extra = extra;
    }

    
}
