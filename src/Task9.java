import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task9 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] nmk = reader.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        int[][] arr = new int[n][m];
        int[][] prefsum = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {

            String[] nums = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {

                arr[i][j] = Integer.parseInt(nums[j]);

                prefsum[i + 1][j + 1] = arr[i][j] + prefsum[i + 1][j] + prefsum[i][j + 1] - prefsum[i][j];

            }
        }

        FileWriter writer = new FileWriter("output.txt");
        for (int i = 0; i < k; i++) {

            String[] xy = reader.readLine().split(" ");
            int x1 = Integer.parseInt(xy[0]);
            int y1 = Integer.parseInt(xy[1]);
            int x2 = Integer.parseInt(xy[2]);
            int y2 = Integer.parseInt(xy[3]);

            int result = prefsum[x2][y2] - prefsum[x1 - 1][y2] - prefsum[x2][y1 - 1] + prefsum[x1 - 1][y1 - 1];
            writer.write(String.format("%d\n", result));
            
        }
        reader.close();
        writer.close();

    }

}
