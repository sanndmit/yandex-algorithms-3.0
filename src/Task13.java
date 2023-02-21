import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Task13 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] str = reader.readLine().split(" ");
        reader.close();

        Stack<Integer> stack = new Stack<>();
        for (String s : str) {

            if (s.equals("+")) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.add(left + right);
            } else if (s.equals("-")) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.add(left - right);
            } else if (s.equals("*")) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.add(left * right);
            } else
                stack.add(Integer.parseInt(s));

        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(stack.pop()));
        writer.close();

    }

}
