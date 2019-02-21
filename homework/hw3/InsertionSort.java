/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * InsertionSort.java
 */

package hw3;

import hw2.Array;


/**
 * The Insertion Sort algorithm.
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {


    @Override
    public void sort(Array<T> array) {
        for (int i = 1; i < array.length(); ++i) {
            // For every element in the array, we place it in order.
            // We can start with i = 1 because the first element is
            // sorted by default.
            for (int j = 0; j < i; ++j) {
                if (array.get(j).compareTo(array.get(i)) > 0) {
                    T temp = array.get(j);
                    array.put(j, array.get(i));
                    array.put(i, temp);
                }
            }
        }
    }

    @Override
    public String name() {
        return "Insertion Sort";
    }
}
