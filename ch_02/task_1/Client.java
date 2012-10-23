import com.sun.istack.internal.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 18.10.12
 * Time: 4:01
 */

public class Client {
    public static void main(String[] args) throws IOException {
        sendMessage("date");
       // sendMessage("time");
    }

    private static void sendMessage(@NotNull String message) throws IOException{

        Socket socket = new Socket("localhost", 8080);
        //socket.setSoTimeout(5000);
        try{

            //Scanner scanner = new Scanner(socket.getInputStream());
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            InputStream inputStream = socket.getInputStream();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.print(message);
            printWriter.flush();

            int tmp;
            while ((tmp = inputStream.read()) != -1){
                System.out.print((char)tmp);
            }

            //System.out.print(scanner.nextLine());
           // System.out.print(bufferedReader.readLine());

        }finally {
            socket.close();
        }
    }
}
