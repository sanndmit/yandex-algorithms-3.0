import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task2 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int k = Integer.parseInt(reader.readLine());
        String str = reader.readLine();
        reader.close();

        Map<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = Character.valueOf(str.charAt(i));
            ArrayList<Integer> list = map.get(c);
            if (list == null)
                list = new ArrayList<>();
            list.add(i);
            map.put(c, list);
        }
        
        int result = k + 1;
        for (ArrayList<Integer> list : map.values()) {

            int left = 0, right = 0;
            while (left != list.size() - 1) {

                int occ = 1 + (right - left);
                int dist = 1 + (list.get(right) - list.get(left));
                if (dist - occ > k) {
                    left++;
                    continue;
                }

                result = Math.max(result, occ + k);
                if (right == list.size() - 1)
                    break;
                right++;

            }

        }
        result = Math.min(result, str.length());
        
        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(result));
        writer.close();

    }

}
