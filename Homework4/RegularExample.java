/**
 * RegularExample.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The RegularExample accepts a file and
 * checks whether the contents match the regular
 * expression in the String array allPatternsToTest.
 * The array also contains a verbal explanation of the same
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

import java.util.*;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

public class RegularExample {
	//String array stores a Pattern string and the corresponding explanation for that pattern
	
	static String[] allPatternsToTest = { "^a\\d+","Start with a followed by one or more digits",
			"[\\w&&\\D&&[^aeiou]]*[aA][\\w&&\\D\\D&&[^aeiou]]*[eE]"
					+ "[\\w&&\\D&&[^aeiou]]*[iI][\\w&&\\D&&[^aeiou]]*[oO][\\w&&\\D&&[^aeiou]]*[uU][\\w&&\\D&&[^aeiou]]*"
					+ "[\\w&&\\D&&[^aeiou]]*","contains aeiou vowels only once in order",
			"^a[1-3]{3}","starts with ’a’ followed by 3 digits in the range between 1 and 3 only",
			"^a[1-3]{3,}","starts with ’a’ followed by least 3 digits in the range between 1 and 3 only",
			"^a[8-9]{1,2}",
			"starts with ’a’ followed by between 1 and 2 digits in the range between 8 and 9 only",
			"[a-z&&[^hpb]]*",
			"includes only lower case characters, but not the character ’h’, ’p’, and ’b’" };
	/**
	 * Main Method checks the 
	 * @param args
	 */
	public static void main(String []args){
		
		String delimeterSymbol = "";
		String fileName = "";
		Scanner scannerObject = null;
		
		scannerObject = check_input(args, delimeterSymbol, fileName, scannerObject);
		checkForResults( scannerObject );
	}
	
	/**
	 * Checks the file for it input and returns the scanner object reference
	 * @param args
	 * @param delimeterSymbol
	 * @param fileName
	 * @param scannerObject
	 * @return
	 */
	public static Scanner check_input(String[] args, String delimeterSymbol,
									String fileName, Scanner scannerObject) {
		try {
			for( int index=0;index<args.length;index++ ) {
				
				if( args[index].equals( "-d" ) ) {
					delimeterSymbol=args[index+1];
				}
				else if( args[index].equals("-input") ) {
					fileName=args[index+1];
				}
			}
			
			if( delimeterSymbol.equals("*") || 
					delimeterSymbol.equals("+") || 
					delimeterSymbol.equals("?") ) {
				scannerObject=new Scanner( new File( fileName ) )
								.useDelimiter( "\\"+delimeterSymbol );
			}
			else {
				scannerObject=new Scanner( new File( fileName ) )
								.useDelimiter( delimeterSymbol );
			}
			if(!scannerObject.hasNext()) {
				System.out.println( "File is empty" );
			}
			
		}
		catch ( FileNotFoundException e )	{
			System.err.println( "Arguments could not be parsed." );
			System.err.println( "Arguments:file name = "+fileName+
								" delimeter = "+delimeterSymbol );
			System.exit( 1 );
		}
		return scannerObject;
	}
	
	/**
	 * This method checks the input of the file against
	 * the patterns to test and outputs the result.
	 * @param sc
	 */
	private static void checkForResults( Scanner sc ) {
		while( sc.hasNext() ) {
			String input=sc.next();
			
			if( !input.equals( "\n" ) ) {	
				System.out.println( "--------------------------------------------------------" );
				System.out.println( "Input: "+input+"=" );
				for( int index=0;index<allPatternsToTest.length;index=index+2 ) {
					if( Pattern.matches(allPatternsToTest[index], input) ) {
						System.out.println( "This regular expression \""+allPatternsToTest[index]+
										"\" matches the following input:"+input+" =" );
						System.out.println( "\tverbal explanation "+allPatternsToTest[index+1] );
					}
				}
			}
		}
		sc.close();
	}
}
