import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

public class TaskC {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());

        int[][] orders = new int[n + 1][2];
        Set<int[]> variants = new HashSet<>();

        String[] fs = reader.readLine().split(" ");
        int f = Integer.parseInt(fs[0]);
        int s = Integer.parseInt(fs[1]);
        orders[1][0] = f;
        orders[1][1] = s;

        variants.add(variant(f, 0));
        variants.add(variant(0, s));

        List<Map<Integer, Integer>> layers = new ArrayList<>(n + 1);
        layers.add(null);
        layers.add(new HashMap<>());
        layers.get(1).put(f, 0);
        layers.get(1).put(0, s);

        for (int i = 2; i <= n; i++) {

            Set<int[]> previous = variants;
            variants = new HashSet<>();

            fs = reader.readLine().split(" ");
            f = Integer.parseInt(fs[0]);
            s = Integer.parseInt(fs[1]);
            orders[i][0] = f;
            orders[i][1] = s;

            Map<Integer, Integer> data = new TreeMap<>();
            for (int[] v : previous) {

                int[] vf = variant(v[0] + f, v[1]);
                if (!data.containsKey(vf[0]) || data.get(vf[0]) > vf[1])
                    data.put(vf[0], vf[1]);

                int[] vs = variant(v[0], v[1] + s);
                if (!data.containsKey(vs[0]) || data.get(vs[0]) > vs[1])
                    data.put(vs[0], vs[1]);

            }

            Map<Integer, Integer> layer = new HashMap<>();
            int ceiling = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer> e : data.entrySet())
                if (e.getValue() < ceiling) {
                    ceiling = e.getValue();
                    int[] v = variant(e.getKey(), e.getValue());
                    variants.add(v);
                    layer.put(v[0], v[1]);
                }
            layers.add(layer);

            System.out.println(layer.size());

        }
        reader.close();

        int[] result = variant(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (int[] v : variants)
            if (Math.max(v[0], v[1]) < Math.max(result[0], result[1]))
                result = v;

        System.out.println(result[0]);
        System.out.println(result[1]);

        String output = "";
        for (int i = n; i > 1; i--) {
            Map<Integer, Integer> previous = layers.get(i - 1);
            if (result[1] - previous.getOrDefault(result[0], 0) == orders[i][1]) {
                result[1] -= orders[i][1];
                output = "2 " + output;
            } else {
                result[0] -= orders[i][0];
                output = "1 " + output;
            }
        }

        output = ((result[0] > 0) ? "1" : "2") + " " + output;

        FileWriter writer = new FileWriter("output.txt");
        writer.write(output);
        writer.close();

    }

    static int[] variant(int first, int second) {

        int[] result = new int[2];
        result[0] = first;
        result[1] = second;
        return result;

    }

}
