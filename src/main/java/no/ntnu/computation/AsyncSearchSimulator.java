package no.ntnu.computation;

import java.io.IOException;
import java.net.Socket;

import static no.ntnu.computation.SearchSimulator.processClientRequest;

public class AsyncSearchSimulator implements Runnable {

    protected Socket clientSocket = null;
    protected String serverText = null;

    protected String serverType = null;

    public AsyncSearchSimulator(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        try {
            processClientRequest(clientSocket, serverText);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}