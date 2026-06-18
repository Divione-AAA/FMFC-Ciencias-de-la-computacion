package ed2.proyectofinal.heap;

import java.util.ArrayList;

class HeapNode{

    /*
    Clase que se usa para almacenar ruta + prioridad
    */

    MetabolicRoute route;
    double priority;

    public HeapNode(MetabolicRoute route,double priority){
        this.route=route;
        this.priority=priority;
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
    Comparacion adaptada al proyecto

    Mayor relevancia primero

    En empate:
    menor longitud
    */

    private boolean higherPriority(
            HeapNode a,
            HeapNode b
    ){

        if(a.priority>b.priority){
            return true;
        }

        if(a.priority==b.priority){

            return
            a.route.getLength()
            <
            b.route.getLength();

        }

        return false;
    }

    /*
    Funcion de insertado
    */

    public void insert(
            MetabolicRoute route,
            double priority
    ){

        HeapNode nuevo=
                new HeapNode(
                        route,
                        priority
                );

        heap.add(nuevo);

        siftUp(
                heap.size()-1
        );

    }

    /*
    Extraccion del maximo balanceandolo
    */

    public MetabolicRoute extractMax()
            throws EmptyPriorityQueueException{

        if(isEmpty())
            throw new EmptyPriorityQueueException(
                    "El heap esta vacio"
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
    Balanceo hacia abajo
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

            swap(i,largest);

            i=largest;

        }

    }

    /*
    Se ordena con siftup
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
    Obtiene el mayor
    */

    public MetabolicRoute getMax()
            throws EmptyPriorityQueueException{

        if(isEmpty())
            throw new EmptyPriorityQueueException(
                    "El heap esta vacio"
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
    Devuelve tamaño
    */

    public int size(){
        return heap.size();
    }

    /*
    Cambio de prioridad
    */

    public void changePriority(
            MetabolicRoute route,
            double newPriority
    )
            throws ElementNotFoundException{

        int index=-1;

        for(int i=0;i<heap.size();i++){

            if(
                    heap
                    .get(i)
                    .route
                    .equals(route)
            ){

                index=i;

                break;

            }

        }

        if(index==-1)
            throw new ElementNotFoundException(
                    "Ruta no encontrada"
            );

        double oldPriority=
                heap
                .get(index)
                .priority;

        heap
                .get(index)
                .priority=
                newPriority;

        if(
                newPriority
                >
                oldPriority
        ){

            siftUp(index);

        }else{

            siftDown(index);

        }
    }
}