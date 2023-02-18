import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task6 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        Integer.parseInt(reader.readLine());
        int n = Integer.parseInt(reader.readLine());

        OS[] array = new OS[n];
        int invalid = 0;
        for (int i = 0; i < array.length; i++) {

            String[] bounds = reader.readLine().split(" ");
            int from = Integer.parseInt(bounds[0]);
            int to = Integer.parseInt(bounds[1]);

            for (int j = 0; j < i; j++) {
                
                OS os = array[j];
                if (os.isValid() && os.from <= to && os.to >= from) {
                    os.invalidate();
                    invalid++;
                }

            }

            array[i] = new OS(from, to);

        }
        reader.close();

        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(n - invalid));
        writer.close();

    }

    public static class OS {

        private int from;
        private int to;
        private boolean valid;
    
        public OS(int from, int to) {
            this.from = from;
            this.to = to;
            this.valid = true;
        }
    
        public int getFrom() {
            return from;
        }
    
        public int getTo() {
            return to;
        }
    
        public boolean isValid() {
            return valid;
        }
    
        public void invalidate() {
            valid = false;
        }
        
    }

}
