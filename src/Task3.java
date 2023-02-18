import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Task3 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int[] nums = new int[Integer.parseInt(reader.readLine())];
        int n = 0;
        for (String str : reader.readLine().split(" "))
            nums[n++] = Integer.parseInt(str);
        int[] cols = new int[Integer.parseInt(reader.readLine())];
        int c = 0;
        for (String str : reader.readLine().split(" "))
            cols[c++] = Integer.parseInt(str);
        reader.close();

        nums = Arrays.stream(nums).distinct().toArray();
        Arrays.sort(nums);

        FileWriter writer = new FileWriter("output.txt");
        for (int col : cols)
            writer.write(String.valueOf(binarySearch(nums, col)) + "\n");
        writer.close();

    }

    private static int binarySearch(int[] nums, int value) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = (low + high) >>> 1;
            
            if (nums[mid] < value)
                low = mid + 1;
            else if (nums[mid] > value)
                high = mid - 1;
            else
                return mid; // key found
        }
        return low; // key not found

    }

}
