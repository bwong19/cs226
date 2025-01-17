package hw7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Count unique words.
 *
 * Read text from standard input, count how often each unique word appears,
 * write the results to standard output. Note that the definiton of "word"
 * is rather arbitrary and won't make the linguists among you very happy.
 */
public final class Words {
    private static Map<String, Integer> data;

    // Make checkstyle happy.
    private Words() {}

    /**
     * Main method.
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) throws FileNotFoundException {
        data = new SimpleMap<String, Integer>();
        File file = new File("lorem100000.in");
        Scanner scanner = new Scanner(System.in);
        scanner = new Scanner(file);

        long start = System.currentTimeMillis();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            // The regular expression splits strings on whitespace and
            // non-word characters (anything except [a-zA-Z_0-9]). Far
            // from perfect, but close enough for this simple program.
            String[] words = s.split("[\\s\\W]+");
            for (String word: words) {
                if (word.length() <= 1) {
                    // Skip "short" words, most of which just "dirty up"
                    // the statistics.
                    continue;
                }
                if (data.has(word)) {
                    data.put(word, data.get(word) + 1);
                } else {
                    data.insert(word, 1);
                }
            }
        }

        for (String word: data) {
            System.out.println(word + ": " + data.get(word));
        }

        long end = System.currentTimeMillis();

        double time = (end - start) / (double) 1000;

        System.out.println();
        System.out.println("time: " + time + " seconds");
    }
}
