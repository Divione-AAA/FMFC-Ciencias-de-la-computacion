/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import java.util.LinkedList;

public class Laboratorio1TDAGrafo {

    public static void main(String[] args) throws Exception {

        // 1) Grafo no ponderado usando GrafoLista_Adyacencia (G1)
        // Este grafo corresponde al primer grafo solicitado en las orientaciones.
        // Aquí se deben verificar adyacencias, eliminar vértices y aristas,
        // e imprimir los recorridos desde A.
        System.out.println("GRAFO LISTA DE ADYACENCIA (G1, no ponderado)");

        GrafoLista_Adyacencia G1 = new GrafoLista_Adyacencia();
        G1.insVertice("A");
        G1.insVertice("B");
        G1.insVertice("C");
        G1.insVertice("D");
        G1.insVertice("E");
        G1.insVertice("F");
        G1.insVertice("G");

        G1.insArista("A", "D");
        G1.insArista("A", "F");
        G1.insArista("A", "B");
        G1.insArista("B", "C");
        G1.insArista("B", "F");
        G1.insArista("C", "D");
        G1.insArista("C", "F");
        G1.insArista("C", "A");
        G1.insArista("D", "E");
        G1.insArista("F", "E");
        G1.insArista("G", "E");

        G1.imprimirGrafo();

        // a) Verificar si B es adyacente a A
        System.out.println("\n¿El vértice \"B\" es adyacente a \"A\"? " + (G1.esAdyacente("B", "A") ? "Sí" : "No"));

        // b) Eliminar el vértice C e imprimir el orden
        System.out.println("\nOrden de los vértices antes de eliminar C:");
        G1.imprimirGrafo();

        System.out.println("\nEliminando el vértice C...");
        G1.elimVertice("C");

        System.out.println("Orden de los vértices después de eliminar C:");
        G1.imprimirGrafo();

        // c) Eliminar la arista C -> D
        System.out.println("\nEliminando la arista C -> D...");
        G1.elimArista("C", "D");

        // d) Recorridos desde A
        System.out.println("\nRecorrido en amplitud desde A:");
        LinkedList<String> bfsG1 = G1.recorrido_amplitud("A");
        for (String v : bfsG1) System.out.print(v + ", ");
        System.out.println();

        System.out.println("\nRecorrido en profundidad desde A:");
        LinkedList<String> dfsG1 = G1.recorrido_profundidad("A");
        for (String v : dfsG1) System.out.print(v + ", ");
        System.out.println();


        // 2) Otro grafo no ponderado usando GrafoLista_Adyacencia (G2)
        // Este es el grafo con vértices V0..V6. Aquí se deben imprimir los
        // recorridos desde V0.
        System.out.println("\n GRAFO LISTA DE ADYACENCIA (G2, no ponderado)");

        GrafoLista_Adyacencia G2 = new GrafoLista_Adyacencia();
        G2.insVertice("V0");
        G2.insVertice("V1");
        G2.insVertice("V2");
        G2.insVertice("V3");
        G2.insVertice("V4");
        G2.insVertice("V5");
        G2.insVertice("V6");

        G2.insArista("V0", "V1");
        G2.insArista("V0", "V3");
        G2.insArista("V1", "V3");
        G2.insArista("V1", "V4");
        G2.insArista("V2", "V0");
        G2.insArista("V2", "V5");
        G2.insArista("V3", "V2");
        G2.insArista("V3", "V4");
        G2.insArista("V3", "V5");
        G2.insArista("V3", "V6");
        G2.insArista("V4", "V6");
        G2.insArista("V6", "V5");

        G2.imprimirGrafo();

        System.out.println("\nRecorrido en amplitud desde V0:");
        LinkedList<String> bfsG2 = G2.recorrido_amplitud("V0");
        for (String v : bfsG2) System.out.print(v + ", ");
        System.out.println();

        System.out.println("\nRecorrido en profundidad desde V0:");
        LinkedList<String> dfsG2 = G2.recorrido_profundidad("V0");
        for (String v : dfsG2) System.out.print(v + ", ");
        System.out.println();


        // 3) Mismo grafo anterior pero ponderado usando GrafoMatriz_Ady (G3)
        // Aquí se demuestra que los recorridos no cambian porque no dependen
        // de los pesos de las aristas.
        System.out.println("\nGRAFO MATRIZ DE ADYACENCIA (G3, ponderado)");

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

        System.out.println("\nRecorrido en amplitud desde V0:");
        LinkedList<String> bfsG3 = G3.recorrido_amplitud("V0");
        for (String v : bfsG3) System.out.print(v + ", ");
        System.out.println();

        System.out.println("\nRecorrido en profundidad desde V0:");
        LinkedList<String> dfsG3 = G3.recorrido_profundidad("V0");
        for (String v : dfsG3) System.out.print(v + ", ");
        System.out.println();

        System.out.println("\nLos recorridos son iguales al grafo no ponderado porque los algoritmos");
        System.out.println("de amplitud y profundidad no utilizan los pesos, solo las adyacencias.");


        // 4) Repetir orientaciones usando GrafoMatriz_Ady (G4)
        // Este grafo es equivalente al G1 pero usando matriz de adyacencia.
        System.out.println("\nGRAFO MATRIZ DE ADYACENCIA (G4, no ponderado)");

        GrafoMatriz_Ady G4 = new GrafoMatriz_Ady();
        G4.insVertice("A");
        G4.insVertice("B");
        G4.insVertice("C");
        G4.insVertice("D");
        G4.insVertice("E");
        G4.insVertice("F");
        G4.insVertice("G");

        G4.insArista("A", "D", 1);
        G4.insArista("A", "F", 1);
        G4.insArista("A", "B", 1);
        G4.insArista("B", "C", 1);
        G4.insArista("B", "F", 1);
        G4.insArista("C", "D", 1);
        G4.insArista("C", "F", 1);
        G4.insArista("C", "A", 1);
        G4.insArista("D", "E", 1);
        G4.insArista("F", "E", 1);
        G4.insArista("G", "E", 1);

        G4.imprimirGrafo();

        System.out.println("\n¿El vértice \"B\" es adyacente a \"A\"? "
                + (G4.esAdyacente("B", "A") ? "Sí" : "No"));

        System.out.println("\nOrden de los vértices antes de eliminar C:");
        G4.imprimirGrafo();

        System.out.println("\nEliminando el vértice C...");
        G4.elimVertice("C");

        System.out.println("Orden de los vértices después de eliminar C:");
        G4.imprimirGrafo();

        System.out.println("\nEliminando la arista C -> D...");
        G4.elimArista("C", "D");

        System.out.println("\nRecorrido en amplitud desde A:");
        LinkedList<String> bfsG4 = G4.recorrido_amplitud("A");
        for (String v : bfsG4) System.out.print(v + ", ");
        System.out.println();

        System.out.println("\nRecorrido en profundidad desde A:");
        LinkedList<String> dfsG4 = G4.recorrido_profundidad("A");
        for (String v : dfsG4) System.out.print(v + ", ");
        System.out.println();
    }
}
