import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task19 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(reader.readLine());
        int[] heap = new int[n];
        int end = -1;

        FileWriter writer = new FileWriter("output.txt");
        for (int i = 0; i < n; i++) {

            String line = reader.readLine();
            if (line == null)
                break;

            String[] command = line.split(" ");

            if (command[0].equals("0"))
                insert(heap, Integer.parseInt(command[1]), ++end);

            else if (command[0].equals("1"))
                writer.write(String.valueOf(extract(heap, end--)) + "\n");

        }
        reader.close();
        writer.close();

    }

    private static void insert(int[] heap, int value, int pos) {
        heap[pos] = value;
        heapifyUp(heap, pos);
    }

    private static int extract(int[] heap, int end) {
        swap(heap, 0, end);
        heapifyDown(heap, 0, end - 1);
        return heap[end];
    }

    private static void heapifyUp(int[] arr, int pos) {
        while (pos > 0) {
            int p = (pos - 1) / 2;
            if (arr[p] < arr[pos]) {
                swap(arr, pos, p);
                pos = p;
            } else
                break;
        }
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
