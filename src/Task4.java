import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task4 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        int row = Integer.parseInt(reader.readLine());
        int side = Integer.parseInt(reader.readLine());
        reader.close();

        int seat = seat(row, side);
        int seathi = seat + k;
        int seatlo = seat - k;

        FileWriter writer = new FileWriter("output.txt");
        if (seathi <= n && seatlo >= 1)
            if (row - row(seatlo) < row(seathi) - row)
                writer.write(String.format("%d %d", row(seatlo), side(seatlo)));
            else
                writer.write(String.format("%d %d", row(seathi), side(seathi)));
        else if (seathi <= n)
            writer.write(String.format("%d %d", row(seathi), side(seathi)));
        else if (seatlo >= 1)
            writer.write(String.format("%d %d", row(seatlo), side(seatlo)));
        else
            writer.write(String.format("%d", -1));

        writer.close();

    }

    private static int seat(int row, int side) {
        return 2 * (row - 1) + side;
    }

    private static int row(int seat) {
        return (seat - 1) / 2 + 1;
    }

    private static int side(int seat) {
        return (seat % 2 == 0) ? 2 : 1;
    }

}
