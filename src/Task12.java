import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Task12 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String str = reader.readLine();
        reader.close();

        boolean correct = true;
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {

            if (isOpening(c))
                stack.add(c);

            else if (stack.isEmpty() || !isClosing(c) || stack.pop() != getClosing(c)) {
                correct = false;
                break;
            }

        }
        if (correct)
            correct = stack.isEmpty();

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf((correct) ? "yes" : "no"));
        writer.close();

    }

    private static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static Character getClosing(char c) {

        if (c == ')')
            return '(';
        else if (c == ']')
            return '[';
        else if (c == '}')
            return '{';

        return ' ';
    }

}
