import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Task38 {

    static class Vertex {

        int x, y;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] nmstq = reader.readLine().split(" ");
        int n = Integer.parseInt(nmstq[0]);
        int m = Integer.parseInt(nmstq[1]);
        int s = Integer.parseInt(nmstq[2]);
        int t = Integer.parseInt(nmstq[3]);
        int q = Integer.parseInt(nmstq[4]);

        int[][] dist = new int[n + 1][m + 1];
        for (int x = 1; x <= n; x++)
            for (int y = 1; y <= m; y++)
                dist[x][y] = Integer.MAX_VALUE;
        dist[s][t] = 0;

        int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };
        int[] dy = { 2, 1, -1, -2, -2, -1, 1, 2 };

        Queue<Vertex> bfs = new LinkedList<>();
        bfs.add(new Vertex(s, t));
        while (!bfs.isEmpty()) {

            Vertex head = bfs.poll();
            for (int i = 0; i < 8; i++) {

                int jumpx = head.x + dx[i];
                int jumpy = head.y + dy[i];
                if (jumpx <= 0 || jumpx > n || jumpy <= 0 || jumpy > m)
                    continue;

                Vertex jump = new Vertex(jumpx, jumpy);
                if (dist[jumpx][jumpy] == Integer.MAX_VALUE) {
                    bfs.add(jump);
                    dist[jumpx][jumpy] = dist[head.x][head.y] + 1;
                }

            }

        }

        int result = 0;
        for (int i = 0; i < q; i++) {
            String[] xy = reader.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            if (dist[x][y] == Integer.MAX_VALUE) {
                result = -1;
                break;
            }
            result += dist[x][y];
        }
        reader.close();

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(result));
        writer.close();

    }

}
