/* Name: Brandon
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * Unique.java
 */

package hw1;

/** A class with a main method for printing out unique numbers. */
public final class Unique {

    /** Make checkstyle happy. */
    private Unique() {
        throw new AssertionError("Can not instantiate class Unique\n");
    }

    /**
     * A main method to print the unique numerical command line arguments.
     * @param args The string array of arguments in the command line.
     * @throws IllegalArgumentException if command line argument is invalid.
     */
    public static void main(String[] args) throws IllegalArgumentException {
        // Limits upper bound on number of command line arguments
        final int maxArgs = 255;
        
        if (args.length > maxArgs) {
            throw new IllegalArgumentException("Too many arguments\n");
        }

        // Contains unique numbers.
        int[] uniq = new int[args.length];
        // Tracks size of uniq.
        int uniqSize = 0;
        
        for (int i = 0; i < args.length; ++i) {   
            // Checks if command line argument is an integer.
            int tmp;
            try {
                tmp = Integer.parseInt(args[i]);
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("Not an integer\n");
            }
            
            // Checks if args[i] has been seen before
            boolean isUnique = true;
            for (int j = 0; j < uniqSize; ++j) {
                if (tmp == uniq[j]) {
                    isUnique = false;
                    break;
                }
            }
            // If args[i] has not been seen, add it to uniq
            if (isUnique) {
                uniq[uniqSize] = tmp;
                uniqSize++;
            }
        }

        for (int i = 0; i < uniqSize; ++i) {
            System.out.println(uniq[i]);
        }
    }
}