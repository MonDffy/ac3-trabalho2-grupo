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

    static void testFile(ArrayList<String> input) {

  //      for(int i = 0;i<=input.size();i++){
  //      String[] entrada = input.get(i).split(" ");   
  //  }
        // Boolean valid = true;
        // if (valid = true) {

        // } else {

        // }

    }

}
