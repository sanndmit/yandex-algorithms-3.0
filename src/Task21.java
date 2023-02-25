import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task21 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        reader.close();

        int[] dp = new int[(n < 3) ? 3 : n];
        dp[1] = 1;
        dp[2] = 1;
        int[] res = new int[(n < 3) ? 3 : n];
        res[0] = 2;
        res[1] = 4;
        res[2] = 7;
        for (int i = 3; i < n; i++) {
            dp[i] = res[i - 2] - dp[i - 2] - dp[i - 1];
            res[i] = 2 * res[i - 1] - dp[i - 1];
        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(res[n - 1]));
        writer.close();

    }

}
