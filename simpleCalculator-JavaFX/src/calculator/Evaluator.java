package calculator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Michael on 27/06/2017.
 */
public class Evaluator {

    private static final HashMap<Character, Integer> PRECEDENCE;

    static {
        PRECEDENCE = new HashMap<>();
        PRECEDENCE.put('+', 0);
        PRECEDENCE.put('-', 0);
        PRECEDENCE.put('*', 1);
        PRECEDENCE.put('/', 1);
    }

    /**
     * Evaluates using the Shunting-yard algorithm.
     * @param line
     * @return
     */
    public static String evaluate(String line) {
        if (line.length() == 0)
            return "";

        Stack<Character> operators = new Stack<>();
        LinkedList<String> output = new LinkedList<>();

        int start = 0;
        while (line.length() != start) {
            if (PRECEDENCE.containsKey(line.charAt(start))) {
                while (!operators.isEmpty() && PRECEDENCE.get(operators.peek()) >= PRECEDENCE.get(line.charAt(start))) {
                    output.add(String.valueOf(operators.pop()));
                }
                operators.push(line.charAt(start));
                start++;
            } else {
                int i = 0;
                while (start + i < line.length() && !PRECEDENCE.containsKey(line.charAt(start + i)))
                    i++;
                output.add(line.substring(start,start + i));
                System.out.println("Added1 " + line.substring(start,start + i));
                start += i;
            }
            // No brackets in this iteration
        }

        // Push remainder operators onto the output queue
        while (!operators.isEmpty()) {
            String item = String.valueOf(operators.pop());
            output.add(item);
            System.out.println("Added 2" + item);

        }

        // Start evaluation
        Stack<String> numbers = new Stack<>();
        while (!output.isEmpty()) {
            String popped = output.poll();
            System.out.println("Popped " + popped);
            if (isOperator(popped)) {
                // Do operator stuff
                String a;
                String b;
                try {
                    a = numbers.pop();
                    b = numbers.pop();
                } catch (Exception e) {
                    // Some kind of error
                    // Should never happen
                    return "Non-matching arithmetic";
                }
                try {
                    numbers.push(operate(popped, a, b));
                } catch (NumberFormatException e) {
                    return "Error";
                }
            } else {
                // Add to numbers queue
                numbers.push(popped);
                System.out.println("Pushed onto stack: " + popped);
            }
        }

        return numbers.pop();
    }


    private static boolean isOperator(String s) {
        char c = s.charAt(0);
        return PRECEDENCE.containsKey(c);
    }

    private static String operate(String op, String a, String b) throws NumberFormatException {
        String ans = null;
        double num;
        switch (op) {
            case "+":
                num = Double.valueOf(a) + Double.valueOf(b);
                ans = String.valueOf(num);
                break;
            case "-":
                num = Double.valueOf(b) - Double.valueOf(a);
                ans = String.valueOf(num);
                break;
            case "*":
                num = Double.valueOf(a) * Double.valueOf(b);
                ans = String.valueOf(num);
                break;
            case "/":
                num = Double.valueOf(b) / Double.valueOf(a);
                ans = String.valueOf(num);
                break;
        }
        return ans;
    }


}
