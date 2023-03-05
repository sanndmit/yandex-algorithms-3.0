import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task29 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        int[] days = new int[n + 1];
        for (int i = 1; i <= n; i++)
            days[i] = Integer.parseInt(reader.readLine());
        reader.close();

        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 0;
        for (int j = 1; j <= n; j++)
            dp[0][j] = Integer.MAX_VALUE;
        int[][] prev = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 0; j <= n; j++) {

                int with = (j != n) ? dp[i - 1][j + 1] : Integer.MAX_VALUE;

                int without;
                if (days[i] > 100)
                    without = (j != 0 && dp[i - 1][j - 1] != Integer.MAX_VALUE) ? dp[i - 1][j - 1] + days[i]
                            : Integer.MAX_VALUE;
                else
                    without = (dp[i - 1][j] != Integer.MAX_VALUE) ? dp[i - 1][j] + days[i]
                            : Integer.MAX_VALUE;

                if (with < without) {
                    dp[i][j] = with;
                    prev[i][j] = j + 1;
                } else {
                    dp[i][j] = without;
                    prev[i][j] = (days[i] > 100) ? j - 1 : j;
                }

            }

        int result = dp[n][0];
        int left = 0;
        for (int j = 1; j <= n; j++) {
            if (dp[n][j] <= result) {
                result = dp[n][j];
                left = j;
            }
        }

        int from = left;
        int used = 0;
        String usages = "";
        for (int i = n; i > 0; i--) {
            if (prev[i][from] > from) {
                used++;
                usages = String.valueOf(i) + "\n" + usages;
            }
            from = prev[i][from];
        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(result) + "\n");
        writer.write(String.valueOf(left) + " " + String.valueOf(used) + "\n");
        writer.write(usages);
        writer.close();

    }

}
