package Heap;

public interface PriorityQueue<E> {

    void insert(E element, double priority);

    E extractMax() throws EmptyPriorityQueueException;

    E getMax() throws EmptyPriorityQueueException;

    boolean isEmpty();

    int size();
}