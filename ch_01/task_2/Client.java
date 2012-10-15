import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Date;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 15.10.12
 * Time: 23:42
 */
public class Client {
    public static void main(String[] args) throws IOException {

        int serverPort = 8080;
        int clientPort = 8081;

        Subject subject = new Subject("Ilya", "Hello World", new Date());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(subject);
        objectOutputStream.flush();

        byte [] buffer = byteArrayOutputStream.toByteArray();

        DatagramSocket datagramSocket = new DatagramSocket(clientPort);
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        datagramPacket.setAddress(InetAddress.getByName("localhost"));
        datagramPacket.setPort(serverPort);
        datagramSocket.send(datagramPacket);
    }
}
