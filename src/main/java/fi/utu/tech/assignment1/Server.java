package fi.utu.tech.assignment1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Waiting for client to connect");
        Socket commSocket = serverSocket.accept();
        
        if (commSocket.isConnected()) { System.out.println("Client connected"); }

        System.out.println("Closing connection");
        serverSocket.close();
    }

}
