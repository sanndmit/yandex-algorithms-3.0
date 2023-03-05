import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task28 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        reader.close();

        int[][] deck = new int[n][m];
        deck[0][0] = 1;
        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++)
                deck[i][j] = ((i - 2 >= 0 && j - 1 >= 0) ? deck[i - 2][j - 1] : 0)
                        + ((i - 1 >= 0 && j - 2 >= 0) ? deck[i - 1][j - 2] : 0);

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(deck[n - 1][m - 1]));
        writer.close();

    }

}
