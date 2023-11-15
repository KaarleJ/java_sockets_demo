package fi.utu.tech.assignment2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Connecting to server");
        Socket commSocket = new Socket("localhost", 12345);
        System.out.println("Connected to server");

        OutputStream out = commSocket.getOutputStream();
        out.write("Hello world!".getBytes());
        out.flush();

        System.out.println("Message sent. Closing output stream");
        out.close();

        System.out.println("Closing connection");
        commSocket.close();
    }

}
