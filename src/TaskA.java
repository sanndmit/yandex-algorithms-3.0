import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TaskA {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());

        FileWriter writer = new FileWriter("output.txt");

        Stack<Integer> train = new Stack<>();
        Stack<String> stuff = new Stack<>();
        Map<String, Integer> total = new HashMap<>();

        for (int i = 0; i < n; i++) {

            String[] command = reader.readLine().split(" ");

            if (command[0].equals("add")) {

                Integer qty = Integer.valueOf(command[1]);
                String cmmd = command[2];

                train.push(qty);
                stuff.push(cmmd);
                total.put(cmmd, ((total.containsKey(cmmd)) ? total.get(cmmd) : 0) + qty);

            } else if (command[0].equals("delete")) {

                Integer qty = Integer.valueOf(command[1]);
                while (qty != 0) {

                    Integer removed = train.pop();
                    String cmmd = stuff.pop();
                    if (removed > qty) {
                        train.push(removed - qty);
                        stuff.push(cmmd);
                        total.put(cmmd, ((total.containsKey(cmmd)) ? total.get(cmmd) : 0) - qty);
                        qty = 0;
                    } else {
                        total.put(cmmd, ((total.containsKey(cmmd)) ? total.get(cmmd) : 0) - removed);
                        qty -= removed;
                    }

                }

            } else if (command[0].equals("get")) {

                String cmmd = command[1];
                writer.write(String.valueOf(total.containsKey(cmmd) ? total.get(cmmd) : 0) + "\n");

            }

        }
        reader.close();
        writer.close();

    }

}
