/**
 * StorageOfAstronomicalObject.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * This class is the storage class of all Astronomical Objects like
 * Planet, Asteroid and Binaries. And calculates the average density 
 * of the objects stored in it.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
import java.util.Vector;

public class StorageOfAstronomicalObject<T extends ParentOfAstronomicalObject> {
	// Instance variables
	Vector<T> astronomicalVector;
	String myGalaxy = "Galaxy";
	
	private double densityOnAverage = 0;
	/**
	 * Default constructor
	 */
	public StorageOfAstronomicalObject () {
		astronomicalVector = new Vector<T>();
	}
	/**
	 * Parameterized constructor
	 * @param galaxy 	Name of the galaxy
	 */
	public StorageOfAstronomicalObject (String galaxy) {
		astronomicalVector = new Vector<T>();
		this.myGalaxy = galaxy;
	}
	/**
	 * This method stores the object of class which extends 
	 * ParentOfAstronomicalObject
	 * 
	 * @param anyAstronomicalObject  Object of Planet/Asteroid/Binaries
	 */
	public void add(T anyAstronomicalObject) {
		astronomicalVector.addElement( anyAstronomicalObject);
		
		calculateDensityOnAverage();
	}
	/**
	 * This method returns all the name of the astronomical objects stored
	 * @return	Names of astronomical objects
	 */
	public String getAllNames() {
		String returnAllNames = "";
		for(int i = 0; i < astronomicalVector.size(); i++) {
			returnAllNames += astronomicalVector.get(i).getName() + ", ";
		}
		return returnAllNames;
	}
	/**
	 * This method calculates the average density of the astronomical objects stored
	 */
	private void calculateDensityOnAverage() {
		double allDensity = 0;
		for ( int index = 0; index < astronomicalVector.size(); index ++ )	{
			if ( astronomicalVector.get(index) != null )	{
				allDensity += astronomicalVector.get(index).getDensity();
			}
		}
		densityOnAverage = allDensity / astronomicalVector.size();
	}	
	/**
	 * returns string on printing the object of class
	 */
	public String toString() {
		String printString = "" ;
		for(int i = 0; i < astronomicalVector.size(); i++) {
			printString += i + ": " + astronomicalVector.get(i) + "\n";
		}
		return printString + "\n" + "average density: " + densityOnAverage + "\n";
	}
	/**
	 * The main method
	 * @param args
	 */
	public static void main(String[] args) {
		StorageOfAstronomicalObject<Planet> a1 = 
				new StorageOfAstronomicalObject<Planet>("Milky Way");
		
		a1.add(new Planet("Mercury", 5.427, 87.97, 0));
		
		Planet aPlanet = new Planet("Saturn", 0.687, 10792, 82);
        a1.add(aPlanet);

        aPlanet.setName("Earth");
        aPlanet.setDensity(5.514);
        aPlanet.setOrbitalPeriod(365.256363004);
        aPlanet.setNumberOfMoons(1);
        a1.add(aPlanet);
        
		
		System.out.println(a1.getAllNames());
		System.out.println(a1);		
	}	
}
