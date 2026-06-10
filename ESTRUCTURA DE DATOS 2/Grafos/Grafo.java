/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafos;

import java.util.LinkedList;


public interface Grafo {
    
    int insVertice(String nombre);
    void insArista(String origen,String destino,float costo);
    void insArista(String origen,String destino);
    void elimArista(String origen,String destino);
    void elimVertice(String nombre);

    //operaciones basicas
    int buscar(String nombre);
    boolean esAdyacente(String vertice1,String vertice2);

    //recorridos sobre grafos
    LinkedList recorrido_amplitud(String VerticeOrigen);
    LinkedList recorrido_profundidad(String VerticeOrig);
  

}
