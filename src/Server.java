import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args){

        try {

            ServerSocket serverSocket = new ServerSocket(8015);
            System.out.println("Socket er oprettet");

            // denne linie blokere
            // afventer klient
            Socket socket = serverSocket.accept();


            System.out.println("client forbundet");

            InputStream inputStream = socket.getInputStream();

            byte [] byteArray = new byte[4];


            inputStream.read(byteArray);

            for (int i = 0; i < byteArray.length; i++) {
                if (byteArray[i]!=0){
                    System.out.println("recieved : "+ byteArray[i]);
                }

            }

        } catch (IOException e) {

            System.out.println("port 6780 is already used");
            e.printStackTrace();
        }
    }
}
