import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task24 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        int[][] arr = new int[n + 3][3];
        for (int i = 0; i < arr.length; i++) {

            if (i < 3) {
                arr[i][0] = Integer.MAX_VALUE;
                arr[i][1] = Integer.MAX_VALUE;
                arr[i][2] = Integer.MAX_VALUE;
            } else {
                String[] str = reader.readLine().split(" ");
                arr[i][0] = Integer.parseInt(str[0]);
                arr[i][1] = Integer.parseInt(str[1]);
                arr[i][2] = Integer.parseInt(str[2]);
            }

        }
        reader.close();

        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {

            if (i < 3) {
                dp[i] = 0;
                continue;
            }

            int a = dp[i - 1] + arr[i][0];
            int b = dp[i - 2] + arr[i - 1][1];
            int c = dp[i - 3] + arr[i - 2][2];

            if (a < b && a < c)
                dp[i] = a;
            else if (b < c)
                dp[i] = b;
            else
                dp[i] = c;

        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(dp[dp.length - 1]));
        writer.close();

    }

}
