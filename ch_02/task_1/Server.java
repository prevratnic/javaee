import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 18.10.12
 * Time: 4:02
 */

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        //socket.setSoTimeout(5000);

        try{

            //Scanner scanner = new Scanner(socket.getInputStream());
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            InputStream inputStream = socket.getInputStream();
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.print(getCurrentDate());
            printWriter.flush();

            int tmp;
            while ((tmp = inputStream.read()) != -1){
                System.out.print((char)tmp);
            }

            //System.out.print(bufferedReader.readLine());
//            if(scanner.nextLine().equals("date")){
//                printWriter.println(getCurrentDate());
//                printWriter.flush();
//            }

        }finally {
            socket.close();
            serverSocket.close();
        }
    }

    private static String getCurrentDate(){
        return new Date().toString();
    }

    private static String getCurrentTime(){
        return new Date().toString();
    }
}
