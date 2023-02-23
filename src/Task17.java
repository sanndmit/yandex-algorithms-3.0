import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Task17 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        Queue<Integer> first = new LinkedList<>();
        for (String card : reader.readLine().split(" "))
            first.add(Integer.valueOf(card));

        Queue<Integer> second = new LinkedList<>();
        for (String card : reader.readLine().split(" "))
            second.add(Integer.valueOf(card));

        reader.close();

        int move = 0;
        String result = "botva";
        while (move < 1000000) {

            move++;
            if (move == 1000000)
                break;

            Integer cardF = first.remove();
            Integer cardS = second.remove();
            boolean firstWins;
            if (cardF == 0 && cardS == 9)
                firstWins = true;
            else if (cardF == 9 && cardS == 0)
                firstWins = false;
            else
                firstWins = (cardF > cardS);

            if (firstWins) {
                first.add(cardF);
                first.add(cardS);
            } else {
                second.add(cardF);
                second.add(cardS);
            }

            if (second.isEmpty()) {
                result = String.format("first %d", move);
                break;
            } else if (first.isEmpty()) {
                result = String.format("second %d", move);
                break;
            }

        }

        FileWriter writer = new FileWriter("output.txt");
        writer.write(result);
        writer.close();

    }

}
