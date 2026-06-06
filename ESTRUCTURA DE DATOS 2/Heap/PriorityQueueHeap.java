package Heap;

import java.util.ArrayList;

class HeapNode<E>{

    E element;
    double priority;

    public HeapNode(E element, double priority) {
        this.element = element;
        this.priority = priority;
    }
}

public class PriorityQueueHeap<E> implements PriorityQueue<E> {
    
    ArrayList<HeapNode<E>> arbol;

    private ordenar(){
        
    }

    public PriorityQueueHeap(){
        arbol = new ArrayList<HeapNode<E>>();
    }

    @Override
    public void insert(E element, double priority){
        HeapNode<E> heap = new HeapNode(element, priority);
        if(arbol.isEmpty()){
            arbol.add(heap);
        }else{
            arbol.add(heap);
        }
    }
}