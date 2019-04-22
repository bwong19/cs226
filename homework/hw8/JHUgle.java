package hw8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;

/**
 * Takes in a file of keywords and URLs. Works like Google.
 */
public final class JHUgle {

    private JHUgle() {}

    /**
     * Takes a file as input and allows operations to be performed
     * on keywords and URLs.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args[0] == null) {
            System.err.println("Invalid number of arguments.");
            return;
        }
        File file = new File(args[0]);
        Scanner fsc;
        try {
            fsc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file.");
            return;
        }

        Map<String, List<String>> hash = new HashMap<>();

        // Creates map from keyword to URLs
        while (fsc.hasNext()) {
            String url = fsc.nextLine();
            String keywordList = fsc.nextLine();
            String[] keywords = keywordList.split("\\s+");

            // For each keyword, we insert a URL in the following manner:
            // 1. Get the current list of URLs (or make one if doesn't exist
            // 2. Append new URL to list of URLs
            // 3. Insert URL list back into map
            hash = createIndex(hash, url, keywords);
        }

        System.out.println("Index Created");

        // User input/query stack
        Scanner stdin = new Scanner(System.in);
        // Stack of URL lists
        Stack<List<String>> processed = new Stack<>();

        System.out.print("> ");
        while (stdin.hasNext()) {
            String word = stdin.next();
            if (word.compareTo("?") == 0 && processed.empty()) {
                System.err.println("Too few arguments.");
                System.out.print("> ");
                continue;
            }

            else if (isAndOr(word)) {
                if (processed.size() < 2) {
                    System.err.println("Too few arguments.");
                    System.out.print("> ");
                    continue;
                }
            }

            else if (word.compareTo("!") == 0) {
                return;
            }

            processed = process(word, processed, hash);

            System.out.print("> ");
        }
    }

    // creates index for one url/keywords pair
    private static Map<String, List<String>> createIndex(Map<String,
            List<String>> hash, String url, String[] keywords) {
        for (int i = 0; i < keywords.length; ++i) {
            List<String> urlList = new ArrayList<>();

            if (hash.has(keywords[i])) {
                urlList = hash.get(keywords[i]);
                urlList.add(url);
                hash.put(keywords[i], urlList);
                continue;
            }
            urlList.add(url);
            hash.insert(keywords[i], urlList);
        }
        return hash;
    }

    // processes a query and performs according operations
    private static Stack<List<String>> process(String word,
            Stack<List<String>> processed, Map<String, List<String>> hash) {

        if (word.compareTo("?") == 0) {
            printURLs(processed.peek());
        }

        else if (isAndOr(word)) {
            List<String> list1 = processed.pop();
            List<String> list2 = processed.pop();

            if (word.compareTo("&&") == 0) {
                processed.push(and(list1, list2));
            } else {
                processed.push(or(list1, list2));
            }
        }

        else if (hash.has(word)) {
            processed.push(hash.get(word));
        }

        else {
            System.err.println("Bad token.");
        }

        return processed;
    }

    // determines if an operation is && or ||
    private static boolean isAndOr(String word) {
        return word.compareTo("&&") == 0 || word.compareTo("||") == 0;
    }

    // return the intersection of two lists
    private static List<String> and(List<String> list1, List<String> list2) {
        List<String> intersection = new ArrayList<>();
        for (String s1: list1) {
            for (String s2: list2) {
                if (s1.compareTo(s2) == 0) {
                    intersection.add(s1);
                }
            }
        }
        return intersection;
    }

    // returns the union of two lists
    private static List<String> or(List<String> list1, List<String> list2) {
        List<String> union = new ArrayList<>();
        for (String s1 : list1) {
            union.add(s1);
        }
        for (String s2 : list2) {
            boolean appears = false;
            for (String s1 : list1) {
                if (s1.compareTo(s2) == 0) {
                    appears = true;
                    break;
                }
            }
            if (appears) {
                continue;
            }
            union.add(s2);
        }

        return union;
    }

    // prints a list of URLs
    private static void printURLs(List<String> urls) {
        for (String s: urls) {
            System.out.println(s);
        }
    }

}