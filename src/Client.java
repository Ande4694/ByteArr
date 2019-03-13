import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {

    public static void main(String[] args) {

        try {

            System.out.println("client startet");
            Socket socket = new Socket("192.168.1.100", 8015);
            System.out.println("socket oprettet");

            OutputStream outputStream = socket.getOutputStream();


            outputStream.write(99); //sender "c"



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
