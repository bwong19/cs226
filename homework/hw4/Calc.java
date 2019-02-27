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
     * Processes the next token according to its value.
     * @param token The token that is being processed.
     * @param numbers The integer stack of numbers.
     * @return true to continue loop, false to break loop.
     */
    private static boolean processToken(String token, Stack<Integer> numbers) {
        if (token.length() > 1) {
            System.err.println("ERROR: bad token");
            return true;
        }

        // reads only if user inputs '?', '.', or '!' and acts accordingly
        char ch = token.charAt(0);
        switch (ch) {
            case '?':
                System.out.println(numbers);
                break;
            case '.':
                if (!numbers.empty()) {
                    int t = numbers.top();
                    numbers.pop();
                    System.out.println(t);
                }
                else {
                    System.err.println("ERROR: empty stack");
                }
                break;
            case '!':
                return false;
            default:
                if (isOper(ch)) {
                    calculate(ch, numbers);
                }
                else {
                    System.err.println("ERROR: bad token");
                }
                break;
        }

        return true;
    }

    /**
     * Checks if argument is an operation.
     * @param ch Character to be checked.
     * @return true if character is an operation, false if not
     */
    private static boolean isOper(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%');
    }

    /**
     * Calculates and puts result in stack.
     * @param op Operator.
     * @param nums Stack of numbers.
     */
    private static void calculate(char op, Stack<Integer> nums) {
        int r;
        int s;

        // only operates if there are 2 objects in the stack
        if (!nums.empty()) {
            r = nums.top();
            nums.pop();
        }
        else {
            System.err.println("ERROR: not enough arguments");
            return;
        }
        if (!nums.empty()) {
            s = nums.top();
            nums.pop();
        }
        else {
            // have to put back r if nothing happens
            nums.push(r);
            System.err.println("ERROR: not enough arguments");
            return;
        }


        switch (op) {
            case '+':
                nums.push(s + r);
                break;
            case '-':
                nums.push(s - r);
                break;
            case '*':
                nums.push(s * r);
                break;
            case '/':
                if (r == 0) {
                    System.err.println("ERROR: divide by 0");
                    nums.push(s);
                    nums.push(r);
                    break;
                }
                nums.push(s / r);
                break;
            case '%':
                if (r == 0) {
                    System.err.println("ERROR: remainder by 0");
                    nums.push(s);
                    nums.push(r);
                    break;
                }
                nums.push(s % r);
                break;
            default:
                System.err.println("ERROR: bad token");
                nums.push(s);
                nums.push(r);
                break;
        }
    }

    /**
     * The main function.
     * @param args Not used.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Stack<Integer> nums = new ArrayStack<>();
        
        // input

        // command-line arguments
        for (int i = 0; i < args.length; ++i) {
            try {
                int t = Integer.parseInt(args[i]);
                nums.push(t);
            } 
            catch (NumberFormatException e) {
                if (!processToken(args[i], nums)) {
                    break;
                }
            }
        }
        
        // standard input arguments
        while (sc.hasNext()) {
            // creates stack if next token is an integer
            if (sc.hasNextInt()) {
                nums.push(sc.nextInt());
            }
            else {
                String nxt = sc.next();
                //System.out.println(nxt);

                if (!processToken(nxt, nums)) {
                    break;
                }

            }
        }
    }
}
