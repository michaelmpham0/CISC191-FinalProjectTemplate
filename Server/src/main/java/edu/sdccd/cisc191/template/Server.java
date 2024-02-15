package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program sends the current time to
 * the connected socket.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example).  Note that this server processes each connection
 * as it is received, rather than creating a separate thread
 * to process the connection.
 */

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            CustomerRequest request = CustomerRequest.fromJSON(inputLine);
            CustomerResponse response = new CustomerResponse(request.getId(), "Jane", "Doe");
            out.println(CustomerResponse.toJSON(response));
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    // the old main method from the template
    /*
    public static void oldMain(String[] args) {

        Server server = new Server();
        try {
            server.start(4444);
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    */
    public static void exploreMenu(PromptDisplay displayPrompt,PromptController controlPrompt,int promptChoice, Player player) {
        displayPrompt.display("Exploration");
        promptChoice = controlPrompt.answerPrompt(2);
        if (promptChoice == 1)
        {
            displayPrompt.display("Combat");
        }
        else if (promptChoice == 2)
        {
            System.out.println("Greetings " + player.getName() + ", you are a " + player.getPlayerClass() + " and your stats are: ");
            System.out.println("----------------");
            System.out.println(player.getAllStats());
            System.out.println("----------------");
            displayPrompt.display("Check");
        }
    }

    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        TextDisplay displayText = new TextDisplay();
        PromptDisplay displayPrompt = new PromptDisplay();
        PromptController controlPrompt = new PromptController();

        int promptChoice;
        System.out.println("----------------");

        Player player = new Player();
        player.setUp();

        System.out.println("Greetings " + player.getName() + ", you are a " + player.getPlayerClass() + " and your stats are: ");
        System.out.println("----------------");
        System.out.println(player.getAllStats());
        System.out.println("----------------");


        displayText.display("Introduction");
        displayPrompt.display("Continue");promptChoice = controlPrompt.answerPrompt(2);



        if (promptChoice == 1)
        {
            exploreMenu();
        }
        else
        {
            System.out.println("QUIT GAME");
        }
    }

}
 //end class Server
