/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.PriorityQueue;


public class GrafoLista_Adyacencia implements Grafo {

    private static int TAMANHO_TABLA_INI = 10;
    private int numVertices;
    private VerticeExt[] tabla;
    private float INFINITO = Float.MAX_VALUE;

    public GrafoLista_Adyacencia() {
        numVertices = 0;
        tabla = new VerticeExt[TAMANHO_TABLA_INI];

    }

    public int insVertice(String nombre) {
        int i = 0;
        while (i < numVertices) {
            if (tabla[i].getNombre().equals(nombre)) {
                return i;
            } else {
                i++;
            }
        }
        VerticeExt v1 = new VerticeExt(nombre);
        if (tabla.length == numVertices) {
            duplicarVectorTabla();
        }
        tabla[numVertices] = v1;
        return numVertices++;
    }

    public int buscar(String nombre) {
        int i = 0;
        while (i < numVertices) {
            if (tabla[i].getNombre().equals(nombre)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    private void duplicarVectorTabla() {
        VerticeExt[] nuevaTabla = new VerticeExt[tabla.length * 2];
        System.arraycopy(tabla, 0, nuevaTabla, 0, tabla.length);
        tabla = nuevaTabla;
    }

    public void insArista(String origen, String destino, float costo) {
        int origen2 = insVertice(origen);
        int destino2 = insVertice(destino);
        Arista a1 = new Arista(destino2, costo);
        tabla[origen2].getLista().addLast(a1);

    }

    public void insArista(String origen, String destino) {
        int origen2 = insVertice(origen);
        int destino2 = insVertice(destino);
        Arista a1 = new Arista(destino2, 0);
        tabla[origen2].getLista().addLast(a1);

    }

    public void elimArista(String origen, String destino) {
        int i = buscar(origen);
        int j = buscar(destino);
        LinkedList<Arista> L;
        if (i != -1 && j != -1) {
            L = tabla[i].getLista();
            for (int k = 0; k < L.size(); k++) {
                Arista x = L.get(k);
                int dest = x.getDestino();
                if (dest == j) {
                    L.remove(k);
                    break;
                }
            }
        } else {
            System.out.println("la arista no existe");
        }
    }

    public void elimArista2(String origen, String destino) {
        int i = buscar(origen);
        int j = buscar(destino);
        Iterator it;
        if (i != -1 && j != -1) {
            it = tabla[i].getLista().iterator();
            while (it.hasNext()) {
                Arista w = (Arista) it.next();
                int dest = w.getDestino();
                if (j == dest) {
                    it.remove();
                    break;
                }
            }
        } else {
            System.out.println("la arista no existe");
        }
    }

    public void elimVertice(String nombre) {
        int i = buscar(nombre);
        if (i != -1) {
            tabla[i] = tabla[--numVertices];
            LinkedList<Arista> L;
            for (int j = 0; j < numVertices; j++) {
                L = tabla[j].getLista();
                for (int k = 0; k < L.size(); k++) {
                    Arista x = L.get(k);
                    int destino = x.getDestino();
                    if (i == destino) {
                        L.remove(k);
                    }
                    if (numVertices == destino) {
                        L.get(k).setDestino(i);
                    }
                }

            }
        } else {
            System.out.println("El v�rtice que se quiere eliminar no existe");
        }
    }

    public boolean esAdyacente(String vertice1, String vertice2) {//aqu� se usa el iterador de la lista para que vean un ejemplo us�ndolo
        int i = buscar(vertice1);
        int j = buscar(vertice2);
        Iterator it;
        if (i != -1 && j != -1) {
            it = tabla[i].getLista().iterator();
            while (it.hasNext()) {
                Arista w = (Arista) it.next();
                int dest = w.getDestino();
                if (j == dest) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean esAdyacente2(String vertice1, String vertice2) {//es el mismo m�todo anterior pero sin usar el iterador
        int i = buscar(vertice1);
        int j = buscar(vertice2);
        LinkedList<Arista> L;
        if (i != -1 && j != -1) {
            L = tabla[i].getLista();
            for (int k = 0; k < L.size(); k++) {
                Arista x = L.get(k);
                int destino = x.getDestino();
                if (destino == j) {
                    return true;
                }
            }
        }
        return false;
    }
    
        private void limpiarDatos() {
        for (int i = 0; i < numVertices; i++) {
            tabla[i].setExtra(0);
            tabla[i].setAnt(-1);
            tabla[i].setDist(INFINITO);
        }
    }

    public LinkedList<String> recorrido_amplitud(String VerticeOrigen) {
        limpiarDatos();
        boolean repetir = true;
        int VerticeOrig = insVertice(VerticeOrigen);
        Queue<Integer> q = new LinkedList<>();
        LinkedList<String> L = new LinkedList<>();
        Iterator it;
        while (repetir) {//este ciclo es necesario para poder incorporar al recorrido los vertices aislados
            tabla[VerticeOrig].setExtra(1);
            L.add(tabla[VerticeOrig].getNombre());
            q.add(VerticeOrig);
            while (!q.isEmpty()) {
                int actual = q.remove();
                it = tabla[actual].getLista().iterator();
                while (it.hasNext()) {
                    Arista w = (Arista) it.next();
                    int dest = w.getDestino();
                    if (tabla[dest].getExtra() != 1) {
                        tabla[dest].setExtra(1);
                        L.add(tabla[dest].getNombre());
                        q.add(dest);
                    }

                }
            }
            repetir = false;
            for (int j = 0; j < numVertices; j++) {//este for se hace para los vértices aislados del grafo no se queden fuera del recorrido como es el caso del vertice E en el grafo del main
                if (tabla[j].getExtra() != 1) {
                    VerticeOrig = j;
                    repetir = true;
                    break;
                }

            }
        }
        return L;
    }

    private LinkedList recorrido_profundidadR(String VerticeOrigen, LinkedList L) {
        int VerticeOrig = insVertice(VerticeOrigen);
        tabla[VerticeOrig].setExtra(1);
        L.add(tabla[VerticeOrig].getNombre());
        //System.out.print(tabla[VerticeOrig].getNombre());
        Iterator it;
        it = tabla[VerticeOrig].getLista().iterator();
        while (it.hasNext()) {
            Arista w = (Arista) it.next();
            int dest = w.getDestino();
            if (tabla[dest].getExtra() != 1) {
                recorrido_profundidadR(tabla[dest].getNombre(), L);
            }

        }

        return L;
    }
 
    public LinkedList recorrido_profundidad(String VerticeOrig) {
        limpiarDatos();
        LinkedList<String> L = new LinkedList<>();
        recorrido_profundidadR(VerticeOrig, L);
        for (int j = 0; j < numVertices; j++) {//este for se hace para los vertices aislados del grafo no se queden fuera del recorrido como es el caso del vertice E en el grafo del main
            if (tabla[j].getExtra() != 1) {
                recorrido_profundidadR(tabla[j].getNombre(), L);
            }
        }
        return L;
    }

    public void imprimirVerticesEnOrden() {
        for (int i = 0; i < numVertices; i++) {
            if (i == numVertices - 1) {
                System.out.print(tabla[i].getNombre() + "\n");
            } else {
                System.out.print(tabla[i].getNombre() + ", ");
            }
        }
    }

    public void imprimirGrafo() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + "  [" + tabla[i].getNombre() + "] ---> ");
            Iterator it = tabla[i].getLista().iterator();
            if (!it.hasNext()) {
                System.out.print("||");
            } else {
                while (it.hasNext()) {
                    Arista w = (Arista) it.next();
                    int destino = w.getDestino();
                    float costo = w.getCosto();
                    System.out.print("[" + destino + " | " + costo + "]" + " ---> ");
                }
                System.out.print("||");
            }
            System.out.println("");
        }
    }
    
    public void caminoMSinPeso(String VerticeOrigen) {
        int verticeOrig = buscar(VerticeOrigen);
        if (verticeOrig == -1) {
            System.out.println("El vértice " + VerticeOrigen + " no existe");
        } else {
            Queue<Integer> q = new LinkedList<>();
            limpiarDatos();
            tabla[verticeOrig].setDist(0);
            q.add(verticeOrig);
            Iterator it;
            while (!q.isEmpty()) {
                int actual = q.remove();
                it = tabla[actual].getLista().iterator();
                while (it.hasNext()) {
                    Arista a = (Arista) it.next();
                    int w = a.getDestino();
                    if (tabla[w].getDist() == INFINITO) {
                        tabla[w].setDist(tabla[actual].getDist() + 1);
                        tabla[w].setAnt(actual);
                        q.add(w);
                    }
                }
            }
        }
    }

    public boolean caminoMConPesoPositivo(String verticeOrig){//este es Disjktra
        int v,w;
        float cvw;
        limpiarDatos();//se inicializan todos los atributos
        int verticeOrigen=buscar(verticeOrig);//buscar el vertice origen para saber si existe y ademas para saber su posicion
        if (verticeOrigen==-1)//si el vértice origen no existe se devuelve falso
            return false;
        Vertice verO=new Vertice(verticeOrig,0);//se crea objeto Vertice con el nombre del vertice origen y distancia 0
        PriorityQueue<Vertice>cp=new PriorityQueue<Vertice>();//se crea una cola de prioridad
        tabla[verticeOrigen].setDist(0);//se pone en 0 la distancia del vertice origen
        cp.add(verO);//se inserta el objeto verO creado en la cola de prioridad
        Iterator it;
        while(!cp.isEmpty()) {//mientras la cola no este vacía
            Vertice ver = cp.remove();//eliminar de la cola
            v=buscar(ver.getNombre());
            if (tabla[v].getExtra() == 0){//si no esta marcado
              tabla[v].setExtra(1);//marcarlo
              it=tabla[v].getLista().iterator();
              while (it.hasNext()){ //se recorre la lista de adyacencia del vertice marcado
                   Arista a= (Arista) it.next();
                   w = a.getDestino();
                   cvw = a.getCosto();
                   if(cvw < 0)  // si el costo es negativo retornar falso
                     return false;
                   if (tabla[w].getDist() > tabla[v].getDist()+ cvw) {//si la distancia en el vertice es mas grande que dist(v)+costo, se actualiza todo
                     tabla[w].setDist(tabla[v].getDist() + cvw);// se actualiza la distancia
                     tabla[w].setAnt(v);//se actualiza el anterior
                     Vertice vecino=new Vertice(tabla[w].getNombre(),tabla[w].getDist());//se crea un objeto Vertice con el nombre de w y su nueva distancia
                     cp.add(vecino);//se inserta en la cola al objeto creado.
                  }//termina el if
              }//termino el while que recorre la lista de adyacencia
            }//termina el if que se ejecutó si el vétice no estaba marcado
        }//termina el while que pregunta si la cola esta vacía
       return true;
    }

    public void imprimirCamino(String verticeDestino) {//para imprimir el camino minimo desde el vértice origen hasta un vértice destino especificado
        int verticeDest = buscar(verticeDestino);
        if (verticeDest == -1) {
            System.out.println("El vértice " + verticeDestino + " no existe");
        }
        if (tabla[verticeDest].getDist() == INFINITO) {
            System.out.println(verticeDestino + " es inalcanzable");
        } else {
            imprimirCaminoRec(verticeDest);
            System.out.println(" (el costo es " + tabla[verticeDest].getDist() + ")");
        }
//        System.out.println();
    }

    private void imprimirCaminoRec(int verticeDest) {
        if (tabla[verticeDest].getAnt() != -1 && tabla[verticeDest].getDist() != 0) {
            imprimirCaminoRec(tabla[verticeDest].getAnt());
            System.out.print(" -> ");
        }
        System.out.print(tabla[verticeDest].getNombre());
    }
    
}
