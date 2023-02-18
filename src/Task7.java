import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task7 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] a = reader.readLine().split(":");
        String[] b = reader.readLine().split(":");
        String[] c = reader.readLine().split(":");
        reader.close();

        int roundtrip = timestamp(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]))
                - timestamp(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]));

        if (roundtrip < 0)
            roundtrip += timestamp(24, 0, 0);

        int time = timestamp(Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]))
                + ((roundtrip % 2 == 0) ? roundtrip : roundtrip + 1) / 2;

        int seconds = time % 60;
        int minutes = time / 60 % 60;
        int hours = time / 3600 % 24;
        String result = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        FileWriter writer = new FileWriter("output.txt");
        writer.write(result);
        writer.close();

    }

    private static int timestamp(int hours, int minutes, int seconds) {

        int result = 0;

        result += seconds;
        result += minutes * 60;
        result += hours * 3600;

        return result;

    }

}
