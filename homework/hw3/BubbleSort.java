/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * BubbleSort.java
 */

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
        for (int i = 0; i < array.length(); ++i) {
            // swapped determines the quick break
            boolean swapped = false;
            for (int j = 0; j < array.length() - 1; ++j) {
                // if out of order, swap the two values
                if (array.get(j).compareTo(array.get(j + 1)) > 0) {
                    T temp = array.get(j);
                    array.put(j, array.get(j + 1));
                    array.put(j + 1, temp);
                    swapped = true;
                }
            }
            // quick break if no swaps were performed
            if (!swapped) {
                break;
            }

        }
    }

    @Override
    public String name() {
        return "Bubble Sort";
    }
}
