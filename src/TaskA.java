import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TaskA {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());

        FileWriter writer = new FileWriter("output.txt");

        Stack<BigInteger> train = new Stack<>();
        Stack<String> stuff = new Stack<>();
        Map<String, BigInteger> total = new HashMap<>();

        for (int i = 0; i < n; i++) {

            String[] command = reader.readLine().split(" ");

            if (command[0].equals("add")) {

                BigInteger qty = new BigInteger(command[1]);
                String cmmd = command[2];

                train.push(qty);
                stuff.push(cmmd);
                total.put(cmmd, ((total.containsKey(cmmd)) ? total.get(cmmd) : new BigInteger("0")).add(qty));

            } else if (command[0].equals("delete")) {

                BigInteger qty = new BigInteger(command[1]);
                boolean proceed = true;
                while (proceed) {

                    BigInteger removed = train.pop();
                    String cmmd = stuff.pop();
                    BigInteger curr = (total.containsKey(cmmd)) ? total.get(cmmd) : new BigInteger("0");

                    if (removed.compareTo(qty) > 0) {
                        train.push(removed.subtract(qty));
                        stuff.push(cmmd);
                        total.put(cmmd, curr.subtract(qty));
                        proceed = false;
                    } else {
                        total.put(cmmd, curr.subtract(removed));
                        qty = qty.subtract(removed);
                        if (qty.equals(new BigInteger("0")))
                            proceed = false;
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
