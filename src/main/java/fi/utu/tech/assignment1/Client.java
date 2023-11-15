package fi.utu.tech.assignment1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Connecting to server");
        Socket commSocket = new Socket("localhost", 12345);
        System.out.println("Connected to server");

        System.out.println("Closing connection");
        commSocket.close();
    }

}
