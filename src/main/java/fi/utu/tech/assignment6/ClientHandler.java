package fi.utu.tech.assignment6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    Socket s;
    Hub hub;
    final BufferedReader in;
    final PrintWriter out;

    public ClientHandler(Socket s, Hub h) throws IOException {
        this.hub = h;
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

                System.out.println("Received command: " + message);

                if (message.toLowerCase().equals("quit")) {
                    System.out.println("Client disconnected");
                    break;
                }

                String[] cmd = message.split(";");

                if (!cmd[0].toLowerCase().equals("light")) {
                    out.println("Unknown command");
                    continue;
                }
                commandExecutor(cmd);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void commandExecutor(String[] cmd) {
        System.out.println("Executing command: " + cmd[1]);
        switch (cmd[1]) {
            case "ON":
                out.println("Turning light " + cmd[2] + " ON");
                hub.turnOnLight(Integer.parseInt(cmd[2]));
                break;
            case "OFF":
                out.println("Turning light " + cmd[2] + " OFF!");
                hub.turnOffLight(Integer.parseInt(cmd[2]));
                break;
            case "QUERY":
                out.println(formattedLights());
                break;
            default:
                break;
        }
    }

    // En halua muuttaa Hub ja Light luokkia, joten toteutin metodin,
    // joka formatoi getLights palautusmerkkijonon uuteen formaattiin.
    private String formattedLights() {
        String lights = hub.getLights().toString();
        String[] lightStatuses = lights.split(", ");
        for (int i = 0; i < lightStatuses.length; i++) {
            String[] parts = lightStatuses[i].split(" ");
            lightStatuses[i] = parts[1] + ":" + parts[4];
        }
        String reformattedLights = String.join(";", lightStatuses);
        return reformattedLights;
    }
}
