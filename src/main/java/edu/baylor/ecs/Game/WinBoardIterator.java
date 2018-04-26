package edu.baylor.ecs.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import static edu.baylor.ecs.Game.SinglePlayer.SIZE_OF_BOARD;

/** Author: Brandon Mork. */
public class WinBoardIterator implements Iterable {

    /** A temporary 2D array of WinnerTiles.
     */
    private WinnerTile[][] temp;

    /** the maximum number of elements to iterate over.
     */
    private int numElements;

    /** the current element.
     */
    private int index;

    /** An ArrayList of WinnerTiles.
     */
    private ArrayList<WinnerTile> arrayList = new ArrayList<>();

    /** Author: Brandon Mork.
     * @param board This is the board we will iterate over */
    public WinBoardIterator(final WinnerTile[][] board) {
        this.temp = board;
        this.index = 0;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                arrayList.add(temp[i][j]);
                numElements++;
            }
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return if there is another WinnerTile.
     */
    public final boolean hasNext() {
        return index < numElements;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return the WinnerTile that we are iterating over.
     */
    public final WinnerTile next() {
        return arrayList.get(index++);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public final Iterator iterator() {
        return null;
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Unless otherwise specified by the implementing class,
     * actions are performed in the order of iteration (if an iteration order
     * is specified).  Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public final void forEach(final Consumer action) {
    }

    /**
     * Creates a {@link Spliterator} over the elements described by this
     * {@code Iterable}.
     *
     * @return a {@code Spliterator} over the elements described by this
     * {@code Iterable}.
     * @implSpec The default implementation creates an
     * <em><a href="Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator
     * inherits the <em>fail-fast</em> properties of the iterable's iterator.
     * @implNote The default implementation should usually be overridden.  The
     * spliterator returned by the default implementation has poor splitting
     * capabilities, is unsized, and does not report any spliterator
     * characteristics. Implementing classes can nearly always provide a
     * better implementation.
     * @since 1.8
     */
    @Override
    public final Spliterator spliterator() {
        return null;
    }


}
