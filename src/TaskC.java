import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TaskC {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());

        int[][] orders = new int[n + 1][2];
        Set<int[]> variants = new HashSet<>();
        Set<int[]> previous = null;

        String[] fs = reader.readLine().split(" ");
        int f = Integer.parseInt(fs[0]);
        int s = Integer.parseInt(fs[1]);
        orders[1][0] = f;
        orders[1][1] = s;

        int[] vf = new int[2];
        vf[0] = f;
        vf[1] = 0;
        variants.add(vf);

        int[] vs = new int[2];
        vs[0] = 0;
        vs[1] = s;
        variants.add(vs);

        int[][] allocation = new int[n * 1000 + 1][n * 1000 + 1];
        allocation[f][0] = 1;
        allocation[0][s] = 1;

        for (int i = 2; i <= n; i++) {

            previous = variants;
            variants = new HashSet<>();

            fs = reader.readLine().split(" ");
            f = Integer.parseInt(fs[0]);
            s = Integer.parseInt(fs[1]);
            orders[i][0] = f;
            orders[i][1] = s;

            for (int[] v : previous) {

                vf = new int[2];
                vf[0] = v[0] + f;
                vf[1] = v[1];
                if (allocation[v[0] + f][v[1]] < i) {
                    allocation[v[0] + f][v[1]] = i;
                    variants.add(vf);
                }

                vs = new int[2];
                vs[0] = v[0];
                vs[1] = v[1] + s;
                if (allocation[v[0]][v[1] + s] < i) {
                    allocation[v[0]][v[1] + s] = i;
                    variants.add(vs);
                }

            }

        }
        reader.close();

        int fr = n * 1000;
        int sr = n * 1000;
        int maxr = n * 1000;
        for (int i = 0; i <= maxr; i++)
            for (int j = 0; j <= maxr; j++)
                if (allocation[i][j] == n && Math.max(i, j) < maxr) {
                    fr = i;
                    sr = j;
                    maxr = Math.max(i, j);
                }

        String result = "";
        for (int i = n; i > 1; i--)
            if (fr - orders[i][0] >= 0 && allocation[fr - orders[i][0]][sr] == i - 1) {
                fr -= orders[i][0];
                result = "1 " + result;
            } else {
                sr -= orders[i][1];
                result = "2 " + result;
            }

        result = ((fr > 0) ? "1" : "2") + " " + result;

        FileWriter writer = new FileWriter("output.txt");
        writer.write(result);
        writer.close();

    }

}
