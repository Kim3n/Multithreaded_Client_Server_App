package no.ntnu;

import no.ntnu.servers.MultiThreadedServer;
import no.ntnu.servers.SingleThreadedServer;

class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting SingleThread server");
        SingleThreadedServer server = new SingleThreadedServer(8080);
        Thread serverThread = new Thread(server);
        serverThread.start();

//        System.out.println("Starting MultiThread server");
//        MultiThreadedServer server = new MultiThreadedServer(8080);
//        Thread serverThread = new Thread(server);
//        serverThread.start();
    }
}