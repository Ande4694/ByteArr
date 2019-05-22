package webserver;

import javafx.scene.transform.Scale;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static final String path = System.getProperty("user.dir");
    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(1337);

            while(true) {
                System.out.println("path: "+path);
                System.out.println("Afventer forbindelse");
                Socket socket = serverSocket.accept();
                System.out.println("Forbindelse oprettet");


                serviceTheClient(socket);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private static void serviceTheClient(Socket socket) {
        try {
            Scanner fraClient = new Scanner(socket.getInputStream());


            String fraClientString = fraClient.nextLine();
            System.out.println("fra client string : "+fraClientString);

            StringTokenizer st = new StringTokenizer(fraClientString);

            System.out.println(st.nextToken());
            String toeknString = st.nextToken();

            String fin = toeknString.substring(1,toeknString.length());


            System.out.println(fraClientString);

            if (fin.equals("index.html")) {
//                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//
//                // send svar til client(browser)
//                dataOutputStream.writeBytes("HTTP/1.0 200 her kommer skidtet\r\n");
//                // SYNTAX   HTTP/1.0 200 "besked" \r\n
//                // 200 er 404 sprog fro "ok"
//                // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
//                dataOutputStream.writeBytes("Content-length: 3\r\n");
//                // selvsigende
//                dataOutputStream.writeBytes("\r\n");
//                // vigtigt, denne adskiller header fra indhold
//
//                //selve indholdet
//                dataOutputStream.writeBytes("hej\n");

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String pathIndex = path + "/index.html";
                File index = new File(pathIndex);
                if (!index.isFile()){
                    System.out.println("filen ikke fundet");
                } else {
                    System.out.println("filen fundet");
                    int length = (int) index.length();
                    byte [] byteArr = new byte[length];
                    FileInputStream fileInputStream = new FileInputStream(index);
                    fileInputStream.read(byteArr);
                    fileInputStream.close();


                    // HER ER HTTP HEADER
                    dataOutputStream.writeBytes("HTTP:/1.0 200 her kommer index\r\n");
                    dataOutputStream.writeBytes("Content-length: "+length+"\r\n");
                    dataOutputStream.writeBytes("\r\n");

                    // HER ER HTTP "RESOURCE"
                    //"byteArr" indeholder index.html i byte format
                    dataOutputStream.write(byteArr,0,length);
                    dataOutputStream.writeBytes("\n");
                }

                // TELNET EASY ACCESS
                //l

                // TELNET localhost 1337
                // GET /index.html HTTP/1.1
                // Host: localhost
                //



            }


            else if (fin.equals("about.html")){
                DataOutputStream dataOutputStream2 = new DataOutputStream(socket.getOutputStream());

                String pathAbout = path + "/about.html";
                File about = new File(pathAbout);
                if (!about.isFile()){
                    System.out.println("filen ikke fundet");
                } else {
                    System.out.println("filen fundet");
                    int length = (int) about.length();
                    byte [] byteArr = new byte[length];
                    FileInputStream fileInputStream = new FileInputStream(about);
                    fileInputStream.read(byteArr);
                    fileInputStream.close();


                    dataOutputStream2.writeBytes("HTTP/1.0 200 her kommer about\r\n");
                    dataOutputStream2.writeBytes("Content-length: "+length+"\r\n");
                    dataOutputStream2.writeBytes("\r\n");
                    dataOutputStream2.write(byteArr,0,length);
                    dataOutputStream2.writeBytes("\n");
                }



            }
            else if (fin.equals("products.html")){
                DataOutputStream dataOutputStream2 = new DataOutputStream(socket.getOutputStream());

                String pathProducts = path + "/products.html";
                File products = new File(pathProducts);
                if (!products.isFile()){
                    System.out.println("filen ikke fundet");
                } else {
                    System.out.println("filen fundet");
                    int length = (int) products.length();
                    byte [] byteArr = new byte[length];
                    FileInputStream fileInputStream = new FileInputStream(products);
                    fileInputStream.read(byteArr);
                    fileInputStream.close();


                    dataOutputStream2.writeBytes("HTTP/1.0 200 her kommer about\r\n");
                    dataOutputStream2.writeBytes("Content-length: "+length+"\r\n");
                    dataOutputStream2.writeBytes("\r\n");
                    dataOutputStream2.write(byteArr,0,length);
                    dataOutputStream2.writeBytes("\n");
                }



            }


            else {
                DataOutputStream dataOutputStream2 = new DataOutputStream(socket.getOutputStream());

                String pathError = path + "/error.html";
                File products = new File(pathError);
                if (!products.isFile()){
                    System.out.println("filen ikke fundet");
                } else {
                    System.out.println("filen fundet");
                    int length = (int) products.length();
                    byte [] byteArr = new byte[length];
                    FileInputStream fileInputStream = new FileInputStream(products);
                    fileInputStream.read(byteArr);
                    fileInputStream.close();


                    dataOutputStream2.writeBytes("HTTP/1.0 200 her kommer about\r\n");
                    dataOutputStream2.writeBytes("Content-length: "+length+"\r\n");
                    dataOutputStream2.writeBytes("\r\n");
                    dataOutputStream2.write(byteArr,0,length);
                    dataOutputStream2.writeBytes("\n");
                }



            }






        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
