import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task26 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String[] str = reader.readLine().split(" ");
            for (int j = 1; j <= m; j++)
                arr[i][j] = Integer.parseInt(str[j - 1]);
        }
        reader.close();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= m; j++) {

                if (i == 0 || j == 0)
                    dp[i][j] = Integer.MAX_VALUE;
                else if (i == 1 && j == 1)
                    dp[i][j] = arr[i][j];
                else
                    dp[i][j] = arr[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);

            }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(dp[n][m]));
        writer.close();

    }

}
