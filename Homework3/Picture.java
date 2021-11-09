/**
 * Picture.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class Picture implements a version of word guessing game
 * called hangman using the Scanner class.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

import java.util.Scanner;
import java.util.Vector;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

public class Picture {
	// Input Parameters
	final static String myFileLocation = "D:\\MyWork\\Programs\\Java\\RIT\\Homework3\\src\\";
	final static int MAX_TRIES = 9;
	// Variables to store the file contents and track them
	static Vector <String> myWordVector = new Vector<String>();
	static StringBuilder progressSelectedWord = new StringBuilder();
	static StringBuilder copyOfWord = new StringBuilder();
	static StringBuilder myPictureList = new StringBuilder();
	static int lengthOfWord = 0;
	
	/**
	 * Returns the elements of file in vector
	 * @param wordFile	Scanner object of file input
	 * @return			String Vector of contents of file
	 */
	public static Vector<String> getWordList( Scanner wordFile ) {
		Vector<String> words = new Vector<>();
		while( wordFile.hasNextLine() ) {
			words.add( wordFile.nextLine() );
		}
		return words;
	}
	/**
	 * This method initializes the progressString.
	 * Usually called before guessing any word.
	 */
	public static void initializeProgressPrint() {
		progressSelectedWord.setLength(0);
		for( int i = 0; i < lengthOfWord; i++ ) {
			progressSelectedWord.append( "." );
		}
	}
	/**
	 * This method updates a stringbuilder to show the 
	 * progress of user of guessing the word.
	 * @param inputVar	User input
	 */
	public static void updateProgressPrint( String inputVar ) {
		char a = inputVar.charAt(0);
		
		for( int k = 0; k < copyOfWord.length(); k++ ) {
			if( progressSelectedWord.charAt(k) == '.' ) {
				if( copyOfWord.charAt(k) == a ) {
					progressSelectedWord.setCharAt( k, inputVar.charAt(0) );		
					break;
				}
			}			
		}
	}	
	
	/**
	 * Returns the random word from the vector
	 * @return	Random word from the vector
	 */
	public static StringBuilder returnRandomWord() {
		StringBuilder reString = new StringBuilder();
		int i = getRandomWordIndex(myWordVector);
		reString.append( myWordVector.elementAt(i) );
		myWordVector.remove(i);
		return reString;
	}
	
	/**
	 * This method compares the characters of word with user input.
	 * And returns the index accordingly
	 * @param myInput			User input character
	 * @param requiredWord		Word choose at random for compare 
	 * @return					Positive index if character found else negative
	 */
	public static int compareWithWord( String myInput, StringBuilder requiredWord ) {
		
		if( !myInput.isEmpty() ) {
			for(int i = 0; i < requiredWord.length(); i++) {
				if(myInput.charAt(0) == requiredWord.charAt(i)) {		
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * This method returns the index for random number 
	 * with respect to number of words in word file.
	 * We are using Random class for that.
	 * @param myList 	vector of word file
	 * @return			random index number
	 */
	public static int getRandomWordIndex( Vector<String> myList ) {
		Random rand = new Random();
		int randomIndex = 0;
		
		randomIndex = rand.nextInt(myWordVector.size());
		return randomIndex;
	}
	
	/**
	 * This method prints the required output.
	 * @param pictureToPrint	how much of Picture file to show
	 * @param wordSizeLeft		length of word left 
	 * @param triesCount		Number of wrong tries
	 */
	public static void printFunction( StringBuilder pictureToPrint, int wordSizeLeft, 
			int triesCount ) {
		StringBuilder toPrint = new StringBuilder(pictureToPrint);
		int count = 0;
		
		for( int i = 0; i < toPrint.length(); i++ ) {
			
			if( toPrint.charAt(i) != '\n' ) {
				if( count < wordSizeLeft ) {
					toPrint.setCharAt(i, '.');
					count++;
				}
				else {
					count = 0;
				}
			}
		}
		System.out.println( toPrint );		
		// "< 0" indicates all the tries used up or correct word guessed, so print required word
		if( triesCount < 0 ) {
			System.out.println("The word was: " + copyOfWord);
		}
		else {			
			System.out.print(triesCount + ": ");
			System.out.println(progressSelectedWord);
		}
	}
	/**
	 * The main function.
	 * Read all the files and stores in the corresponding variables
	 * Initializes and outputs the result with respect to user input for
	 * the hangman game.
	 * args[0] contains words.txt and args[1] contains batman.txt
	 * @param args	
	 * @throws FileNotFoundException
	 */
	public static void main( String[] args ) throws FileNotFoundException {
    	// Constants to get the required result
		StringBuilder mySelectedWord = new StringBuilder();
		int numberOfTries = 0;
		int isGameOver = 0;
		// Reading all the files using Scanner Class
		File myWordFile = new File( myFileLocation+args[0] );
		Scanner readWord = new Scanner( myWordFile );
		
		Scanner myGuesses = new Scanner( System.in );
		File myPictureFile = new File( myFileLocation+args[1] );
		
		Scanner readPicture = new Scanner( myPictureFile );
		// Gets the vector of equivalent word file
		myWordVector = getWordList( readWord );
		readWord.close();		
		// Appending the picture file with new line
		while(readPicture.hasNextLine()) {
			myPictureList.append( readPicture.nextLine() );
			myPictureList.append("\n");
		}
		readPicture.close();
		
		// Number of times the game will be played
		isGameOver = myWordVector.size();
		// Initializing parameters required before guessing
		copyOfWord.setLength(0);
		copyOfWord.append( returnRandomWord() );
		mySelectedWord.append( copyOfWord );
		lengthOfWord = mySelectedWord.length();
		initializeProgressPrint();

		printFunction( myPictureList, mySelectedWord.length(), numberOfTries );
		/** The main loop, gets the user input char and compare it with random word
		  	selected and displays the picture accordingly */
		while( myGuesses.hasNext() ) {
			String userInput = myGuesses.nextLine();
			int checkOutput = compareWithWord( userInput, mySelectedWord );
			// Goes inside if the user input matches characters with words
			if( checkOutput >= 0 ) {
				mySelectedWord.deleteCharAt( checkOutput );
				updateProgressPrint( userInput );
				// Check if whole word is guessed
				if( mySelectedWord.length() == 0 ) {					
					isGameOver--;
					printFunction( myPictureList, mySelectedWord.length(), -1 );
					
					if( !myWordVector.isEmpty() ) {
						numberOfTries = 0;
						copyOfWord.setLength(0);
						copyOfWord.append( returnRandomWord() );
						mySelectedWord.append( copyOfWord );
						lengthOfWord = mySelectedWord.length();
						initializeProgressPrint();
					}					
				}		
			}
			else {
				if( numberOfTries < MAX_TRIES ) {
					numberOfTries++;
				}
				else {
					isGameOver--;
					printFunction( myPictureList, mySelectedWord.length(), -1 );
					
					if( !myWordVector.isEmpty() ) {
						numberOfTries = 0;
						copyOfWord.setLength(0);
						copyOfWord.append( returnRandomWord() );
						mySelectedWord.setLength(0);
						mySelectedWord.append( copyOfWord );
						lengthOfWord = mySelectedWord.length();
						initializeProgressPrint();
					}
				}				
			}				
			// Check for game-over or else print the resulting result.
			if( isGameOver == 0 ) {
				break;
			}		
			else
			{
				printFunction( myPictureList, mySelectedWord.length(), numberOfTries );
			}
		}
		myGuesses.close();
		System.out.println(" No more words left to guess\r\n" + 
				                    "I hope you enjoyed the game, bye!");
	}	
	
}