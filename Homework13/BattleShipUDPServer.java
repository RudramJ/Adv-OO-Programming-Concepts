/**
 * BattleShipUDPServer.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * The class BattleShipUDPServer implements a version of two player
 * 3-dimensional BattleShip game using UDP socket connection.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

import java.io.*;
import java.util.Scanner;
import java.net.*;
import java.util.Random;

public class BattleShipUDPServer {

    // Variables to store the file contents
    private static int myWidth = 0;
    private static int myHeight = 0;
    private static String[][] myOceanGrid = null;
    private static String[][] displayOceanGrid = null;
    private static String  lineDelimiter		= "#";

    private static String hostName;
    private static int port;
    private static boolean first = false;
    private static boolean second = false;
    private static boolean playFirst = false;
    private static boolean whileStarting = true;

    private static DatagramSocket socket;
    private static DatagramPacket packetReceived;
    private static DatagramPacket packetToSend;
    private static byte[] bufReceiver = new byte[1024];
    private static byte[] bufSender = new byte[1024];

    private static String  battleFieldString 	= null;

    /**
     * Read the input file and gets the required input parameters like
     * width, height and ocean grid indication of battleship and water.
     * Reads width and height in any order and throws error if not found
     * Assuming the ocean grid provided to be correct.
     * @param theFile	Scanner object of input file
     */
    private static void getParameters(Scanner theFile) {
        // Parameters to read the width and height from file
        int onlyTwoLinesCount = 0;
        String[] getWidthHeight = new String[2];

        while( theFile.hasNext() && onlyTwoLinesCount < 2) {
            getWidthHeight[onlyTwoLinesCount] = theFile.nextLine();
            onlyTwoLinesCount++;
        }
        // Checks if keywords are present and takes takes input in any order, if not program will exit.
        if(getWidthHeight[0].contains("width") || getWidthHeight[0].contains("height")) {
            if(getWidthHeight[0].contains("width")) {
                String myParameters1 = getWidthHeight[0].replaceAll("\\s+", ".");
                myWidth = Integer.parseInt(myParameters1.substring(myParameters1.indexOf(".")+1));
            }
            else if(getWidthHeight[0].contains("height")) {
                String myParameters1 = getWidthHeight[0].replaceAll("\\s+", ".");
                myHeight = Integer.parseInt(myParameters1.substring(myParameters1.indexOf(".")+1));
            }
        }
        else {
            System.err.println("Please provide correct Input parameters");
            System.exit(0);
        }
        if(getWidthHeight[1].contains("width") || getWidthHeight[1].contains("height")) {
            if(getWidthHeight[1].contains("height")) {
                String myParameters1 = getWidthHeight[1].replaceAll("\\s+", ".");
                myHeight = Integer.parseInt(myParameters1.substring(myParameters1.indexOf(".")+1));
            }
            else if(getWidthHeight[1].contains("width")) {
                String myParameters1 = getWidthHeight[1].replaceAll("\\s+", ".");
                myWidth = Integer.parseInt(myParameters1.substring(myParameters1.indexOf(".")+1));
            }
        }
        else {
            System.err.println("Please provide correct Input parameters");
            System.exit(0);
        }

        if(myWidth == 0 || myHeight == 0) {
            System.err.println("Please provide correct width or height in the file");
            System.exit(0);
        }

        // Declaring the 2D string array to store the ocean string provided.
        myOceanGrid = new String[myHeight][myWidth];

        while( theFile.hasNext()) {

            for(int i = 0; i < myHeight; i++) {
                String str = theFile.nextLine().replaceAll("\\s+", ".");
                String[] temp = str.split("\\.");
                for(int j = 0; j < myWidth; j++) {
                    myOceanGrid[i][j] = temp[j+1];
                }
            }
        }
        initOceanGrid();
    }
    /**
     * This method is invoked to initialize the ocean grid with "."
     * for displaying
     */
    private static void initOceanGrid() {
        displayOceanGrid = new String[myHeight][myWidth];
        for(int i = 0; i < myHeight; i++) {
            for(int j = 0; j < myWidth; j++) {
                displayOceanGrid[i][j] = ".";
            }
        }
    }
    /**
     * Starts the game
     * Reads the input from command line and prints the result accordingly
     */
    private static void startMyGame() {
        // Declaring parameters for getting user input and displaying the result.
        Scanner userInput = new Scanner(System.in);
        int getAlternateInput = 0;
        int columnLocation = 0;
        int rowLocation = 0;
        int canPrint = 0;
        printGame();
        waitForOtherPlayer();
        System.out.println("column coordinate (0 <= column <"+ myWidth+"): ");
        while(userInput.hasNext()) {
            String myInput = userInput.nextLine();
            if(getAlternateInput == 0) {
                columnLocation = checkForInput(myInput, getAlternateInput);

                if(columnLocation >= 0) {
                    System.out.println("row coordinate (0 <= row <"+ myHeight+"): ");
                    getAlternateInput = 1;
                }
                else {
                    System.out.println("column coordinate (0 <= column <"+ myWidth+"): ");
                }
            }
            else if(getAlternateInput == 1) {
                rowLocation = checkForInput(myInput, getAlternateInput);

                if(rowLocation >= 0) {
                    canPrint = 1;
                    getAlternateInput = 0;
                }
                else {
                    System.out.println("row coordinate (0 <= row <"+ myHeight+"): ");
                }
            }
            if(canPrint == 1) {
                canPrint = 0;

                if(myOceanGrid[rowLocation][columnLocation].equals("w")) {
                    displayOceanGrid[rowLocation][columnLocation] = "w";
                    System.out.println("NOT HIT");
                }
                else {
                    String myId = myOceanGrid[rowLocation][columnLocation];
                    for(int i = 0; i < myHeight; i++) {
                        for(int j = 0; j < myWidth; j++) {
                            if(myOceanGrid[i][j].equals(myId)) {
                                displayOceanGrid[i][j] = "x";
                            }
                        }
                    }
                    System.out.println("HIT");
                }

                printGame();
                if(checkForEndGame()) {
                    System.out.println("GAME OVER! All the boats have been HIT");
                    break;
                }
                else {
                    waitForOtherPlayer();
                    System.out.println("column coordinate (0 <= column <"+ myWidth+"): ");
                }
            }
        }
        userInput.close();
        sendData("exit");
        System.out.println("You won!!");
        exitGame();
    }
    /**
     * This method is invoked when all battleships are HIT by the player
     * This method exits the JVM
     */
    private static void exitGame() {
        System.exit(1);
    }
    /**
     * This method is used when the player has to wait till
     * the opponent plays. Acknowledgement is sent other player
     * after its turn or when all boats are HIT.
     */
    private static void waitForOtherPlayer() {
        if(whileStarting) {
            whileStarting = false;
            if(!playFirst) {
                System.out.println("Waiting for other player...");
                String acknowledgement = readData();
                System.out.println("Your turn");
            }
        }
        else {
            sendData("@");
            System.out.println("Waiting for other player...");
            String acknowledgement = readData();
            if(acknowledgement.equals("exit")) {
                System.out.println("Other player Won! You lost!");
                exitGame();
            }
            System.out.println("Your turn");
        }
    }
    /**
     * Check if the column and row coordinates input is correct or not
     * @param input		User input
     * @param id		Whether the input is for row or column coordinate.
     * @return			Input, If the input is in range or not.
     * 					Else, return -1.
     */
    private static int checkForInput(String input, int id) {
        if(!input.isEmpty()) {
            if(Character.isDigit(input.charAt(0))) {
                int myValue = Integer.parseInt(String.valueOf(input.charAt(0)));
                if(id == 0) {
                    if(myValue >= 0 && myValue < myWidth) {
                        return myValue;
                    }
                }
                else if(id == 1){
                    if(myValue >= 0 && myValue < myHeight) {
                        return myValue;
                    }
                }
            }
        }
        return -1;
    }
    /**
     * Checks if all the battleships are Hit.
     * @return		If all battleships hit or not
     */
    private static boolean checkForEndGame() {
        for(int i = 0; i < myHeight; i++) {
            for(int j = 0; j < myWidth; j++) {
                if(!myOceanGrid[i][j].equals("w")) {
                    if(!displayOceanGrid[i][j].equals("x")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * This method prints the 3-D Ocean Grid
     */
    private static void printGame() {
        System.out.print("   ");
        for(int k = 0; k < myWidth; k++) {
            System.out.print(k+" ");
        }
        System.out.println("--->");
        for(int i = 0; i < myHeight; i++) {
            System.out.print(i+": ");
            for(int j = 0; j < myWidth; j++) {
                System.out.print(displayOceanGrid[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("x indicates a hit.");
        System.out.println("w indicates a miss, but you know now there is water.");
    }
    /**
     * This method is used to read the command line arguments
     * and initialize the corresponding parameters.
     * @param args  Input Arguments
     */
    private static void parseArgs(String args[]) {
        int valid = 0;
        if(args.length == 7) {
            if(args[0].equals("-hostname")) {
                hostName = args[1];
                if(args[2].equals("-port")) {
                    port = Integer.parseInt(args[3]);
                    if(args[4].equals("-first") || args[4].equals("-second")) {
                        if(args[4].equals("-first")) {
                            first = true;
                        }
                        else {
                            second = true;
                        }
                        if(args[5].equals("-ocean")) {
                            readBattleFieldFile(args[6]);
                        }
                        else valid = 1;
                    }
                    else valid = 1;
                }
                else valid = 1;
            }
            else valid = 1;
        }
        else valid = 1;

        if(valid == 1) {
            System.err.println("Enter Valid Arguments!");
            System.exit(0);
        }
    }
    /**
     * This method is invoked to initialize and setup the Datagram connection based on first(server)
     * and second(client)
     * The oceans are read in this method
     */
    private static void setUpIO() {
        if ( first )	{
            try {
                socket = new DatagramSocket(port);
                packetReceived = new DatagramPacket(bufReceiver, bufReceiver.length);
                socket.receive(packetReceived);
                String receivedFile = new String(packetReceived.getData());
                readBattleFieldFromString(receivedFile.trim());

                InetAddress myIp = InetAddress.getLocalHost();
                bufSender = battleFieldString.getBytes();
                packetToSend = new DatagramPacket(bufSender, bufSender.length, myIp, packetReceived.getPort());
                socket.send(packetToSend);

            } catch ( Exception e )	{
                System.err.println("Error connecting to Client");
                System.exit(0);
            }
        } else {
            try {
                socket = new DatagramSocket();
                bufSender = battleFieldString.getBytes();
                InetAddress myIp = InetAddress.getLocalHost();
                packetToSend = new DatagramPacket(bufSender, bufSender.length, myIp, port);
                socket.send(packetToSend);


                packetReceived = new DatagramPacket(bufReceiver, bufReceiver.length);
                socket.receive(packetReceived);
                String receivedFile = new String(packetReceived.getData());
                readBattleFieldFromString(receivedFile.trim());


            } catch ( Exception e )	{
                System.err.println("Error connecting to Server");
                System.exit(0);
            }
        }
    }
    /**
     * This method reads the string value of input file and get the input
     * parameters
     * @param theBattleFieldInStringForm  String value of input file
     */
    private static void readBattleFieldFromString(String theBattleFieldInStringForm) {

        if(theBattleFieldInStringForm != null) {
            theBattleFieldInStringForm = theBattleFieldInStringForm.replaceAll(lineDelimiter, "\n");
            Scanner battleFieldParser = new Scanner(theBattleFieldInStringForm);
            getParameters(battleFieldParser);
        }
        else {
            System.err.println("Error reading battle field from String");
            System.exit(0);
        }
    }
    /**
     * This method creates a string value of input file
     * @param fileName   Name of input file
     */
    private static void readBattleFieldFile(String fileName) {		// new
        if ( fileName.equals("exit")) {
            System.exit(0);
        }
        try {
            battleFieldString = "";
            Scanner battleFieldParser = new Scanner(new File(fileName));
            while ( battleFieldParser.hasNextLine() )	{
                battleFieldString += battleFieldParser.nextLine() + lineDelimiter ;
            }

        } catch (FileNotFoundException e) {
            System.err.println("Can't find that file! Try Again.");
        }

    }
    /**
     * This method is used to send the data from the socket using
     * datagram packet
     * @param theData   Data to send into socket
     */
    private static void sendData(String theData) {
        try {
            if(first) {
                InetAddress myIp = InetAddress.getLocalHost();
                bufSender = theData.getBytes();
                packetToSend = new DatagramPacket(bufSender, bufSender.length, myIp, packetReceived.getPort());
                socket.send(packetToSend);
            }
            else {
                bufSender = theData.getBytes();
                InetAddress myIp = InetAddress.getLocalHost();
                packetToSend = new DatagramPacket(bufSender, bufSender.length, myIp, port);
                socket.send(packetToSend);
            }
        } catch ( Exception e )	{
            System.err.println("Error while send data");
            System.exit(0);
        }
    }
    /**
     * This method returns the data by reading from the datagram
     * packet
     * @return   Data received on Socket
     */
    private static String  readData() {
        String rValue = "";
        try {
            byte[] bufferReceive = new byte[1024];
            packetReceived = new DatagramPacket(bufferReceive, bufferReceive.length);
            socket.receive(packetReceived);
            String receivedFile = new String(packetReceived.getData());
            rValue = receivedFile.trim();

        } catch ( Exception e )	{
            System.err.println("Error while read data");
            System.exit(0);
        }
        return rValue;
    }
    /**
     * This method determines whether client play first or server
     */
    private static void whoPlaysFirst() {
        if ( first ) {
            String theOtherOnes =  readData();
            if(theOtherOnes.equals("1")) {
                playFirst = true;
            }
        }
        else {
            Random rand = new Random();

            int randomNumber = rand.nextInt(9);
            if(randomNumber%2 == 0) {
                sendData("1");
            }
            else {
                playFirst = true;
                sendData("2");
            }
        }
    }
    /**
     * This method sequentially calls the all the method for playing the game
     * @param args  Input Arguments
     */
    private static void playTheGame(String[] args)	{
        parseArgs(args);
        setUpIO();
        whoPlaysFirst();
        startMyGame();
    }

    /**
     * The main function.
     * Passes the arguments to the method playTheGame to get input parameters.
     *
     * @param args  -hostname hostname -port port -first/-second -ocean oceanName
     */
    public static void main( String[] args ){
        playTheGame(args);
    }
}

