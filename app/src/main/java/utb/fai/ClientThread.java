package utb.fai;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClientThread extends Thread {

    private Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // Implementation of processing incoming communication from the telnet client
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                out.print(line + "\r\n");
                out.flush();
            }
            
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
