package fi.utu.tech.assignment2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);

        System.out.println("Waiting for client to connect");
        Socket commSocket = serverSocket.accept();
        System.out.println("Client connected");

        InputStream in = commSocket.getInputStream();
        String message = new String(in.readAllBytes());
        in.close();
        System.out.println("Received message: " + message);

        System.out.println("Closing connection");
        serverSocket.close();
    }

}
