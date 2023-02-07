//package ntnu.no.servers;
//
//import ntnu.no.computation.AsyncSearchSimulator;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//import static ntnu.no.computation.SearchSimulator.processClientRequest;
//
//public class MultiThreadedServer implements Runnable {
//
//    protected int serverPort = 8080;
//    protected ServerSocket serverSocket = null;
//    protected boolean isStopped = false;
//
//    public MultiThreadedServer(int port) {
//        this.serverPort = port;
//    }
//
//
//    public void run() {
//        openServerSocket();
//        Socket clientSocket = null;
//
//        while (!isStopped()) {
//            // wait for a connection
//            try {
//                clientSocket = serverSocket.accept();
//                OutputStream out = clientSocket.getOutputStream();
//                new Thread(
//                        new AsyncSearchSimulator(
//                                clientSocket,
//                                "Multithreaded Server",
//                                out
//                        )
//                ).start();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            // on receiving a request, execute the heavy computation in a new thread
//        }
//
//
//        System.out.println("Server Stopped.");
//    }
//
//    private synchronized boolean isStopped() {
//        return this.isStopped;
//    }
//
//    public synchronized void stop() {
//        this.isStopped = true;
//        try {
//            this.serverSocket.close();
//        } catch (IOException e) {
//            throw new RuntimeException("Error closing server", e);
//        }
//    }
//
//    private void openServerSocket() {
//        try {
//            this.serverSocket = new ServerSocket(this.serverPort);
//        } catch (IOException e) {
//            throw new RuntimeException("Cannot open port: " + serverPort, e);
//        }
//    }
//}

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