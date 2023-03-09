import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task31 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        String[] nm = reader.readLine().split(" ");
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(nm[1]); i++) {

            String[] edge = reader.readLine().split(" ");

            Integer a = Integer.valueOf(edge[0]);
            Integer b = Integer.valueOf(edge[1]);

            graph.putIfAbsent(a, new HashSet<>());
            graph.get(a).add(b);

            graph.putIfAbsent(b, new HashSet<>());
            graph.get(b).add(a);

        }
        reader.close();

        int[] comp = new int[Integer.parseInt(nm[0]) + 1];
        dfs(graph, 1, comp);

        int count = 0;
        String list = "";
        for (int i = 1; i < comp.length; i++)
            if (comp[i] == 1) {
                count++;
                list += i + " ";
            }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(count) + "\n");
        writer.write(list);
        writer.close();

    }

    private static void dfs(Map<Integer, Set<Integer>> graph, int vertex, int[] components) {

        components[vertex] = 1;
        if (graph.get(vertex) == null)
            return;

        for (Integer next : graph.get(vertex))
            if (components[next] == 0)
                dfs(graph, next, components);

    }

}
