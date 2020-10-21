package edu.psu.ist311.sortinator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ListImpl<E> implements ISortinator<E> {


    private ArrayList<E> list;
    private final Comparator<E> order;
    private Boolean accepting;


    public ListImpl(Comparator<E> order) {
        this.list = new ArrayList<>();
        this.order = order;
        this.accepting = true;
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
        if (!this.accepting) {
            throw new IllegalStateException("requires clause violation, machine not operating in accepting modes");
        }
        this.list.add(e);
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
    private void swapElementsAt(int index1, int index2) { Collections.swap(this.list, index1, index2); }

    /**
     * Uses bubbleSort to sort the list
     *
     * @ensures bubbleSort is called in the switchStateMethod
     * no requires clause because errors are located in the switchState method
     */
    private void bubbleSort() {
        int i;
        int j;
        int n = this.list.size();
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (order.compare(this.list.get(j), this.list.get(j + 1)) > 0) {
                    swapElementsAt(j, j + 1);
                }
            }
        }
    }

    /**
     * Removes and returns some 'smallest' E from the contents of {@code this}
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
    public E removeSmallest() throws NoSuchElementException, IllegalStateException {
        if (!this.accepting){
            if (this.list.size() != 0){
                E smallest = this.list.get(0);
                this.list.remove(0);
                return smallest;
            } else { throw new NoSuchElementException("violates requires clause"); }
        } else { throw new IllegalStateException("violates requires clause"); }
    }

    /**
     * Switches the sortinator into insertion or extraction state (depending on
     * the current/existing state of {@code this} sortinator object).
     *
     * @ensures the machine is switched to the opposite state of its current
     *          operating state
     */
    @Override
    public void switchState() {
        if (accepting) {
            bubbleSort();
        }
        this.accepting = !this.accepting;
    }

    /**
     * Returns the {@link Comparator<E>} for {@code this} sortinator object.
     */
    @Override
    public Comparator<E> order() {
        return this.order;
    }

    /**
     * Returns the number of elements in {@code this} sortinator.
     *
     * @ensures elementCount = the number of elements stored in {@code this}
     *              sortinator's multiset.
     *
     * @return the number of elements
     */
    @Override
    public int elementCount() {
        return this.list.size();
    }

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
     * Returns the list in a string format so that it can be printed to the console
     *
     * @return Heap in a string format
     */
    @Override
    public String toString() {
        String listString = "list:";
        int i = 0;

        while (i < this.list.size()) {
            listString = listString + " " + (this.list.get(i));
            i += 1;
        }
        return listString;
    }

}
