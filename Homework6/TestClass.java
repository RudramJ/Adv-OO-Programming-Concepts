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
 * This class is the test class for StorageOfAstronomicalObject
 * and StorageOfAstronomicalObjectUpdated.
 * This class tests two example for storage of object one by reference and 
 * another by creating a new object of same type.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
public class TestClass {
	/**
	 * This method is called to test the parameters of Binaries stored
	 */
	public static void testBinaries() {
		StorageOfAstronomicalObjectUpdated<Binaries> a3 = 
				new StorageOfAstronomicalObjectUpdated<Binaries>("Milky Way");
		a3.add(new Binaries("RT77", 25, "David", 6));

		Binaries aBinaries = new Binaries("QT3", 447.9, "Shukla", 17);
        a3.add(aBinaries);;

        aBinaries.setName("OT9");
        aBinaries.setDensity(94.8);
        aBinaries.setDiscoverer("Amy");
        aBinaries.setSatelites(27);
        a3.add(aBinaries);
		
		System.out.println(a3.getAllNames());
		System.out.println(a3);		
	}
	/**
	 * This method is called to test the parameters of Asteroid stored
	 */
	public static void testAsteroids() {
		StorageOfAstronomicalObjectUpdated<Asteroid> a2 = 
				new StorageOfAstronomicalObjectUpdated<Asteroid>("Milky Way");
		
		
		a2.add(new Asteroid("L90", 5.427, "John"));

		Asteroid aAsteroid = new Asteroid("W55", 133.4, "Stella");
        a2.add(aAsteroid);;

        aAsteroid.setName("TL1");
        aAsteroid.setDensity(22.1);
        aAsteroid.setDiscoverer("Mohan");
        a2.add(aAsteroid);
		
		System.out.println(a2.getAllNames());
		System.out.println(a2);
	}
	/**
	 * This method is called to test the parameters of planet stored
	 */
	public static void testPlanets() {
		StorageOfAstronomicalObject<Planet> aStorageOfAstronomicalObject = 
				new StorageOfAstronomicalObject<Planet>("Milky Way");

        aStorageOfAstronomicalObject.add(new Planet("Mercury", 5.427, 87.97, 0));

        Planet aPlanet = new Planet("Saturn", 0.687, 10792, 82);
        aStorageOfAstronomicalObject.add(aPlanet);;

        aPlanet.setName("Earth");
        aPlanet.setDensity(5.514);
        aPlanet.setOrbitalPeriod(365.256363004);
        aPlanet.setNumberOfMoons(1);
        aStorageOfAstronomicalObject.add(aPlanet);


        System.out.println(aStorageOfAstronomicalObject.getAllNames());
        System.out.println(aStorageOfAstronomicalObject);
	}
	/**
	 * The main method
	 * @param args
	 */
	public static void main(String[] args) {
		testPlanets();
		testAsteroids();
	}
}
