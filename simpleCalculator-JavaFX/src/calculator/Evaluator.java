package calculator;


import java.util.*;

/**
 * Created by Michael on 27/06/2017.
 */
public class Evaluator {

    private static final char[] OPERATORS = {'+', '-', '*', '/'};
    private static final ArrayList<Character> ops;
    private LinkedList<String> numbers;
//    private Stack<String> operators;
    private static final HashMap<String, Integer> PRECEDENCE;
    static {
        PRECEDENCE = new HashMap<>();
        PRECEDENCE.put("+", 0);
        PRECEDENCE.put("-", 0);
        PRECEDENCE.put("*", 1);
        PRECEDENCE.put("/", 1);

        ops = new ArrayList<>();
        ops.add('+');
        ops.add('-');
        ops.add('*');
        ops.add('/');
    }



    public Evaluator() {
//        numbers = new LinkedList<>();
//        operators = new Stack<>();
//        output = new LinkedList<>();
    }

    /**
     * Evaluates using the Shunting-yard algorithm.
     * @param line
     * @return
     */
    public static String evaluate(String line) {
        if (line.length() == 0)
            return "";
        Stack<String> operators = new Stack<>();
        LinkedList<String> output = new LinkedList<>();
//        String read;

        int start = 0;
        while (line.length() != start) {
            if (ops.contains(line.charAt(start))) {
                while (!operators.isEmpty() && PRECEDENCE.get(operators.peek()) >= PRECEDENCE.get(line.substring(start,start + 1))) {
                    output.add(String.valueOf(operators.pop()));
                }
                operators.push(line.substring(start,start+1));
                start++;
            } else {
                int i = 0;
                while (start + i < line.length() && !ops.contains(line.charAt(start + i)))
                    i++;
                output.add(line.substring(start,start + i));
                System.out.println("Added " + line.substring(start,start + i));
                start += i;
            }
            // No brackets in this iteration

        }

        // Push remainder operators onto the output queue
        while (!operators.isEmpty()) {
            String item = operators.pop();
            output.add(String.valueOf(item));
            System.out.println("Added " + item);

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

        // Should only have 1 number
        /*
        System.out.println(numbers.size());
        if (numbers.size() != 1) {
            return "Non-matching arithmetic2";
        } else {
            return numbers.pop();
        }
        */
        return numbers.pop();


    }

//    public boolean isOperator(char c) {
//        return PRECEDENCE.containsKey(c);
//    }

    public static boolean isOperator(String s) {
//        char c = s.charAt(0);
        return PRECEDENCE.containsKey(s);
    }

    private static String operate(String op, String a, String b) throws NumberFormatException {
        String ans = null;
        double num;
        if (op.equals("+")) {
            num = Double.valueOf(a) + Double.valueOf(b);
            ans = String.valueOf(num);
        } else if (op.equals("-")) {
            num = Double.valueOf(b) - Double.valueOf(a);
            ans = String.valueOf(num);
        } else if (op.equals("*")) {
            num = Double.valueOf(a) * Double.valueOf(b);
            ans = String.valueOf(num);
        } else if (op.equals("/")) {
            num = Double.valueOf(b) / Double.valueOf(a);
            ans = String.valueOf(num);
        }
        return ans;
    }


}
