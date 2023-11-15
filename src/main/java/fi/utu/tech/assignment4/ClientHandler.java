package fi.utu.tech.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    Socket s;
    final BufferedReader in;
    final PrintWriter out;

    public ClientHandler(Socket s) throws IOException {
        super();
        this.s = s;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
    }

    @Override
    public void run() {
        System.out.println("Client connected");

        try (in; out) {
            
            while (true) {
                String message = in.readLine();

                if (message == null) {
                    continue;
                }

                System.out.println("Received message: " + message);

                if (message.toLowerCase().equals("hello")) {
                    out.println("ack");
                }

                if (message.toLowerCase().equals("quit")) {
                    break;
                }
            }

            System.out.println("Closing sockets");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
