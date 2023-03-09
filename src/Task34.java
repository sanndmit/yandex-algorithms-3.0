import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class Task34 {

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
                result += entry.getKey() + ": " + entry.getValue() + "\n";
            return result.trim();
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
        }
        reader.close();

        System.out.println(graph);

        Stack<Integer> sort = new Stack<>();
        int[] visited = new int[graph.vertexes().size() + 1];
        boolean cycle = false;
        for (Integer vertex : graph.vertexes())
            if (visited[vertex] == 0)
                cycle = cycle || dfs(graph, vertex, visited, sort);

        FileWriter writer = new FileWriter("output.txt");
        if (cycle)
            writer.write(String.valueOf(-1));
        else
            while (!sort.isEmpty())
                writer.write(sort.pop() + " ");
        writer.close();

    }

    static boolean dfs(Graph graph, Integer vertex, int[] visited, Stack<Integer> sort) {

        visited[vertex] = 1;
        boolean cycle = false;
        for (Integer next : graph.edges(vertex))
            if (visited[next] == 1)
                cycle = true;
            else if (visited[next] == 0)
                cycle = cycle || dfs(graph, next, visited, sort);
        visited[vertex] = 2;
        sort.push(vertex);
        return cycle;

    }

}
