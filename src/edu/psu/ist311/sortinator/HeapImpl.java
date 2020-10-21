package edu.psu.ist311.sortinator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class HeapImpl<E> implements ISortinator<E> {

    private final Comparator<E> order;
    private ArrayList<E> heap;
    private boolean accepting;

    public HeapImpl(Comparator<E> o) {
        this.order = o;
        this.heap = new ArrayList<E>();
        this.accepting = true;
    }

    /**
     * @requires 0 <= i < contents.size()
     * @ensures the index of i's left child is returned
     */
    public int leftIdxOf(int i) {
        return (2 * i) + 1;

    }

    /**
     * @requires 0 <= i < contents.size()
     * @ensures the index of i's right child is returned
     */
    public int rightIdxOf(int i) {
        return (2 * i) + 2;

    }

    /**
     * @requires 0 <= i < contents.size()
     * @ensures the index of i's parent is returned
     */
    private int parentIdxOf(int i) {
        return (i - 1) / 2;

    }

    /**
     * Swaps the contents of the heap at indices {@code index1} and
     * {@code index2}.
     *
     * @requires 0 <= index1, index2 < heap.size()
     * @ensures the same heap as the input heap, but with the elements stored
     * at index1 and index2 swapped.
     *
     * @param index1 the index of the first element to swap
     * @param index2 the index of the second element to swap
     */
    private void swapElementsAt(int index1, int index2) {
        if (index1 >= 0 && index2 < heap.size()) {
            Collections.swap(this.heap, index1, index2);
        } else {
            throw new IllegalArgumentException("violates requires clause");
        }
    }

    /**
     * Adds an element {@code e} of type {@code T} to {@code this} sortinator.
     *
     * @ensures {@code this} sortinator's multiset has added element {@code e}
     * @param e The element to be added
     * @throws IllegalStateException if the client attempts to add an element
     *                       while the sortinator is not accepting new elements
     */
    @Override
    public void add(E e) throws IllegalStateException {
        if (!this.accepting){
            throw new IllegalStateException("requires clause violation, machine not accepting");
        }
        this.heap.add(e);
        int pos = heap.size() -1;
        while (pos > 0 && order.compare(heap.get(parentIdxOf(pos)), heap.get(pos)) > 0) {
            swapElementsAt(pos, parentIdxOf(pos));
            pos = parentIdxOf(pos);
        }
    }

    /**
     * Trickles the element located at index {@code i} 'down' into
     * the correct position within the heap such that the heap rooted at index
     * {@code i} obeys the heap ordering property.
     * <p>
     * Note that this internal helper method mutates {@code this} object's
     * {@code heap} list.
     *
     * @requires [the left subtree of the tree rooted at i obeys the heap property] and
     *  [the right subtree of the tree rooted at i obeys the heap property]
     * @ensures
     * [the tree rooted at i obeys the heap property]
     */
    private void heapify(int i) {
        int idxOfSmallest = i;
        int left = leftIdxOf(i);
        int right = rightIdxOf(i);
        int heapSize = this.heap.size();

        if (heapSize > 2 && left < heapSize && right < heapSize && order.compare(heap.get(left), heap.get(right)) < 0) {
            idxOfSmallest = left;
        }
        if (heapSize > 2 && right < heapSize && order.compare(heap.get(right), heap.get(idxOfSmallest)) < 0) {
            idxOfSmallest = right;
        }
        if(heapSize == 2 && order.compare(heap.get(0), heap.get(1)) > 0){
            swapElementsAt(0, 1);
        }
        if (idxOfSmallest != i) {
            swapElementsAt(i, idxOfSmallest);
            heapify(idxOfSmallest);
        }
    }

    /**
     * Removes and returns some 'smallest' T from the contents of {@code this}
     * sortinator.
     *
     * @requires {@code this} sortinator's multiset {@code m} is not empty
     * @ensures foreach element X of type {@code T} stored in {@code this}
     *            sortinator (immediately prior to the invocation of this
     *            method), the return value of removeSmallest() is
     *            'smaller-than' X.
     *
     * @return the 'smallest' element
     * @throws NoSuchElementException if the sortinator has no more elements
     * @throws IllegalStateException if the sortinator is not in extraction
     *                               state
     */
    @Override
    public E removeSmallest() throws NoSuchElementException, IllegalStateException{
        if (this.accepting) {
            throw new IllegalStateException("requires");
        }
        if (this.heap.size() == 0) {
            throw new IllegalStateException("requires");
        }
        E smallest = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapify(0);
        return smallest;

    }

    /**
     * Switches the sortinator into insertion or extraction state (depending on
     * the current/existing state of {@code this} sortinator object).
     *
     * @ensures the machine is switched to the opposite state of its current
     *          operating state
     */
    @Override
    public void switchState() { accepting = !accepting; }

    /**
     * Returns the {@link Comparator<E>} for {@code this} sortinator object.
     */
    @Override
    public Comparator<E> order() { return this.order; }

    /**
     * Returns the number of elements in {@code this} sortinator.
     *
     * @ensures elementCount = the number of elements stored in {@code this}
     *              sortinator's multiset.
     *
     * @return the number of elements
     */
    @Override
    public int elementCount() { return heap.size(); }

    /**
     * Returns {@code true} <strong>iff</strong> {@code this} sortinator is
     * accepting new elements.
     *
     * @ensures acceptingElements = {@code this} sortinator's accepting flag
     *
     * @return whether {@code this} sortinator is accepting new elements
     */
    @Override
    public boolean acceptingElements() { return this.accepting; }

    /**
     * Returns the heap in a string format so that it can be printed to the console
     *
     * @return Heap in a string format
     */
    @Override
    public String toString(){
        String heapString = "heap:";
        int i = 0;

        while(i < heap.size()){
            heapString = heapString + " " + (heap.get(i));
            i += 1;
        }
        return heapString;
    }

}

