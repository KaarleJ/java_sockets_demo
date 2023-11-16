package fi.utu.tech.assignment6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Connecting to server");
        Socket commSocket = new Socket("localhost", 12345);
        System.out.println("Connected to server");

        BufferedReader in = new BufferedReader(new InputStreamReader(commSocket.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(commSocket.getOutputStream())), true);

        while (true) {
            System.out.println("Enter command (QUIT/LIGHT;ON/OFF/QUERY)");
            Scanner input = new Scanner(System.in);
            String cmd = input.nextLine();
            out.println(cmd);

            if (cmd.toLowerCase().equals("quit")) {
                input.close();
                break;
            }

            String response = in.readLine();
            System.out.println(response);
        }

        System.out.println("Closing connection");
        commSocket.close();
    }

}

