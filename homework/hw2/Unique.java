/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * Unique.java
 */

package hw2;

import java.util.Scanner;

/** Unique problem using a SparseArray and processing from standard in. */
public final class Unique {

    // make checkstyle happy
    private Unique() {}


    /**
     * Doubles the capacity of an array.
     * @param arr The array that needs to be doubled.
     * @return the new array.
     */
    public static SimpleArray<Integer> doubleCap(SimpleArray<Integer> arr) {
        SimpleArray<Integer> tmp = new
            SimpleArray<Integer>(arr.length() * 2, 0);
        for (int i = 0; i < arr.length(); ++i) {
            tmp.put(i, arr.get(i));
        }
        return tmp;
    }

    /**
     * Print only unique integers out of entered numbers.
     * @param args Command line arguments.
     * @throws IllegalArgumentException if user enters incorrect input.
     */
    public static void main(String[] args) throws IllegalArgumentException {

        // INPUT HANDLING
        Scanner sc = new Scanner(System.in);

        SimpleArray<Integer> values = new SimpleArray<Integer>(1, 0);
        int valuesLength = 0; // keeps track of how many entries are used
        
        boolean badInput = false; // checks if an int is not inputted
        while (sc.hasNext()) {
            // if out of space in array, double the length
            if (valuesLength >= values.length()) {
                values = doubleCap(values);
            }

            try {
                values.put(valuesLength, sc.nextInt());
                valuesLength++;
            }
            catch (java.util.InputMismatchException e) {
                sc.nextLine();
                badInput = true;
                continue;
            }
        }
        
        if (badInput) {            
            throw new IllegalArgumentException("Not an integer\n");
        }

        
        // UNIQUE ALGORITHM
        SimpleArray<Integer> uniq = new SimpleArray<Integer>(1, 0);
        int uniqLength = 0; // keeps track of how many entries are used

        // Searches in place for unique integers
        for (int i = 0; i < valuesLength; ++i) {
            boolean isUniq = true;
            for (int j = 0; j < i; ++j) {
                if (values.get(i) == values.get(j)) {
                    isUniq = false;
                    break;
                }
            }
            if (isUniq) {
                System.out.println(values.get(i));
            }
        }
    }
}
