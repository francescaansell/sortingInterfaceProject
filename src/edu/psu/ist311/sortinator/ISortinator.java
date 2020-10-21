package edu.psu.ist311.sortinator;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * ISortinator interface with core (informally specified) method signatures.
 * <p>
 * Conceptually speaking, we can think of a sortinator object as a pair
 * containing:
 * <ul>
 *   <li>a multiset: m (a multiset is like an ordinary mathematical set --
 *                      but one that allows duplicate elements)
 *   <li>a boolean flag: accepting
 * </ul>
 * When the sortinator starts up (initializes), the accepting flag is set to
 * {@code true} and its multiset m is empty.
 * <p>
 * The sortinator itself operates in two states: insertion and extraction. In
 * the insertion state, new elements of type {@code T} can be added to the
 * sortinator via calls to {@link #add}. Note that there is no guarantee
 * (at the interface level) that calls to add necessarily store the elements in
 * sorted order -- it is left up to the implementation where/when the sorting
 * occurs.
 * <p>
 * Elements may only be removed when the sortinator is operating in extraction
 * mode. A call to {@link #switchState()} changes the accepting flag to
 * {@code false} and only then can 'min' elements be removed via calls to
 * {@link #removeSmallest()} (where the 'min' element is determined by a
 * {@link Comparator} supplied to a given implementation's constructor).
 * <p>
 * New elements may only be inserted into the sortinator when the sortinator is
 * operating in insertion mode.
 *
 * @param <T> the type of elements stored in the sortinator
 */
public interface ISortinator<T> {

    /**
     * Adds an element {@code e} of type {@code T} to {@code this} sortinator.
     *
     * @ensures {@code this} sortinator's multiset has added element {@code e}
     * @param e The element to be added
     * @throws IllegalStateException if the client attempts to add an element
     *                       while the sortinator is not accepting new elements
     */
    void add(T e) throws IllegalStateException;

    /**
     * Returns the {@link Comparator<T>} for {@code this} sortinator object.
     */
    Comparator<T> order();

    /**
     * Switches the sortinator into insertion or extraction state (depending on
     * the current/existing state of {@code this} sortinator object).
     *
     * @ensures the machine is switched to the opposite state of its current
     *          operating state
     */
    void switchState();

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
    T removeSmallest() throws NoSuchElementException, IllegalStateException;

    /**
     * Returns the number of elements in {@code this} sortinator.
     *
     * @ensures elementCount = the number of elements stored in {@code this}
     *              sortinator's multiset.
     *
     * @return the number of elements
     */
    int elementCount();

    /**
     * Returns {@code true} <strong>iff</strong> {@code this} sortinator is
     * accepting new elements.
     *
     * @ensures acceptingElements = {@code this} sortinator's accepting flag
     *
     * @return whether {@code this} sortinator is accepting new elements
     */
    boolean acceptingElements();


}
