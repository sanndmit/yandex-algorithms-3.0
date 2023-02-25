import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Task25 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        String[] str = reader.readLine().split(" ");
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(str[i]);
        reader.close();

        Arrays.sort(arr);

        int[] dp = new int[n];
        dp[1] = arr[1] - arr[0];
        if (n > 2)
            dp[2] = arr[2] - arr[0];
        if (n > 3)
            dp[3] = (arr[3] - arr[0]) - (arr[2] - arr[1]);

        for (int i = 4; i < dp.length; i++)
            dp[i] = (arr[i] - arr[i - 1]) + Math.min(dp[i - 1], dp[i - 2]);

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(dp[dp.length - 1]));
        writer.close();

    }

}
