import java.util;
import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;import java.util.Scanner;
import java.util.Scanner;

public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream("C:\\Users\\victo\\eclipse-workspace\\aaaa\\src\\input.txt");
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);

    System.out.println("Digite sua mensagem:");
    String linha = br.readLine(); // primeira linha

    while (linha != null) {
  
        System.out.println(linha);
        linha = br.readLine();
    }
    br.close();
}