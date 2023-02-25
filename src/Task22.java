import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task22 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] nums = reader.readLine().split(" ");
        reader.close();

        int n = Integer.parseInt(nums[0]);
        int k = Integer.parseInt(nums[1]);

        int[] arr = new int[n];
        arr[0] = 1;
        for (int i = 1; i < n; i++)
            for (int j = i - 1; j >= 0 && j >= i - k; j--)
                arr[i] += arr[j];

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(arr[n - 1]));
        writer.close();

    }

}
