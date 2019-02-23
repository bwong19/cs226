// Name: Brandon Wong
// JHED: bwong19
// Email: bwong19@jhu.edu
// Calc.java


package hw4;

import java.util.Scanner;

/** A program for an RPN calculator that uses a stack. */
public final class Calc {

    // Hush checkstyle
    private Calc() {}

    /**
     * The main function.
     * @param args Not used.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Stack<Integer> nums = new ArrayStack<>();

        // input
        boolean endLoop = false;
        while (sc.hasNext() && !endLoop) {
            // creates stack if next token is an integer
            if (sc.hasNextInt()) {
                nums.push(sc.nextInt());
            }
            // TODO: resolve the case if argument is of the form "?blah"
            else {
                char ch = sc.next();
                switch (ch) {
                case '?': {
                    System.out.println(nums);
                }
                case '.': {
                    int n = nums.top();
                    nums.pop();
                }
                case '!': {
                    endLoop = true;
                }
                case '+': {
                    int result;
                    int r, s;
                    r = nums.top();
                    nums.pop();
                    s = nums.top();
                    nums.pop();
                    result = r + s;
                    nums.push(result);
                }
                case '-': {
                    int result;
                    int r, s;
                    r = nums.top();
                    nums.pop();
                    s = nums.top();
                    nums.pop();
                    result = r - s;
                    nums.push(result);
                }
                case '*': {
                    int result;
                    int r, s;
                    r = nums.top();
                    nums.pop();
                    s = nums.top();
                    nums.pop();
                    results = r * s;
                    nums.push(result);
                }
                case '/': {
                }
                default: {
                    
                }
                }
            }
        }
    }
}
