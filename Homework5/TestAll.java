/**
 * TestAll.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class TestAll Tests the 
 * source files of HW 5.3 and contains
 * the explanation of questions asked in the program. 
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class TestAll	{
	// draw the class diagram, including interface
	// why are these declarations legal?

	/*1. Since class C1 implements the Interface I1, the interface reference of I1 can be typecasted 
	*	to the object of class C1
	*/
	static I1	anI1	=	new C1();
	/*2. Since class C3 implements the Interface I2, the interface reference of I2 can be typecasted
	*	 to the class object of C3
	*/
	static I2	anI2	=	new C3();
	/*3. Since class C2 extends Abstract class which implements the Interface I1, the interface reference 
	*	of I1 can be typecasted to the object of class C2
	*/
	static I1	anI1a	=	new C2();
	/*4. Since class C4 implements both Interfaces I1 and I2, the interface reference of I1 can be 
	*	typecasted to the object of class C4
	*/
	static I1	anI1b	=	new C4();
	/*5. Since class C4 implements both Interfaces I1 and I2, the interface reference of I2 can be
	*	 typecasted to the object of class C4
	*/
	static I2	anI2a	=	new C4();

	// which methods will be called and why?
	public static void test1()	{
		/**
		 * anI1 is an object of Interface I1 which is implemented by Abstract class,
		 * which is in turn extended by class C1. Hence the method i1method that is implemented
		 * will be the one that is executed in class C1.
		 */
		anI1.i1method();
		/**
		 * anI1b is an object of Interface I1 which is implemented by class C4 along-with Interface I2.
		 * Hence, the method that is executed is the one present in class C4(since this is the object of class C4 type)
		 */
		anI1b.i1method();
		/**
		 * anI1b is an object of Interface I1 which is implemented by class C4 along-with Interface I2.
		 * Hence, the i1and2method method of class C4 is executed (since this is an object of class C4 type)
		 */
		anI1b.i1and2method();
		/**
		 * anI1b is an object of Interface I1 which is implemented by class C4 along-with Interface I2.
		 * Hence, the i1method of class C4 is executed
		 */
		anI1b.i1method();
		
		/**
		 * anI2 is an object of Interface I2 which the AbstractClass class implements which is extended by class C3
		 * Hence, the i2method that is implemented in class C3 is executed
		 */
		anI2.i2method();
		/**
		 * i1and2method method of class C3 would be called here.
		 * Since C3 class implements I2 interface, the method call is processed from C3 class
		 */
		anI2.i1and2method();
	}
	// which methods will be called and why?
	public static void test2()	{
		C3 aC3 = new C3();
		C5 aC5 = new C5();
		C3 aaC3 = (C3)aC5;
		/*
		 * c3andC5m method of class C3 is executed because
		 * it is called by aC3 which is C3 class object
		 */
		aC3.c3andC5m();
		/*
		 * c3andC5m method of class C5 is executed because
		 * it is called by aC5 object of class C5
		 */
		aC5.c3andC5m();
		/*
		 * c3andC5m method of class C5 is executed since aaC3 is 
		 * C5 object converted to class C3 object by up-casting
		 * And the method is over-ridded
		 */
		aaC3.c3andC5m();
		/*
		* Here the instance variable of class C3 is printed.
		* Since the variable is accessed from top-bottom format
		*/
		System.out.println("aaC3.c3andC5 = " + aaC3.c3andC5 );
		/*
		* Here the instance variable of class C3 is changed.
		* Since tha variable is accessed from top-bottom format
		*/

		aaC3.c3andC5 = 99999;
		/*
		 * Here, the instance variable of super class is changed.
		 * But the method of class C5 is called, so it prints the value of instance variable
		 * of that class
		 */
		aaC3.c3andC5m();
		/*
		* Again, the type casted object access the instance variable from
		* top to bottom format. Hence the variable of class C3 is printed
		*/
		System.out.println("aaC3.c3andC5 = " + aaC3.c3andC5 );
	}
	public static void main(String[] args)	{
		test1();
		test2();
	}
	// give an example when you would use an abstract class but not an interface
	/*
	 * An abstract class Shape contains an abstract method area() along-with various other methods 
	 * subclasses Rectangle, Circle would override the area method with different implementations.
	 * This is a usage of abstract Class
	 */
	// give an example when you would use an interface but not an abstract class
	/* Consider a interface Bank Account which contains methods like getAccountNumber,
	 * deposit,withdraw. Now 2 classes Savings and Current implement this interface and 
	 * overrides all the methods
	 * 
	 */
	// give an example when you have to use an interface
	/* Consider 2 interfaces Camera and Biometric having methods capture() and verify() respectively
	 * A class Phone implements these 2 interfaces and overrides the corresponding methods 
	 */
}