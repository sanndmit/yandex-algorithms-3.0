import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

public class Task10 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String word = reader.readLine();
        reader.close();

        Map<Character, BigInteger> map = new TreeMap<>();
        BigInteger length = new BigInteger(String.valueOf(word.length()));
        BigInteger one = new BigInteger("1");

        char[] ch = word.toCharArray();
        for (int i = 0; i < ch.length; i++) {

            map.putIfAbsent(ch[i], new BigInteger("0"));

            BigInteger pos = new BigInteger(String.valueOf(i));
            BigInteger result = length.subtract(pos).multiply(one.add(pos));
            map.put(ch[i], map.get(ch[i]).add(result));

        }

        FileWriter writer = new FileWriter("output.txt");
        for (Map.Entry<Character, BigInteger> entry : map.entrySet())
            writer.write(String.format("%c: %d\n", entry.getKey(), entry.getValue()));
        writer.close();

    }

}
