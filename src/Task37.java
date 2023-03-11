import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;
import java.util.Set;

public class Task37 {

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
        String[] fromto = reader.readLine().split(" ");
        reader.close();

        int from = Integer.parseInt(fromto[0]);
        int to = Integer.parseInt(fromto[1]);

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[from] = 0;

        int[] prev = new int[n + 1];
        Arrays.fill(prev, -1);

        Queue<Integer> bfs = new LinkedList<>();
        bfs.add(from);
        while (!bfs.isEmpty()) {

            Integer head = bfs.poll();
            for (Integer edge : graph.edges(head)) {
                if (dist[edge] == Integer.MAX_VALUE)
                    bfs.add(edge);
                if (dist[head] + 1 < dist[edge]) {
                    dist[edge] = dist[head] + 1;
                    prev[edge] = head;
                }
            }

        }

        FileWriter writer = new FileWriter("output.txt");

        if (dist[to] == Integer.MAX_VALUE)
            writer.write(String.valueOf(-1));
        else {

            writer.write(String.valueOf(dist[to]) + "\n");
            if (dist[to] > 0) {
                String route = String.valueOf(to);
                while (prev[to] != -1) {
                    to = prev[to];
                    route = String.valueOf(to) + " " + route;
                }
                writer.write(route);
            }

        }
        writer.close();

    }

}
