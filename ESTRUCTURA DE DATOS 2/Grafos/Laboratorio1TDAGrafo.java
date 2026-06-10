/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;
import java.util.Iterator;
import java.util.LinkedList;


public class Laboratorio1TDAGrafo {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        /*
        System.out.println("------> GRAFO MATRIZ DE ADYACENCIA <------");
        GrafoMatriz_Ady G1 = new GrafoMatriz_Ady();
        G1.insVertice("A");
        G1.insVertice("B");
        G1.insVertice("C");
        G1.insVertice("D");
        G1.insVertice("E");
        G1.insVertice("F");
        G1.insArista("A", "B");
        G1.insArista("A", "D");
        G1.insArista("A", "F");
        G1.insArista("B", "C");
        G1.insArista("B", "F");
        G1.insArista("C", "D");
        G1.insArista("D", "B");
        G1.insArista("E", "D");
        G1.insArista("E", "F");
        G1.insArista("F", "D");
        G1.imprimirGrafo();
        System.out.println("\n¿El vértice \"B\" es adyacente al vértice \"C\"? " + (G1.esAdyacente("B", "C") ? "Sí" : "No") + "\n");
        System.out.println("Recorrido en amplitud:");
        LinkedList<String> rec_amplitud = G1.recorrido_amplitud("A");
        Iterator iterador = rec_amplitud.iterator();

        while (iterador.hasNext()) {
            System.out.print(iterador.next() + ", ");
        }
        System.out.println("\n");
        System.out.println("Recorrido en profundidad:");
        LinkedList<String> rec_profundidad = G1.recorrido_profundidad("A");
        Iterator iterador2 = rec_profundidad.iterator();

        while (iterador2.hasNext()) {
            System.out.print(iterador2.next() + ", ");
        }
        System.out.println("\n");

        System.out.println("------> GRAFO LISTA DE ADYACENCIA <------");
        GrafoLista_Adyacencia G2 = new GrafoLista_Adyacencia();
        G2.insVertice("A");
        G2.insVertice("B");
        G2.insVertice("C");
        G2.insVertice("D");
        G2.insVertice("E");
        G2.insVertice("F");
        G2.insVertice("G");
        G2.insArista("A", "D");
        G2.insArista("A", "F");
        G2.insArista("A", "B");
        G2.insArista("B", "C");
        G2.insArista("B", "F");
        G2.insArista("C", "D");
        G2.insArista("C", "F");
        G2.insArista("C", "A");
        G2.insArista("D", "E");
        G2.insArista("F", "E");
        G2.insArista("G", "E");
        G2.imprimirGrafo();
        System.out.println("\n¿El vértice \"B\" es adyacente al vértice \"A\"? " + (G2.esAdyacente("B", "A") ? "Sí" : "No") + "\n");
        System.out.println("Recorrido en amplitud:");
        LinkedList<String> rec_amplitud2 = G2.recorrido_amplitud("B");
        Iterator iterador3 = rec_amplitud2.iterator();

        while (iterador3.hasNext()) {
            System.out.print(iterador3.next() + ", ");
        }
        System.out.println("\n");
        System.out.println("Recorrido en profundidad:");
        LinkedList<String> rec_profundidad2 = G2.recorrido_profundidad("B");
        Iterator iterador4 = rec_profundidad2.iterator();

        while (iterador4.hasNext()) {
            System.out.print(iterador4.next() + ", ");
        }
        System.out.println("");

        System.out.println("\nOrden de los vértices antes de eliminar el vértice \"C\":");
        G2.imprimirVerticesEnOrden();
        System.out.println("\nEliminando el vértice \"C\" ...");
        G2.elimVertice("C");
        G2.imprimirVerticesEnOrden(); //el último vértice ahora toma el lugar de C

        System.out.println("\nEliminando la arista \"C\"->\"D\" ...");
        G2.elimArista("C", "D");
                
        System.out.println("\nEliminando la arista \"A\"->\"F\" ...");
        G2.elimArista("A", "F");
        System.out.println("");
        G2.imprimirGrafo();
*/

        //otro grafo,recordar q para estos recorridos no importa el peso de las aristas, ellos trabajan con sus adyacentes sin importar si el grafo es o no ponderado
        GrafoLista_Adyacencia G2 = new GrafoLista_Adyacencia();
        G2.insVertice("V0");
        G2.insVertice("V1");
        G2.insVertice("V2");
        G2.insVertice("V3");
        G2.insVertice("V4");
        G2.insVertice("V5");
        G2.insVertice("V6");
        G2.insArista("V0", "V1", 0);
        G2.insArista("V0", "V3", 0);
        G2.insArista("V1", "V3", 0);
        G2.insArista("V1", "V4", 0);
        G2.insArista("V2", "V0", 0);
        G2.insArista("V2", "V5", 0);
        G2.insArista("V3", "V2", 0);
        G2.insArista("V3", "V4", 0);
        G2.insArista("V3", "V5", 0);
        G2.insArista("V3", "V6", 0);
        G2.insArista("V4", "V6", 0);
        G2.insArista("V6", "V5", 0);
        G2.imprimirGrafo();
        
        
        System.out.println("Recorrido en amplitud:");
        LinkedList<String> rec_amplitud = G2.recorrido_amplitud("A");
        Iterator iterador = rec_amplitud.iterator();

        while (iterador.hasNext()) {
            System.out.print(iterador.next() + ", ");
        }
        System.out.println("\n");
        System.out.println("Recorrido en profundidad:");
        LinkedList<String> rec_profundidad = G2.recorrido_profundidad("A");
        Iterator iterador2 = rec_profundidad.iterator();

        while (iterador2.hasNext()) {
            System.out.print(iterador2.next() + ", ");
        }
        System.out.println("\n");
//
        //grafo ponderado,es el mismo grafo anterior G2 pero con pesos en sus aristas y los recorridos son los mismos porque no dependen del peso de aristas
        System.out.println("");
        GrafoMatriz_Ady G3 = new GrafoMatriz_Ady();
        G3.insVertice("V0");
        G3.insVertice("V1");
        G3.insVertice("V2");
        G3.insVertice("V3");
        G3.insVertice("V4");
        G3.insVertice("V5");
        G3.insVertice("V6");
        G3.insArista("V0", "V1", 2);
        G3.insArista("V0", "V3", 1);
        G3.insArista("V1", "V3", 3);
        G3.insArista("V1", "V4", 10);
        G3.insArista("V2", "V0", 4);
        G3.insArista("V2", "V5", 5);
        G3.insArista("V3", "V2", 2);
        G3.insArista("V3", "V4", 2);
        G3.insArista("V3", "V5", 8);
        G3.insArista("V3", "V6", 4);
        G3.insArista("V4", "V6", 6);
        G3.insArista("V6", "V5", 1);
        G3.imprimirGrafo();
        
        
        System.out.println("Recorrido en amplitud:");
        LinkedList<String> rec_amplitud_ = G3.recorrido_amplitud("A");
        Iterator iterador_ = rec_amplitud_.iterator();

        while (iterador_.hasNext()) {
            System.out.print(iterador_.next() + ", ");
        }
        System.out.println("\n");
        System.out.println("Recorrido en profundidad:");
        LinkedList<String> rec_profundidad_ = G3.recorrido_profundidad("A");
        Iterator iterador2_ = rec_profundidad_.iterator();

        while (iterador2_.hasNext()) {
            System.out.print(iterador2_.next() + ", ");
        }
        System.out.println("\n");
    }

}
