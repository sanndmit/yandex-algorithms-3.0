import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task20 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        String[] nums = reader.readLine().split(" ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(nums[i]);

        reader.close();

        for (int i = n / 2; i >= 0; i--)
            heapifyDown(arr, i, n - 1);

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapifyDown(arr, 0, i - 1);
        }

        FileWriter writer = new FileWriter("output.txt");
        for (int i = 0; i < n; i++)
            writer.write(String.valueOf(arr[i]) + " ");
        writer.close();

    }

    private static void heapifyDown(int[] arr, int pos, int end) {
        while (2 * pos + 1 <= end) {
            int l = 2 * pos + 1;
            int r = 2 * pos + 2;
            if (r <= end && arr[r] > arr[l] && arr[r] > arr[pos]) {
                swap(arr, pos, r);
                pos = r;
            } else if (arr[l] > arr[pos]) {
                swap(arr, pos, l);
                pos = l;
            } else
                break;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
