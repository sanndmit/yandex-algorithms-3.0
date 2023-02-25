import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task23 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        reader.close();

        int[] dp = new int[n + 1];
        int[] prev = new int[n + 1];
        for (int i = 1; i <= n; i++) {

            if (i + 1 <= n)
                evaluate(dp, prev, i, i + 1);

            if (i * 2 <= n)
                evaluate(dp, prev, i, i * 2);

            if (i * 3 <= n)
                evaluate(dp, prev, i, i * 3);

        }

        String steps = "";
        int step = n;
        while (step != 0) {
            steps = String.format("%d %s", step, steps);
            step = prev[step];
        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(dp[n]) + "\n" + steps);
        writer.close();

    }

    private static void evaluate(int[] dp, int[] prev, int i, int k) {
        
        if (dp[k] == 0 || dp[k] > dp[i] + 1) {
            dp[k] = dp[i] + 1;
            prev[k] = i;
        }
        
    }

}
