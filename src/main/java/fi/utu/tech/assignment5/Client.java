package fi.utu.tech.assignment5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Connecting to server");
        Socket commSocket = new Socket("localhost", 12345);
        System.out.println("Connected to server");

        System.out.println("Sending message");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(commSocket.getOutputStream())));
        out.println("LIGHT;ON;3");
        out.flush();

        System.out.println("Waiting for confirmation");
        BufferedReader in = new BufferedReader(new InputStreamReader(commSocket.getInputStream()));
        String message;
        while (true) {
            message = in.readLine();
            System.out.println("Received message: " + message);
            if (message.toLowerCase().equals("ack")) {
                System.out.println("Confirmation gotten");
                break;
            }
        }

        out.println("Quit");
        out.flush();
        System.out.println("Closing connection");
        commSocket.close();
    }

}
