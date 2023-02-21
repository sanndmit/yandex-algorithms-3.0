import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Stack;

public class Task15 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        Integer n = Integer.parseInt(reader.readLine());
        String[] cities = reader.readLine().split(" ");
        reader.close();

        int[] result = new int[n];
        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
        for (int i = 0; i < n; i++) {

            Integer value = Integer.valueOf(cities[i]);

            while (!stack.isEmpty() && stack.peek().getValue() > value)
                result[stack.pop().getKey()] = i;

            stack.push(Map.entry(i, value));

        }

        for (int i = 0; i < result.length; i++)
            if (result[i] == 0)
                result[i] = -1;

        FileWriter writer = new FileWriter("output.txt");
        for (int i = 0; i < result.length; i++)
            writer.write(Integer.toString(result[i]) + " ");
        writer.close();

    }

}
