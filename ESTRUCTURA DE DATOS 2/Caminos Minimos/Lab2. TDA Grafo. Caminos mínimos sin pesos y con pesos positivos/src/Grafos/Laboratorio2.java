/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;


public class Laboratorio2 {

    public static void main(String[] args) throws Exception {
        System.out.println("------> #1 GRAFO LISTA DE ADYACENCIA <------");
        GrafoLista_Adyacencia G2 = new GrafoLista_Adyacencia();
        G2.insVertice("V0");
        G2.insVertice("V1");
        G2.insVertice("V2");
        G2.insVertice("V3");
        G2.insVertice("V4");
        G2.insVertice("V5");
        G2.insArista("V0", "V1");
        G2.insArista("V0", "V2");
        G2.insArista("V0", "V4");
        G2.insArista("V1", "V3");
        G2.insArista("V2", "V5");
        G2.insArista("V3", "V0");
        G2.insArista("V4", "V2");
        G2.insArista("V5", "V0");
        G2.insArista("V5", "V1");
        G2.imprimirGrafo();
        G2.caminoMSinPeso("V5");
        System.out.println("\nCaminos mínimos sin pesos:");
        G2.imprimirCamino("V0");
        G2.imprimirCamino("V1");
        G2.imprimirCamino("V2");
        G2.imprimirCamino("V3");
        G2.imprimirCamino("V4");
        G2.imprimirCamino("V5");

        System.out.println("");
        System.out.println("------> #2 GRAFO LISTA DE ADYACENCIA <------");
        GrafoLista_Adyacencia G3 = new GrafoLista_Adyacencia();
        G3.insVertice("V0");
        G3.insVertice("V1");
        G3.insVertice("V2");
        G3.insVertice("V3");
        G3.insVertice("V4");
        G3.insVertice("V5");
        G3.insArista("V0", "V1", 2);
        G3.insArista("V0", "V2", 3);
        G3.insArista("V1", "V3", 2);
        G3.insArista("V1", "V5", 3);
        G3.insArista("V2", "V4", 1);
        G3.insArista("V3", "V0", 3);
        G3.insArista("V3", "V2", 5);
        G3.insArista("V4", "V0", 1);
        G3.insArista("V5", "V0", 4);
        G3.insArista("V5", "V4", 2);
        G3.imprimirGrafo();
        G3.caminoMConPesoPositivo("V5");
        System.out.println("\nCaminos mínimos con pesos positivos:");
        G3.imprimirCamino("V0");
        G3.imprimirCamino("V1");
        G3.imprimirCamino("V2");
        G3.imprimirCamino("V3");
        G3.imprimirCamino("V4");
        G3.imprimirCamino("V5");
        System.out.println("");

        System.out.println("------> #3 GRAFO MATRIZ DE ADYACENCIA <------");
        GrafoMatriz_Ady G4 = new GrafoMatriz_Ady();
        G4.insVertice("V0");
        G4.insVertice("V1");
        G4.insVertice("V2");
        G4.insVertice("V3");
        G4.insVertice("V4");
        G4.insVertice("V5");
        G4.insArista("V0", "V1");
        G4.insArista("V0", "V2");
        G4.insArista("V0", "V4");
        G4.insArista("V1", "V3");
        G4.insArista("V2", "V5");
        G4.insArista("V3", "V0");
        G4.insArista("V4", "V2");
        G4.insArista("V5", "V0");
        G4.insArista("V5", "V1");
        G4.imprimirGrafo();
        G4.caminoMSinPeso("V5");
        System.out.println("\nCaminos mínimos sin pesos:");
        G4.imprimirCamino("V0");
        G4.imprimirCamino("V1");
        G4.imprimirCamino("V2");
        G4.imprimirCamino("V3");
        G4.imprimirCamino("V4");
        G4.imprimirCamino("V5");

        System.out.println("");
        GrafoMatriz_Ady G5 = new GrafoMatriz_Ady();
        G5.insVertice("V0");
        G5.insVertice("V1");
        G5.insVertice("V2");
        G5.insVertice("V3");
        G5.insVertice("V4");
        G5.insVertice("V5");
        G5.insArista("V0", "V1", 2);
        G5.insArista("V0", "V2", 3);
        G5.insArista("V1", "V3", 2);
        G5.insArista("V1", "V5", 3);
        G5.insArista("V2", "V4", 1);
        G5.insArista("V3", "V0", 3);
        G5.insArista("V3", "V2", 5);
        G5.insArista("V4", "V0", 1);
        G5.insArista("V5", "V0", 4);
        G5.insArista("V5", "V4", 2);
        G5.imprimirGrafo();
        G5.caminoMConPesoPositivo("V5");
        System.out.println("\nCaminos mínimos con pesos positivos:");
        G5.imprimirCamino("V0");
        G5.imprimirCamino("V1");
        G5.imprimirCamino("V2");
        G5.imprimirCamino("V3");
        G5.imprimirCamino("V4");
        G5.imprimirCamino("V5");
        System.out.println("");
    }
    
}
