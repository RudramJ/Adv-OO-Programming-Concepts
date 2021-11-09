/**
 * QuestionMark.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class QuestionMark returns the output
 * of various functions using the ternary operator for
 * elegance.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class QuestionMark {
	  /*
	   * Returns true if first integer is greater
	   */
	  public static boolean aGreaterB( int a, int b )	{
		return a > b ? true : false ;
	  }
	  
	  public static int findMaximum( int a, int b )	{
		return a > b ?a : b ;
	  }
	  
	  /*
	   * Returns maximum of 4 numbers
	   */
	  public static int findMaximum( int a, int b, int c, int d )	{
		return a > c ? findMaximum( a, b ) : findMaximum( c, d );
	  }
	  
	  /*
	   * returns a/b if a and b are not 0
	   */
	  
	  public static int leftToRight( int a, int b )	{
		return a!=0?b!=0?a/b:-1:0;
	  }
	 
	  public static void main( String[] args ) {
		int a = 5;
		int b = 10;
		int c = 20;
		int d = 21;
		System.out.println("aGreaterB(" + a + "," + b + ") = " + aGreaterB(a, b ) );
		System.out.println("findMaximum(" + a + "," + b + ") = " + findMaximum(a, b ) );
		System.out.println("findMaximum(" + a + ", " + b + ", " + c + ", " + d + " ) = " + 
				findMaximum(a, b, c, d ) );
		a = 0;
		b = 0;
		System.out.println("leftToRight(" + a++ + "," + b++ + ") = " + leftToRight(a, b ) );
		System.out.println("leftToRight(" + --a + "," + b + ") = " + leftToRight(a, b ) );
	  }
	}
