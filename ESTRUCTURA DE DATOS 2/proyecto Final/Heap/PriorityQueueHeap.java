package Heap;

import java.util.ArrayList;
import metabolico.MetabolicRoute;

class HeapNode{

    /*
    Clase que almacena una ruta metabolica
    */

    MetabolicRoute route;

    public HeapNode(MetabolicRoute route){
        this.route=route;
    }

}

public class PriorityQueueHeap{

    /*
    Este es el arbol representado mediante ArrayList
    */

    private ArrayList<HeapNode> heap;

    /*
    Constructor de la clase
    */

    public PriorityQueueHeap(){
        heap=new ArrayList<>();
    }

    /*
    Operacion de intercambio interno
    */

    private void swap(int i,int j){

        HeapNode temp=heap.get(i);

        heap.set(i,heap.get(j));

        heap.set(j,temp);

    }

    /*
    Regla de prioridad

    1) Mayor relevancia clinica
    2) Menor numero de reacciones
    */

    private boolean higherPriority(
            HeapNode a,
            HeapNode b
    ){

        if(
                a.route.getClinicalRelevance()
                        >
                        b.route.getClinicalRelevance()
        ){

            return true;

        }

        if(
                a.route.getClinicalRelevance()
                        ==
                        b.route.getClinicalRelevance()
        ){

            return
                    a.route.getReactionCount()
                            <
                            b.route.getReactionCount();

        }

        return false;

    }

    /*
    Inserta una nueva ruta pendiente
    */

    public void insert(
            MetabolicRoute route
    ){

        HeapNode nuevo=
                new HeapNode(
                        route
                );

        heap.add(nuevo);

        siftUp(
                heap.size()-1
        );

    }

    /*
    Extrae la ruta mas prioritaria
    */

    public MetabolicRoute extractMax()
            throws EmptyPriorityQueueException{

        if(isEmpty())
            throw new EmptyPriorityQueueException(
                    "La cola esta vacia"
            );

        MetabolicRoute max=
                heap.get(0).route;

        HeapNode ultimo=
                heap.remove(
                        heap.size()-1
                );

        if(!heap.isEmpty()){

            heap.set(
                    0,
                    ultimo
            );

            siftDown(0);

        }

        return max;

    }

    /*
    Balanceo descendente
    */

    private void siftDown(int i){

        while(true){

            int left=2*i+1;

            int right=2*i+2;

            int largest=i;

            if(
                    left<heap.size()
                            &&
                            higherPriority(
                                    heap.get(left),
                                    heap.get(largest)
                            )
            ){

                largest=left;

            }

            if(
                    right<heap.size()
                            &&
                            higherPriority(
                                    heap.get(right),
                                    heap.get(largest)
                            )
            ){

                largest=right;

            }

            if(largest==i)
                break;

            swap(
                    i,
                    largest
            );

            i=largest;

        }

    }

    /*
    Balanceo ascendente
    */

    private void siftUp(int i){

        while(i>0){

            int padre=
                    (i-1)/2;

            if(
                    higherPriority(
                            heap.get(i),
                            heap.get(padre)
                    )
            ){

                swap(
                        i,
                        padre
                );

                i=padre;

            }else{

                break;

            }

        }

    }

    /*
    Obtiene la siguiente ruta
    */

    public MetabolicRoute getMax()
            throws EmptyPriorityQueueException{

        if(isEmpty())
            throw new EmptyPriorityQueueException(
                    "La cola esta vacia"
            );

        return heap.get(0).route;

    }

    /*
    Verifica vacio
    */

    public boolean isEmpty(){

        return heap.isEmpty();

    }

    /*
    Cantidad de rutas
    */

    public int size(){

        return heap.size();

    }

}
```
