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
        sendMessage("time");
    }

    private static void sendMessage(@NotNull String message) throws IOException{

        Socket socket = new Socket("localhost", 8080);

        try{

            Scanner scanner = new Scanner(socket.getInputStream());

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.print(message);
            printWriter.flush();
            socket.shutdownOutput();

            System.out.println(scanner.nextLine());
            socket.shutdownInput();

        }finally {
            socket.close();
        }
    }
}
