import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 18.10.12
 * Time: 4:02
 */

public class TCPServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = null;

        try{
            while (true) {
                socket = serverSocket.accept();
                Scanner scanner = new Scanner(socket.getInputStream());

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

                if (scanner.nextLine().trim().equals("date")) {
                    printWriter.println(getCurrentDate());
                    printWriter.flush();
                } else {
                    printWriter.println(getCurrentTime());
                    printWriter.flush();
                }
            }
        }finally {
            if(socket != null) socket.close();
            serverSocket.close();
        }
    }

    private static String getCurrentDate(){
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }

    private static String getCurrentTime(){
        return new SimpleDateFormat("hh:mm").format(new Date());
    }
}
