/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafo;
import java.util.LinkedList;


public class VerticeExt extends Vertice {
    private LinkedList<Arista> lista;

    public VerticeExt(String nombre) {
        super(nombre);
        lista = new LinkedList<Arista>();
    }

     public LinkedList getLista() {
        return lista;
    }

    public void setLista(LinkedList lista) {
        this.lista=lista;
    }

}
