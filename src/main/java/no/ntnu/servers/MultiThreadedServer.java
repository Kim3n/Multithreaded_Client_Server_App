package no.ntnu.servers;

import no.ntnu.computation.AsyncSearchSimulator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {
    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {

            openServerSocket();

        while (!isStopped()) {
            // wait for a connection

            // on receiving a request, execute the heavy computation in a new thread
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(
                        new AsyncSearchSimulator(
                                clientSocket, "Multithreaded Server"
                        )
                ).start();
            }   catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Server stopped");
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port: " + serverPort, e);
        }
    }
}
