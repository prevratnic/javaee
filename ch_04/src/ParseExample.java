import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 26.10.12
 * Time: 12:55
 */

public class ParseExample {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/media/Project/myProject/javaee/ch_04/src/input.txt")));

        List<String[]> list = new ArrayList<String[]>();

        int count = 0;
        String tmp;
        while ((tmp = bufferedReader.readLine()) != null){
            if(count > 0){
                list.add(parseLine(tmp));
            }
            count++;
        }

        bufferedReader.close();

        for(String[] strings: list){
            System.out.println(strings[3]);
        }

    }

    private static String[] parseLine(String input){
        return input.trim().split("\\s+");
    }

}
