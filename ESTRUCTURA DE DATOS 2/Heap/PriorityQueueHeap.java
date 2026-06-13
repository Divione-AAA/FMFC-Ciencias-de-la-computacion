package Heap;
import java.util.ArrayList;

class HeapNode<E>{
    /*
    Clase que se usa para almacenar valor + prioridad
    */
    E element;
    double priority;
    public HeapNode(E element, double priority){
        this.element = element;
        this.priority = priority;
    }
}

public class PriorityQueueHeap<E> implements PriorityQueue<E>{
    
    /*Este es el arbol representado mediante una amtriz */
    private ArrayList<HeapNode<E>> heap;

    /*Constructor de la clase */
    public PriorityQueueHeap(){
        heap = new ArrayList<HeapNode<E>>();
    }

    /*Operacion de intercambio interno, no es publica */
    private void swap(int i, int j){
        HeapNode<E> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /*Funcion de insertado */
    @Override
    public void insert(E element, double priority) {

        HeapNode<E> nuevo = new HeapNode<>(element, priority); //nuevo elemento
        heap.add(nuevo);
        siftUp(heap.size() - 1);//al ponerse al final, se empieza a verificar su tam con el del padre
    }

    /* Extraccion del maximo balanceandolo*/
    @Override
    public E extractMax() throws EmptyPriorityQueueException {

        if(isEmpty()) throw new EmptyPriorityQueueException("El heap esta vacio");

        E max = heap.get(0).element;

        HeapNode<E> ultimo =
                heap.remove(heap.size()-1);

        if(!heap.isEmpty()) {

            heap.set(0, ultimo);

            siftDown(0);
        }

        return max;
    }

    /* */
    private void siftDown(int i) {

        while(true) {

            int left = 2*i+1;
            int right = 2*i+2;

            int largest = i;

            if(left < heap.size() && heap.get(left).priority > heap.get(largest).priority){
                largest = left;
            }

            if(right < heap.size() && heap.get(right).priority > heap.get(largest).priority){
                largest = right;
            }

            if(largest == i)
                break;

            swap(i,largest);

            i = largest;
        }
    }

    /*Se ordena con siftup */
    private void siftUp(int i) {
        while (i > 0) {
            // calcular padre
            int padre = (i - 1) / 2;
            // si el hijo tiene mayor prioridad
            if (heap.get(i).priority > heap.get(padre).priority) {
                // intercambiar
                swap(i, padre);
                // continuar desde arriba
                i = padre;
            }else{
                // propiedad heap satisfecha
                break;
            }
        }
    }

    @Override
    /*obtiene el mayor */
    public E getMax() throws EmptyPriorityQueueException{
        if(isEmpty()) throw new EmptyPriorityQueueException("El heap esta vacio");
        return heap.get(0).element;
    }

    @Override
    /*...*/
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /*...*/
    @Override
    public int size() {
        return heap.size();
    }

    /*cambio de prioridad de un elemento */
    public void changePriority(E element, double newPriority) throws ElementNotFoundException{

        int index = -1;

        //busca el elemento
        for(int i=0;i<heap.size();i++){
            if(heap.get(i).element.equals(element)) {
                index = i;
                break;
            }
        }

        //eleva una excepcion si no existe
        if(index == -1) throw new ElementNotFoundException("Elemento no encontrado");
        //obtiene la prioridad
        double oldPriority = heap.get(index).priority;
        //lo modifica
        heap.get(index).priority = newPriority;
        //dependiendo su tamano los reordena
        if(newPriority > oldPriority){
            siftUp(index);
        }else{
            siftDown(index);
        }
    }

}