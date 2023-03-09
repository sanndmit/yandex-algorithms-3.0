import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Task33 {

    static class Graph {

        Map<Integer, Set<Integer>> adjacency;

        Graph() {
            this.adjacency = new HashMap<>();
        }

        void addVertex(Integer vertex) {
            adjacency.putIfAbsent(vertex, new HashSet<>());
        }

        void removeVertex(Integer vertex) {
            adjacency.values().stream().forEach(e -> e.remove(vertex));
            adjacency.remove(vertex);
        }

        void addEdge(Integer from, Integer to) {
            addVertex(from);
            addVertex(to);
            adjacency.get(from).add(to);
        }

        void removeEdge(Integer from, Integer to) {
            Set<Integer> edgesFrom = adjacency.get(from);
            if (edgesFrom != null)
                edgesFrom.remove(to);
        }

        Set<Integer> vertexes() {
            return adjacency.keySet();
        }

        Set<Integer> edges(Integer vertex) {
            return adjacency.get(vertex);
        }

        @Override
        public String toString() {
            String result = "";
            for (Entry<Integer, Set<Integer>> entry : adjacency.entrySet())
                if (!entry.getValue().isEmpty())
                    result = result + "\n" + entry.getKey() + ": " + entry.getValue();
            return result;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] nm = reader.readLine().split(" ");

        Graph graph = new Graph();
        for (int i = 1; i <= Integer.parseInt(nm[0]); i++)
            graph.addVertex(i);

        for (int i = 0; i < Integer.parseInt(nm[1]); i++) {
            String[] edge = reader.readLine().split(" ");
            graph.addEdge(Integer.valueOf(edge[0]), Integer.valueOf(edge[1]));
            graph.addEdge(Integer.valueOf(edge[1]), Integer.valueOf(edge[0]));
        }
        reader.close();

        int[] colors = new int[Integer.parseInt(nm[0]) + 1];
        boolean result = true;
        for (Integer vertex : graph.adjacency.keySet())
            if (colors[vertex] == 0)
                result = result && dfs(graph, vertex, colors, 1);

        FileWriter writer = new FileWriter("output.txt");
        writer.write((result) ? "YES" : "NO");
        writer.close();

    }

    static boolean dfs(Graph graph, Integer vertex, int[] colors, int color) {

        colors[vertex] = color;
        boolean result = true;
        for (Integer next : graph.edges(vertex))
            if (colors[next] == color)
                return false;
            else if (colors[next] == 0)
                result = result && dfs(graph, next, colors, -color);
        return result;

    }

}
