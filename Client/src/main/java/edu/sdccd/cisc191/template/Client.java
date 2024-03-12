package edu.sdccd.cisc191.template;

import com.sun.org.apache.bcel.internal.classfile.Unknown;

import java.net.*;
import java.io.*;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class Client {

    private static final String HOST_NAME = "localhost";
    private static final int PORT = 4444;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static BufferedReader userIn;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        userIn = new BufferedReader(new InputStreamReader(System.in));
    }


    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startConnection(HOST_NAME, PORT);
            System.out.println("Connected");
            String userInput;
            while (((userInput = userIn.readLine()) != null)) {
                System.out.println("Sent " + userInput + " to server.");
            }
        } catch(UnknownHostException e){
            System.err.println("Unknown host: " + HOST_NAME);
        } catch (IOException e){
            System.err.println("Cannot connect to server, " + e.getMessage());
        }
    }
} //end class Client

