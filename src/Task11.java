import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Task11 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        Stack<Integer> stack = new Stack<>();
        boolean quit = false;
        while (!quit) {

            String line = reader.readLine();
            if (line == null)
                break;

            String[] command = line.split(" ");
            String reply = "";
            if (command[0].equals("push")) {
                stack.add(Integer.valueOf(command[1]));
                reply = "ok";

            } else if (command[0].equals("pop")) {
                reply = (stack.isEmpty()) ? "error" : String.valueOf(stack.pop());

            } else if (command[0].equals("back")) {
                reply = (stack.isEmpty()) ? "error" : String.valueOf(stack.peek());

            } else if (command[0].equals("size")) {
                reply = String.valueOf(stack.size());

            } else if (command[0].equals("clear")) {
                stack.clear();
                reply = "ok";

            } else if (command[0].equals("exit")) {
                quit = true;
                reply = "bye";

            }
            writer.write(reply + "\n");

        }
        reader.close();
        writer.close();

    }

}
