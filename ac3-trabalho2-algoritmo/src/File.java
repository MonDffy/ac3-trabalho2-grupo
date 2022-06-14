import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File {

    static void reader(String path, ArrayList<String> input) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String line = "";
        while (true) {
            line = buffRead.readLine();
            if (line != null) {
                input.add(line);
            } else {
                break;
            }
        }
        buffRead.close();
    }

    static ArrayList<String[]> split(ArrayList<String> input) {

        ArrayList<String[]> instructions = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String[] str = input.get(i).split("[ .]");
            instructions.add(str);
        }
        return instructions;

    }

    static void testFile(ArrayList<String> input) {

    }

}
