import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Task39 {

    static class Vertex {

        int x, y, z;

        public Vertex(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());

        int[][][] cave = new int[n][n][n];
        Vertex start = null;
        for (int x = 0; x < n; x++) {
            reader.readLine();
            for (int y = 0; y < n; y++) {
                String line = reader.readLine();
                for (int z = 0; z < n; z++) {

                    String s = line.substring(z, z + 1);

                    if (s.equals("#"))
                        cave[x][y][z] = -1;
                    else if (s.equals("S")) {
                        start = new Vertex(x, y, z);
                    }

                }
            }
        }
        reader.close();

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(distance(n, cave, start)));
        writer.close();

    }

    static int distance(int n, int[][][] cave, Vertex start) {

        int[] dx = { -1, 0, 0, 1, 0, 0 };
        int[] dy = { 0, -1, 0, 0, 1, 0 };
        int[] dz = { 0, 0, -1, 0, 0, 1 };

        Queue<Vertex> bfs = new LinkedList<>();
        bfs.add(start);
        while (!bfs.isEmpty()) {

            Vertex head = bfs.poll();
            for (int i = 0; i < 6; i++) {

                int nextx = head.x + dx[i];
                int nexty = head.y + dy[i];
                int nextz = head.z + dz[i];
                if (nextx < 0 || nextx >= n || nexty < 0 || nexty >= n || nextz < 0 || nextz >= n)
                    continue;

                if (cave[nextx][nexty][nextz] == -1)
                    continue;

                if (cave[nextx][nexty][nextz] == 0) {
                    cave[nextx][nexty][nextz] = cave[head.x][head.y][head.z] + 1;
                    bfs.add(new Vertex(nextx, nexty, nextz));
                    if (nextx == 0)
                        return cave[nextx][nexty][nextz];
                }

            }

        }
        return -1;
    }

}
