/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;


public class Laboratorio3 {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("*** ALGORITMO PARA LA BÚSQUEDA DE CAMINOS MÍNIMOS EN GRAFOS PONDERADOS CON COSTOS NEGATIVOS ***");
        System.out.println("------> GRAFO MATRIZ DE ADYACENCIA <------");
        GrafoMatriz_Ady G1 = new GrafoMatriz_Ady();
        G1.insVertice("V0");
        G1.insVertice("V1");
        G1.insVertice("V2");
        G1.insVertice("V3");
        G1.insArista("V0", "V1", 2);
        G1.insArista("V1", "V2", 1);
        G1.insArista("V1", "V3", 4);
        G1.insArista("V2", "V0", -3);       // no hay ciclo de costo negativo porque 2 + 1 + (-3) = 0
        G1.insArista("V2", "V3", 2);
        G1.imprimirGrafo();
        boolean temp1 = G1.caminoMConPesosNegativos("V0");
        if (temp1) {
            System.out.println("Caminos mínimos con pesos negativos:");
            G1.imprimirCamino("V0");
            G1.imprimirCamino("V1");
            G1.imprimirCamino("V2");
            G1.imprimirCamino("V3");
        }
        System.out.println("");

        System.out.println("------> GRAFO LISTA DE ADYACENCIA <------");
        GrafoLista_Adyacencia G2 = new GrafoLista_Adyacencia();
        G2.insVertice("V0");
        G2.insVertice("V1");
        G2.insVertice("V2");
        G2.insVertice("V3");
        G2.insArista("V0", "V1", 2);
        G2.insArista("V1", "V2", 1);
        G2.insArista("V1", "V3", 4);
        G2.insArista("V2", "V0", -4);      //se modifica el peso de esta arista para que exista un ciclo negativo porque 2 + 1 + (-4) = -1
        G2.insArista("V2", "V3", 2);
        G2.imprimirGrafo();
        boolean temp2 = G2.caminoMConPesosNegativos("V0");
        if (temp2) {
            System.out.println("Caminos mínimos con pesos negativos:");
            G2.imprimirCamino("V0");
            G2.imprimirCamino("V1");
            G2.imprimirCamino("V2");
            G2.imprimirCamino("V3");
        }
        System.out.println("");

        GrafoLista_Adyacencia G3 = new GrafoLista_Adyacencia();
        G3.insVertice("V0");
        G3.insVertice("V1");
        G3.insVertice("V2");
        G3.insVertice("V3");
        G3.insArista("V0", "V1", 1);
        G3.insArista("V0", "V2", 2);
        G3.insArista("V1", "V3", 3);
        G3.insArista("V2", "V1", -3);      //se modifica el peso de esta arista para que exista un ciclo negativo porque 2 + 1 + (-4) = -1
        G3.insArista("V2", "V3", 2);
        G3.imprimirGrafo();
        boolean temp3 = G3.caminoMConPesosNegativos("V0");
        if (temp3) {
            System.out.println("Caminos mínimos con pesos negativos:");
            G3.imprimirCamino("V0");
            G3.imprimirCamino("V1");
            G3.imprimirCamino("V2");
            G3.imprimirCamino("V3");
        }
        System.out.println("");;

        System.out.println("*** ORDENAMIENTO TOPOLÓGICO Y BÚSQUEDA DE CAMINOS MÍNIMOS EN GRAFOS ACÍCLICOS ***");
        System.out.println("------> GRAFO MATRIZ DE ADYACENCIA <------");
        GrafoMatriz_Ady G4 = new GrafoMatriz_Ady();
        G4.insVertice("V0");
        G4.insVertice("V1");
        G4.insVertice("V2");
        G4.insVertice("V3");
        G4.insVertice("V4");
        G4.insVertice("V5");
        G4.insVertice("V6");
        G4.insArista("V2", "V0", 4);
        G4.insArista("V2", "V3", 2);
        G4.insArista("V2", "V5", 5);
        G4.insArista("V0", "V3", 1);
        G4.insArista("V0", "V1", 2);
        G4.insArista("V1", "V3", 3);
        G4.insArista("V1", "V4", 10);
        G4.insArista("V3", "V4", 2);
        G4.insArista("V3", "V5", 8);
        G4.insArista("V3", "V6", 4);
        G4.insArista("V4", "V6", 6);
        G4.insArista("V6", "V5", 1);
        G4.imprimirGrafo();
        boolean temp4 = G4.caminoMAciclico("V0");
        if (temp4) {
            System.out.println("Caminos mínimos en grafos acíclicos:");
            G4.imprimirCamino("V0");
            G4.imprimirCamino("V1");
            G4.imprimirCamino("V2");
            G4.imprimirCamino("V3");
            G4.imprimirCamino("V4");
            G4.imprimirCamino("V5");
            G4.imprimirCamino("V6");
        }
        System.out.println("");

        System.out.println("------> GRAFO LISTA DE ADYACENCIA <------");
        GrafoLista_Adyacencia G5 = new GrafoLista_Adyacencia();
        G5.insVertice("V0");
        G5.insVertice("V1");
        G5.insVertice("V2");
        G5.insVertice("V3");
        G5.insVertice("V4");
        G5.insVertice("V5");
        G5.insVertice("V6");
        G5.insArista("V2", "V0", 5);
        G5.insArista("V2", "V3", 3);
        G5.insArista("V2", "V5", 6);    // si se cambiara esta arista por V5 -> V2 aparecen ciclos y por tanto no se puede ejecutar el algoritmo
        G5.insArista("V0", "V3", 2);
        G5.insArista("V0", "V1", 3);
        G5.insArista("V1", "V3", 4);
        G5.insArista("V1", "V4", 11);
        G5.insArista("V3", "V4", 3);
        G5.insArista("V3", "V5", 9);
        G5.insArista("V3", "V6", 5);
        G5.insArista("V4", "V6", 7);
        G5.insArista("V6", "V5", 2);
        G5.imprimirGrafo();
        boolean temp5 = G5.caminoMAciclico("V0");
        if (temp5) {
            System.out.println("Caminos mínimos en grafos acíclicos:");
            G5.imprimirCamino("V0");
            G5.imprimirCamino("V1");
            G5.imprimirCamino("V2");
            G5.imprimirCamino("V3");
            G5.imprimirCamino("V4");
            G5.imprimirCamino("V5");
            G5.imprimirCamino("V6");
        }
        System.out.println("");

        GrafoLista_Adyacencia G6 = new GrafoLista_Adyacencia();
        G6.insVertice("V0");
        G6.insVertice("V1");
        G6.insVertice("V2");
        G6.insVertice("V3");
        G6.insVertice("V4");
        G6.insArista("V0", "V1", 2);
        G6.insArista("V0", "V2", 5);
        G6.insArista("V1", "V2", 2);
        G6.insArista("V1", "V3", 2);
        G6.insArista("V2", "V3", 1);
        G6.insArista("V2", "V4", 2);
        G6.insArista("V3", "V4", 1);
        G6.imprimirGrafo();
        boolean temp6 = G6.caminoMAciclico("V0");
        if (temp6) {
            System.out.println("Caminos mínimos en grafos acíclicos:");
            G6.imprimirCamino("V0");
            G6.imprimirCamino("V1");
            G6.imprimirCamino("V2");
            G6.imprimirCamino("V3");
            G6.imprimirCamino("V4");
        }
        System.out.println("");
    }
    
}
