package fi.utu.tech.assignment3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);

        while (true) {
            System.out.println("Waiting for a client to connect");
            Socket commSocket = serverSocket.accept();

            System.out.println("A client connected. Passing the socket to a new thread");
            ClientHandler handler = new ClientHandler(commSocket);
            handler.start();
        }
    }

}
