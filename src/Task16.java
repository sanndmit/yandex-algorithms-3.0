import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Task16 {
    
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        Queue<Integer> queue = new LinkedList<>();
        boolean quit = false;
        while (!quit) {

            String line = reader.readLine();
            if (line == null)
                break;

            String[] command = line.split(" ");
            String reply = "";
            
            if (command[0].equals("push")) {
                queue.add(Integer.valueOf(command[1]));
                reply = "ok";

            } else if (command[0].equals("pop")) {
                reply = (queue.isEmpty()) ? "error" : String.valueOf(queue.remove());

            } else if (command[0].equals("front")) {
                reply = (queue.isEmpty()) ? "error" : String.valueOf(queue.peek());

            } else if (command[0].equals("size")) {
                reply = String.valueOf(queue.size());

            } else if (command[0].equals("clear")) {
                queue.clear();
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
