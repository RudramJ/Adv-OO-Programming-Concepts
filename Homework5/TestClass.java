/**
 * TestClass.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class TestClass tests the class
 * Array and Flexible to store an element in an array
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class TestClass {
	Array aArrayStr = new Array();
	Array aArray = new Array();	
	Flexible aFlexible 	= new Flexible();
	
	Integer one 		= Integer.valueOf(1);
	Integer two 		= Integer.valueOf(2);
	Integer three 		= Integer.valueOf(3);
	
	private static void error(String errorMessage)	{
		System.out.println("Error: " + errorMessage);
	}
	private void testArrayNull()	{
		if ( ! aArrayStr.add(null) )
			error("1: Adding null element");
		if ( ! aArrayStr.contains(null )	)
			error("2: null element not found");
		if ( ! aArrayStr.delete(null) )
			error("3: Deleting null element");
		if ( aArrayStr.delete(null) )
			error("4: Deleting null element");
	}
	private void testArrayAdd()	{
		if ( ! aArrayStr.add("a") )
			error("Adding element");
		aArrayStr.add("a");
		aArrayStr.add("a");
		aArrayStr.add("a");
		if ( aArrayStr.size() != aArrayStr.getMax() )
			error("Adding size failed");
		if ( aArrayStr.add("a") )
			error("Adding one too many");
		
	}
	private void testArrayDelete()	{
		aArrayStr.add(null);
		if ( aArrayStr.delete(null) )
			error("Deleting null element");
		if (  aArrayStr.delete(null) )
			error("Deleting non exciting null element");
		if ( ! aArrayStr.delete("a") )
			error("Deleting first element");
		aArrayStr.delete("a");  aArrayStr.delete("a");  aArrayStr.delete("a");
		if ( aArrayStr.delete("a") )
			error("Deleting one too many");
		
	}
	private void testAddArray()	{
		aArray.add(one);
		aArray.add(two);
		aArray.add(three);
		aArray.add(null);
		System.out.println(aArray);
	}
	
	private void testAddFlexible()	{
		aFlexible.add(one);
		aFlexible.add(one);
		aFlexible.add(two);
		aFlexible.add(two);
		aFlexible.add(three);
		aFlexible.add(null);
		aFlexible.delete(two);
		System.out.println(aFlexible);
		
	}
	private void test1()	{
		testArrayNull();
		testArrayAdd();
		testArrayDelete();
	}
	private void test2()	{
		testAddArray();
		testAddFlexible();
	}
	public static void main(String args[])	{
		new TestClass().test2();
	}
}
