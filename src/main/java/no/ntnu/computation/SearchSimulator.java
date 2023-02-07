package no.ntnu.computation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import no.ntnu.utils.ResponseGenerator;


public class SearchSimulator {
    public static void processClientRequest(Socket clientSocket, String serverType) throws IOException, InterruptedException {
        InputStream inStream = clientSocket.getInputStream();
        OutputStream outStream = clientSocket.getOutputStream();

        long time1 = System.currentTimeMillis();
        System.out.println("Request processing started at: " + time1);
        Thread.sleep(10 * 1000);
        long time2 = System.currentTimeMillis();
        System.out.println("Request processing ended at: " + time2);

        String responseHTML = ResponseGenerator.generatorResponseHTML(time1, time2, serverType);
        String responseHeader = ResponseGenerator.generatorResponseHeader(responseHTML.length());
        String response = responseHeader + responseHTML;
        outStream.write(response.getBytes());
        outStream.close();
        inStream.close();
    }

}