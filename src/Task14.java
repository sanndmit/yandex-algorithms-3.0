import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Task14 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        Integer n = Integer.parseInt(reader.readLine());
        String[] wagons = reader.readLine().split(" ");
        reader.close();

        Stack<Integer> deadend = new Stack<>();
        Integer next = Integer.valueOf(1);
        for (int i = 0; i < n; i++) {

            deadend.push(Integer.parseInt(wagons[i]));
            while (!deadend.isEmpty() && deadend.peek().equals(next))
                next = deadend.pop() + 1;

        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write((deadend.isEmpty()) ? "YES" : "NO");
        writer.close();

    }
    
}
