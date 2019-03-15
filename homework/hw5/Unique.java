package hw5;

import java.util.Scanner;

/**
 * Filter unique integers from standard input to standard output.
 *
 * If you're benchmarking this program, you may want to suppress
 * the output by redirecting it to /dev/null.
 */
public final class Unique {
    private static Set<Integer> data;

    // Make checkstyle happy.
    private Unique() {}

    /**
     * Generates n-long random number array.
     * Values range from 0 to n - 1.
     * @param n The desired length of the array.
     * @return array that is created.
     */
    private static int[] rand(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = (int) (Math.random() * n);
        }
        return arr;
    }

    /**
     * Generates n-long ascending number array.
     * Values range from 0 to n - 1.
     * @param n The desired length of the array.
     * @return array that is created.
     */
    private static int[] asc(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * Generates n-long descending number array.
     * Values range from 0 to n - 1.
     * @param n The desired length of the array.
     * @return array that is created.
     */
    private static int[] desc(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = n - 1 - i;
        }
        return arr;
    }

    /**
     *  Main method.
     *  @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        data = new MoveToFrontListSet<Integer>();
        /*
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            data.insert(i);
        }
        */
        int size = 1000000;
        int[] arr = rand(size);

        for (int i = 0; i < size; ++i) {
            data.insert(arr[i] % 10000);
        }

        int c = 0;
        for (Integer i: data) {
            //System.out.println(i);
            c++;
        }
        System.out.println("unique: " + c);
    }
}
