/**
 * NumberCounter.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class reads and processes the file containing data
 * of the NY-10 lottery winning numbers.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

import java.io.*;
import java.util.zip.GZIPInputStream;

public class NumberCounter {
	// Global Variable
	private static int [] numberCount = new int[81];

	/**
	 * This method is called for printing the output
	 */
	private static void print() {
		for(int index = 1; index < numberCount.length; index += 4) {
			System.out.print(index+":"+numberCount[index]+"\t\t");
			System.out.print((index+1)+":"+numberCount[index+1]+"\t\t");
			System.out.print((index+2)+":"+numberCount[index+2]+"\t\t");
			System.out.print((index+3)+":"+numberCount[index+3]);
			System.out.println();
		}
	}
	
	/**
	 * Function to get the stream of numbers per line
	 * and increase the frequency of the indexes in the array
	 * @param lineOfFile
	 */
	private static void getLines(String lineOfFile) {
		lineOfFile = lineOfFile.substring(lineOfFile.indexOf(",") + 1);
		String[] lineCharacter = lineOfFile.split("\\s");

		for(int index = 0 ; index < lineCharacter.length; index++) {
			numberCount[Integer.parseInt(lineCharacter[index])]++;
		}
	}
	
	/**
	 * Main method
	 * One line Try with resources block is implemented to read and process
	 * the gzipped or normal file to get the count of numbers in NY-10 lottery
	 * @param args None/FileName
	 */
	public static void main(String[] args) {
		
		try (
				BufferedReader input = new BufferedReader(args.length == 0 ?
						new InputStreamReader(System.in) :
						new InputStreamReader( ( args[0].endsWith(".gz") ?
								new GZIPInputStream(new FileInputStream(args[0])) :
								new FileInputStream(args[0]) )))
		) {
			String line = input.readLine();
			while((line = input.readLine()) != null) {
				getLines(line);
			}
		} catch ( IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("No argument provided");
			System.exit(0);
		}
		print();
	}
}
