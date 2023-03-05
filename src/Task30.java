import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task30 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine().trim());
        String[] firstStr = reader.readLine().split(" ");
        int[] first = new int[n];
        for (int i = 0; i < n; i++)
            first[i] = Integer.parseInt(firstStr[i]);
        int m = Integer.parseInt(reader.readLine().trim());
        String[] secondStr = reader.readLine().split(" ");
        int[] second = new int[m];
        for (int i = 0; i < m; i++)
            second[i] = Integer.parseInt(secondStr[i]);
        reader.close();

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (first[i] == second[j])
                    dp[i][j] = (i == 0 || j == 0) ? 1 : dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max((i == 0) ? 0 : dp[i - 1][j], (j == 0) ? 0 : dp[i][j - 1]);

        String result = "";
        int i = n - 1, j = m - 1;
        while (i >= 0 && j >= 0) {

            if (first[i] == second[j]) {
                result = first[i] + " " + result;
                i--;
                j--;
            } else if (((i == 0) ? 0 : dp[i - 1][j]) > ((j == 0) ? 0 : dp[i][j - 1]))
                i--;
            else
                j--;

        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(result);
        writer.close();

    }

}
