import java.io.*;
import java.net.*;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 15.10.12
 * Time: 23:42
 */

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int serverPort = 8080;
        int clientPort = 8081;

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        DatagramSocket datagramSocket = new DatagramSocket(serverPort);
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

        while (true){
            datagramSocket.receive(datagramPacket);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            Subject t1 = (Subject) objectInputStream.readObject();

            System.out.println(t1);
        }
    }
}
