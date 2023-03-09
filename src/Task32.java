import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task32 {

    static class Graph {

        Map<Integer, Set<Integer>> adjacency;

        public Graph() {
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

        int[] components = new int[graph.vertexes().size() + 1];
        int component = 0;
        for (Integer vertex : graph.vertexes())
            if (components[vertex] == 0)
                dfs(graph, vertex, components, ++component);

        int[] counts = new int[component + 1];
        String[] lists = new String[component + 1];
        for (int i = 1; i < components.length; i++) {
            int c = components[i];
            counts[c]++;
            lists[c] = ((lists[c] == null) ? "" : lists[c] + " ") + i;
        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(component) + "\n");
        for (int i = 1; i <= component; i++) {
            writer.write(String.valueOf(counts[i]) + "\n");
            writer.write(String.valueOf(lists[i]) + "\n");
        }
        writer.close();

    }

    static void dfs(Graph graph, Integer vertex, int[] components, int component) {

        components[vertex] = component;
        for (Integer next : graph.edges(vertex))
            if (components[next] == 0)
                dfs(graph, next, components, component);

    }

}
