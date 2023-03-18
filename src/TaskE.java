import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TaskE {

    static class Vertex {

        int h, w;

        public Vertex(int h, int w) {
            this.h = h;
            this.w = w;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] hw = reader.readLine().split(" ");
        int h = Integer.parseInt(hw[0]);
        int w = Integer.parseInt(hw[1]);

        int[][] field = new int[h + 2][w + 2];

        for (int i = 0; i <= h + 1; i++) {
            field[i][0] = -1;
            field[i][w + 1] = -1;
        }

        for (int i = 0; i <= w + 1; i++) {
            field[0][i] = -1;
            field[h + 1][i] = -1;
        }

        for (int i = h; i >= 1; i--) {

            String line = reader.readLine();
            for (int j = 1; j <= w; j++) {

                String s = line.substring(j - 1, j);
                if (s.equals("X"))
                    field[i][j] = -1;

            }
        }
        String[] start = reader.readLine().split(" ");
        String[] finish = reader.readLine().split(" ");
        reader.close();

        int sh = Integer.parseInt(start[1]);
        int sw = Integer.parseInt(start[0]);

        int fh = Integer.parseInt(finish[1]);
        int fw = Integer.parseInt(finish[0]);

        int distance = distance(field, new Vertex(sh, sw), fh, fw);
        distance = (distance == 0) ? 1 : distance;

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(distance));
        writer.close();

    }

    static int distance(int[][] field, Vertex start, int fh, int fw) {

        int[] dh = { 1, 1, 0, -1, -1, -1, 0, 1 };
        int[] dw = { 0, 1, 1, 1, 0, -1, -1, -1 };

        Queue<Vertex> bfs = new LinkedList<>();
        bfs.add(start);
        while (!bfs.isEmpty()) {

            Vertex head = bfs.poll();
            int value = field[head.h][head.w] + 1;

            for (int i = 0; i < 8; i++) {

                int h = head.h;
                int w = head.w;
                boolean proceed = true;
                while (proceed) {

                    h = h + dh[i];
                    w = w + dw[i];
                    if (field[h][w] == -1) {
                        proceed = false;
                        continue;
                    }

                    if (field[h][w] == 0) {
                        field[h][w] = value;
                        bfs.add(new Vertex(h, w));
                    }

                    if (h == fh && w == fw)
                        return field[h][w];

                }

            }

        }
        return -1;
    }

}
