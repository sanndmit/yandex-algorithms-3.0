import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Task1 {

    public static void main(String[] args) throws IOException {

        Map<Character, Integer> map = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String input = reader.readLine();
        while (input != null) {
            for (char ch : input.toCharArray()) {
                if (ch != ' ')
                    map.put(ch, (map.containsKey(ch)) ? map.get(ch) + 1 : 1);
            }
            input = reader.readLine();
        }
        reader.close();

        Integer max = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max)
                max = entry.getValue();
        }

        FileWriter writer = new FileWriter("output.txt");
        for (int i = max; i > 0; i--) {
            for (Map.Entry<Character, Integer> entry : map.entrySet())
                writer.write((entry.getValue() >= i) ? "#" : " ");
            writer.write("\n");
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            writer.write(entry.getKey());
        writer.close();

    }
}
