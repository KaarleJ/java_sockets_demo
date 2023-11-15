package fi.utu.tech.assignment5;

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
        this.in =  new BufferedReader(new InputStreamReader(s.getInputStream()));
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

                System.out.println("Received command: " + message);
                String[] cmd = message.split(";");
                commandExecutor(cmd);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void commandExecutor(String[] cmd) {
        switch (cmd[1]) {
            case "ON":
                System.out.println("Turning light " + cmd[2] + " ON");
                break;
            case "OFF":
                System.out.println("Turning light " + cmd[2] + " OFF!");
                break;
            case "QUERY":
                System.out.println("Some lights on and some not");
                break;
            default:
                break;
        }
    }

}
