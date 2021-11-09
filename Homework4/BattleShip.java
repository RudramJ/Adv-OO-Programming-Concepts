/**
 * BattleShip.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class BattleShip implements a version of one person
 *  3-dimensional BattleSship game.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

import java.io.File;
import java.util.Scanner;

public class BattleShip {
	// Scanner object to read user input
	private static Scanner userInput = new Scanner(System.in);
	// Variables to store the file contents
	static int myWidth = 0;
	static int myHeight = 0;
	
	/**
	 * Read the input file and gets the required input parameters like
	 * width, height and ocean grid indication of battleship and water.
	 * Reads width and height in any order and throws error if not found
	 * Assuming the ocean grid provided to be correct.
	 * @param theFile	Scanner object of input file
	 * @return			2D array of ocean grid
	 */
	public static String[][] getParameters(Scanner theFile) {
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
			System.exit(1);
			
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
			System.exit(1);
		}
		
		if(myWidth == 0 || myHeight == 0) {
			System.out.println("Please provide correct width or height in the file");
			System.exit(1);
		}
		
		// Declaring the 2D string array to store the ocean string provided.
		String[][] returnOceanGrid = new String[myHeight][myWidth];
		
		while( theFile.hasNext()) {
			
			for(int i = 0; i < myHeight; i++) {
				String str = theFile.nextLine().replaceAll("\\s+", ".");
				String[] temp = str.split("\\.");
				for(int j = 0; j < myWidth; j++) {						
					returnOceanGrid[i][j] = temp[j+1];
				}	
			}
		}
		return returnOceanGrid;
	}
	/**
	 * Gets the input parameters from the input file.
	 * Reads the input from command line and prints the result accordingly
	 * @param args	Arguments(File path and user input)
	 */
	public static void startMyGame(String args[]) {
		// Creating a reference 2D string to our input
		String[][] myOceanGrid = new String[myHeight][myWidth]; 	
		String myFileName = "";
		try {
			System.out.print("battleField file name: ");
			myFileName = userInput.next();
			
			Scanner myFile  = new Scanner( new File(myFileName) );
			myOceanGrid = getParameters(myFile);
			myFile.close();
		}
		catch (Exception e ){
			System.err.println("Arguments could not be parsed.");
			System.err.println("Please verify the input file.");
			e.printStackTrace();
			System.exit(1);
		}	
		// Declaring Ocean grid which is displayed to user.
		String[][] displayOceanGrid = new String[myHeight][myWidth];
		for(int i = 0; i < myHeight; i++) {
			for(int j = 0; j < myWidth; j++) {
				displayOceanGrid[i][j] = ".";	
			} 
		}
		// Declaring parameters for getting user input and displaying the result.
		int getAlternateInput = 0;
		int columnLocation = 0;
		int rowLocation = 0;
		int canPrint = 0;
		printGame(displayOceanGrid);
		System.out.println("column coordinate (0 <= column <"+ myWidth+"): ");
		// Gets the user input and display the result accordingly
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
				
				printGame(displayOceanGrid);
				if(checkForEndGame(displayOceanGrid, myOceanGrid)) {
					System.out.println("GAME OVER! All the boats have been HIT");
					break;
				}
				else {					
					System.out.println("column coordinate (0 <= column <"+ myWidth+"): ");
				}
			}			
		}
		userInput.close();
	}
	/**
	 * Check if the column and row coordinates input is correct or not
	 * @param input		User input
	 * @param id		Whether the input is for row or column coordinate.
	 * @return			Input, If the input is in range or not.
	 * 					Else, return -1.
	 */
	public static int checkForInput(String input, int id) {
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
	 * @param gridToShow		current ocean grid
	 * @param gridToCompare		Original ocean grid
	 * @return					If all battleships hit or not
	 */
	public static boolean checkForEndGame(String[][] gridToShow, String[][] gridToCompare) {
		for(int i = 0; i < myHeight; i++) {
			for(int j = 0; j < myWidth; j++) {
				if(!gridToCompare[i][j].equals("w")) {
					if(!gridToShow[i][j].equals("x")) {
						return false;
					}		
				}
			}
		}
		return true;
	}
	/**
	 * This method prints the 3-D Ocean Grid
	 * @param toPrint 2-D array containing the location of
	 * 				  water and battleships in the ocean
	 */
	public static void printGame(String[][] toPrint) {
		System.out.print("   ");
		for(int k = 0; k < myWidth; k++) {
			System.out.print(k+" ");
		}
		System.out.println("--->");
		for(int i = 0; i < myHeight; i++) {
			System.out.print(i+": ");
			for(int j = 0; j < myWidth; j++) {
				System.out.print(toPrint[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("x indicates a hit.");
		System.out.println("w indicates a miss, but you know now there is water.");
	}
	
	/**
	 * The main function.
	 * Passes the arguments to the method startMyGame to get input parameters.
	 * 
	 * @param args
	 */
	public static void main( String[] args ){
		startMyGame(args);
	}
}		
		
		