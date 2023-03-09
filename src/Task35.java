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

public class Task35 {

    static class Graph {

        Map<Integer, Set<Integer>> adjacency;

        Graph() {
            this.adjacency = new HashMap<>();
        }

        @Override
        public String toString() {
            String result = "";
            for (Entry<Integer, Set<Integer>> entry : adjacency.entrySet())
                result += entry.getKey() + ": " + entry.getValue() + "\n";
            return result.trim();
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

    }

    static Integer cyclepoint;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        Graph graph = new Graph();
        for (int i = 1; i <= n; i++) {
            graph.addVertex(i);
            String[] line = reader.readLine().split(" ");
            for (int j = 1; j <= n; j++)
                if (Integer.parseInt(line[j - 1]) == 1)
                    graph.addEdge(i, j);
        }
        reader.close();

        FileWriter writer = new FileWriter("output.txt");

        if (graph.vertexes().size() > 2) {

            int[] visited = new int[graph.vertexes().size() + 1];
            for (Integer vertex : graph.vertexes()) {

                if (visited[vertex] != 0)
                    continue;

                Stack<Integer> cycle = new Stack<>();
                if (dfs(graph, vertex, null, visited, cycle)) {
                    writer.write("YES" + "\n");
                    writer.write(cycle.size() + "\n");
                    while (!cycle.isEmpty())
                        writer.write(cycle.pop() + " ");
                    writer.close();
                    return;
                }

            }
        }

        writer.write("NO");
        writer.close();

    }

    static boolean dfs(Graph graph, Integer vertex, Integer from, int[] visited, Stack<Integer> cycle) {

        visited[vertex] = 1;
        for (Integer next : graph.edges(vertex)) {

            if (next.equals(from))
                continue;

            if (visited[next] == 1) {
                cyclepoint = next;
                cycle.push(vertex);
                return true;
            } else if (visited[next] == 0 && dfs(graph, next, vertex, visited, cycle)) {

                if (cyclepoint != null)
                    cycle.push(vertex);

                if (vertex.equals(cyclepoint))
                    cyclepoint = null;

                return true;
            }
        }

        visited[vertex] = 2;
        return false;

    }

}
