/**
 * StringIntegerArrays.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class StringIntegerArrays
 * uses the String functions to 
 * answer various questions asked in the assignmnet
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

import java.util.Arrays;

public class StringIntegerArrays {
	public static void main(String args[]) {
		String aString;
		String bString;
		String cString;
		String dString;
		String eString;
		//Checks whether any arguments is provided or not
		if (args.length == 0) {
			aString = "2513";
            bString = "2513";
            cString = "ABCDECFG";
            dString = "abcDECFG";
            eString = aString + ( bString + cString ) + dString;
		} else {
			aString = "213";
			bString = "2513";
			cString = "ABCDECFGT";
			dString = "abcDECFG";
			eString = bString + (bString + cString) + dString;
		}
		//Arrays.sort method sorts the String in ascending order
		char[] sorted= eString.toCharArray();
		Arrays.sort(sorted);
		char[] asort=aString.toCharArray();
		Arrays.sort(asort);
		int first_index=Integer.parseInt(String.valueOf(aString.charAt(0)));
		int last_index=Integer.parseInt(String.valueOf(aString.charAt(aString.length()-1)));
		int lowest=Integer.parseInt(String.valueOf(asort[0]));
		int second_lowest=Integer.parseInt(String.valueOf(asort[1]));
		
		
		System.out.println("I.\t" + aString.equals(bString));
		
		System.out.println( "II.\t" + cString.equalsIgnoreCase(dString) );
		
		System.out.println(("III.\t"+aString.substring(0,1)));
		
		System.out.println("IV.\t"+
						   aString.substring(aString.length()-2,aString.length()));
		System.out.println("V.\t"+cString.substring(0,cString.indexOf("C")+1));

		System.out.println("VI.\t"+dString.substring(first_index,last_index));

		System.out.println("VII.\t"+dString.substring(lowest,second_lowest));           
		
		System.out.println("VIII.\t"+String.valueOf(sorted));
		
		System.out.println("IX.\t"+eString.contains(aString));
		
		System.out.println("X.\t"+cString.toLowerCase().contains(dString.toLowerCase()));

	}
}
