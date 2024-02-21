package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
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
    public static void exploreMenu(PromptDisplay displayPrompt,PromptController controlPrompt, Player player,Inventory storage) {
        displayPrompt.display("Exploration");
        int promptChoice = controlPrompt.answerPrompt(4);

        switch(promptChoice)
        {
            case 1:
                // Continue Forward
                displayPrompt.display("Combat");
                break;
            case 2:
                // Check Status
                System.out.println("Name: "+player.getName());
                System.out.println("Class: "+player.getPlayerClass());
                System.out.println(" ");
                System.out.println(player.getAllStats());
                System.out.println("\nWeapons and Tools:");
                //System.out.println(storage.printWeapons());

                displayPrompt.display("Check");
                promptChoice = controlPrompt.answerPrompt(1);
                exploreMenu(displayPrompt,controlPrompt,player,storage);
                break;
            case 3:
                System.out.println("SPELLS GO HERE");
                break;
            case 4:
                System.out.println("Items:");
                System.out.println(storage.printItems());
                System.out.println("----------------");
                displayPrompt.display("Check");
                promptChoice = controlPrompt.answerPrompt(1);
                exploreMenu(displayPrompt,controlPrompt,player,storage);
                break;
            default:
                break;
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
        displayText.classInfo(player.getPlayerClass());
        /*
        System.out.println("Greetings " + player.getName() + ", you are a " + player.getPlayerClass() + " and your stats are: ");
        System.out.println("----------------");
        System.out.println(player.getAllStats());
        System.out.println("----------------");
        */

        Inventory storage = new Inventory(player.getPlayerClass());
        ArrayList<Items> playerInventory = storage.getInventory();

        // For each loop to go through starting inventory and equip weapons
        for (Items item : playerInventory)
        {
            if (item instanceof Weapons)
            {
                player.setCurrentWeapon((Weapons) item);
            }
        }

        displayText.display("Introduction");
        System.out.println("[1] - Enter the Dungeon" + "\n[0] - Quit Game");
        System.out.println("----------------");
        promptChoice = controlPrompt.answerPrompt(2);

        do {
            if (promptChoice == 1) {
                exploreMenu(displayPrompt, controlPrompt, player, storage);
            } else if (promptChoice == 0) {
                System.out.println("QUIT GAME");
                break; // stop game when 0 is entered
            } else {
                System.out.println("Invalid Response");
            }
            promptChoice = controlPrompt.answerPrompt(2);
        } while (promptChoice != 0 && promptChoice != 1);

        // now we're in the dungeon


    }

}
 //end class Server
