import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class Task18 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        Deque<Integer> deque = new LinkedList<>();
        boolean quit = false;
        while (!quit) {

            String line = reader.readLine();
            if (line == null)
                break;

            String[] command = line.split(" ");
            String reply = "";

            if (command[0].equals("push_front")) {
                deque.addFirst(Integer.valueOf(command[1]));
                reply = "ok";

            } else if (command[0].equals("push_back")) {
                deque.addLast(Integer.valueOf(command[1]));
                reply = "ok";

            } else if (command[0].equals("pop_front")) {
                reply = (deque.isEmpty()) ? "error" : String.valueOf(deque.removeFirst());

            } else if (command[0].equals("pop_back")) {
                reply = (deque.isEmpty()) ? "error" : String.valueOf(deque.removeLast());

            } else if (command[0].equals("front")) {
                reply = (deque.isEmpty()) ? "error" : String.valueOf(deque.peekFirst());

            } else if (command[0].equals("back")) {
                reply = (deque.isEmpty()) ? "error" : String.valueOf(deque.peekLast());

            } else if (command[0].equals("size")) {
                reply = String.valueOf(deque.size());

            } else if (command[0].equals("clear")) {
                deque.clear();
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
