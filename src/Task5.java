import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Task5 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        Integer[] nums = new Integer[n];
        for (int i = 0; i < n; i++)
            nums[i] = Integer.valueOf(reader.readLine());
        reader.close();

        BigInteger result = result(nums, new BigInteger("0"));

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(result));
        writer.close();

    }

    private static BigInteger result(Integer[] array, BigInteger result) {

        int lowest = lowest(array);
        
        BigInteger height = new BigInteger(String.valueOf(array[lowest]));
        BigInteger width = new BigInteger(String.valueOf(array.length - 1));
        result = result.add(height.multiply(width));

        ArrayList<Integer[]> subarrays = subarrays(array, lowest, array[lowest]);
        for (Integer[] subarray : subarrays)
            result = result.add(result(subarray, new BigInteger("0")));

        return result;

    }

    private static int lowest(Integer[] array) {

        int lowest = Integer.MAX_VALUE;
        int result = -1;
        for (int i = 0; i < array.length; i++)
            if (array[i] < lowest) {
                lowest = array[i];
                result = i;
            }
        return result;

    }

    private static ArrayList<Integer[]> subarrays(Integer[] array, int split, Integer cut) {

        ArrayList<Integer[]> result = new ArrayList<>();

        if (split >= 2) {
            Integer[] left = new Integer[split];
            for (int i = 0; i < split; i++)
                left[i] = array[i] - cut;
            result.add(left);
        }

        if (split <= array.length - 3) {
            Integer[] right = new Integer[array.length - split - 1];
            for (int i = split + 1; i < array.length; i++)
                right[i - split - 1] = array[i] - cut;
            result.add(right);
        }

        return result;

    }

}
