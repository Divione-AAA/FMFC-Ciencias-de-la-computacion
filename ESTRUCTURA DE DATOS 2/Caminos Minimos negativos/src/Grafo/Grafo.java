/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Grafo;

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
    LinkedList<String> ordenamientoTopologico();
    LinkedList<String> getCamino(String verticeDestino);
    void imprimirCamino(String verticeDestino);
    
    //caminos mínimos
    void caminoMSinPeso(String VerticeOrigen);
    boolean caminoMConPesoPositivo(String VerticeOrigen);
    boolean caminoMConPesosNegativos(String VerticeOrigen);
    boolean caminoMAciclico(String verticeO);
    
}
