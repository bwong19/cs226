/* BubbleSort.java */

package hw3;

import hw2.Array;

/**
 * The Bubble Sort algorithm with the optimized "quick" break to exit
 * if the array is sorted.
 * @param <T> The type being sorted.
 */
public final class BubbleSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

    @Override
    public void sort(Array<T> array) {
        // TODO
    }

    @Override
    public String name() {
        return "Bubble Sort";
    }
}
