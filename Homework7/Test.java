/**
 * Test.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class Tests the class Address and LP.
 * It checks whether the objects list of objects of
 * these classes are sorted using Collections.sort.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
import java.util.* ;

public class Test {
	/**
	 * The main method
	 * This method contains list of objects of class Address and LP.
	 * Tests sorting of object.
	 * @param args	None
	 */
	public static void main(String[] args) {
        List<Address> aListOfAddresses 	= new ArrayList<Address>();
        List<LP> aListOfLPs = new ArrayList<LP>();

        aListOfAddresses.add(  new Address(1600, "Pennsylvania Avenue NW", "Washington", "DC", 20500) );
		aListOfAddresses.add(  new Address(11, "Wall Street", "New York", "NY", 10118) );
		aListOfAddresses.add(  new Address(102, "Lomb Memorial Drive", "Rochester", "NY", 14623) );
		aListOfAddresses.add(  new Address(1, "A", "B", "C", 1) );
		aListOfAddresses.add(  new Address(2, "A", "B", "C", 1) );
		aListOfAddresses.add(  new Address(3, "A", "B", "C", 1) );
		aListOfAddresses.add(  new Address(4, "A", "B", "C", 1) );

		aListOfLPs.add( new LP( 1960, "Deep Purple in Rock", "Deep Purple", (float)43.30, 7));
		aListOfLPs.add( new LP( 1973, "Dark Side of the Moon", "Pink Floyd ", (float)43.09, 10));
		aListOfLPs.add( new LP( 1, "A", "B ", (float)3, 4));
		aListOfLPs.add( new LP( 2, "A", "B ", (float)3, 4));
		aListOfLPs.add( new LP( 3, "A", "B ", (float)3, 4));
		aListOfLPs.add( new LP( 0, "A", "B ", (float)3, 4));
	
        Collections.sort(aListOfAddresses);
        Collections.sort(aListOfLPs);
        System.out.println(aListOfAddresses);
        System.out.println(aListOfLPs);
    }
}
