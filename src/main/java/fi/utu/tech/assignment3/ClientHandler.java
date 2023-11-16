package fi.utu.tech.assignment3;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    Socket s;

    public ClientHandler(Socket s) {
        super();
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println("Client connected");

        try (InputStream in = s.getInputStream()) {
            String message = new String(in.readAllBytes());
            System.out.println("Received message: " + message);

            System.out.println("Closing socket");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
