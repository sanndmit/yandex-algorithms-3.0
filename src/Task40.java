import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Task40 {

    static class Vertex {

        int m, n;

        public Vertex(int m, int n) {
            this.m = m;
            this.n = n;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        int[][] metro = new int[m][n + 1];
        for (int i = 0; i < m; i++) {

            Arrays.fill(metro[i], -1);
            String[] stations = reader.readLine().split(" ");
            for (int j = 1; j < stations.length; j++)
                metro[i][Integer.parseInt(stations[j])] = Integer.MAX_VALUE;

        }
        String[] fromto = reader.readLine().split(" ");
        reader.close();

        int from = Integer.parseInt(fromto[0]);
        Queue<Vertex> bfs = new LinkedList<>();
        for (int i = 0; i < m; i++)
            if (metro[i][from] == Integer.MAX_VALUE) {
                metro[i][from] = 0;
                bfs.add(new Vertex(i, from));
            }

        while (!bfs.isEmpty()) {

            Vertex head = bfs.poll();
            for (int i = 1; i <= n; i++)
                if (metro[head.m][i] == Integer.MAX_VALUE) {
                    metro[head.m][i] = metro[head.m][head.n];
                    bfs.add(new Vertex(head.m, i));
                }

            for (int i = 0; i < m; i++)
                if (metro[i][head.n] == Integer.MAX_VALUE) {
                    metro[i][head.n] = metro[head.m][head.n] + 1;
                    bfs.add(new Vertex(i, head.n));

                }

        }

        int to = Integer.parseInt(fromto[1]);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++)
            if (metro[i][to] >= 0 && metro[i][to] < result)
                result = metro[i][to];

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf((result == Integer.MAX_VALUE) ? -1 : result));
        writer.close();

    }

}
