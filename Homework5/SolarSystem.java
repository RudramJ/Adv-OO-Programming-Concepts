
/**
 * SolarSystem class contains the planets array and the counter soManyPlanets
 * @author Moinuddin Memon,Rudram Joshi
 *
 */
public class SolarSystem {
	int soManyPlanets;
	Planet[] planets;
	SolarSystem(int soManyPlanets) {
		this.soManyPlanets = soManyPlanets;
		this.planets=new Planet[soManyPlanets];
	}
	
	/**
	 * Protected method to set the Planet at a particular index
	 * @param index
	 * @param planet
	 */
	protected void setPlanet(int index,Planet planet) {
		try {
			this.planets[index]=planet;
		}
		catch(Exception e) {
			System.err.println("Error in arguments");
			System.exit(1);
		}
	}
	@Override
	public String toString() {

		for( int index=0 ; index<this.planets.length ; index++ ) {
			
			if(planets[index]==null) {
				continue;
			}
			System.out.print( (index+1)+": " );
			System.out.print( "name=" + planets[ index ].getName() + ", " );
			System.out.print( "density=" + planets[index ].getDensity()+ ", " );
			System.out.print( "orbitalPeriod="+planets[index].getOrbitalPeriod()+", ");
			System.out.println( "numberOfMoons=" + planets[index].getNumberOfMoons() );
		}
		return "average density="+getAverageDensity( planets );
	}
	
	/**
	 * Private method returns the average density of all the Planets
	 * @param planets
	 * @return
	 */
	private float getAverageDensity( Planet[] planets ) {
		float avgDensity=0;
		int count=0;
		for( int index=0; index<planets.length ; index++ ) {
			
			if(planets[index]==null) {
				continue;
			}
			avgDensity += planets[index].getDensity();
			count++;
		}
		return avgDensity/count;
	}
	
	/**
	 * Main method to create SolarSytem object which includes a particular planet
	 * at an index
	 * @param args
	 */
	public static void main( String[] args ) {
		SolarSystem aSolarSystem = new SolarSystem(8);  // sadly Pluto was demoted

        Planet aPlanet = new Planet("Mercury", 5.427, 87.97, 0);
        aSolarSystem.setPlanet(0, new Planet("Mercury", 5.427, 87.97, 0));

        aPlanet.setName( "Saturn" );
        aPlanet.setDensity( 0.687 );
        aPlanet.setOrbitalPeriod( 10759.22 );
        aPlanet.setNumberOfMoons( 82 );
        aSolarSystem.setPlanet( 7, aPlanet );
        
        System.out.println( aSolarSystem );
        aSolarSystem.setPlanet( 1, new Planet("Venus" , 1.234 , 78.4 , 9 ) );
        
        /**
         * The following piece of code will change the values of the attribute
         * for the object aPlanet.Since we already have an object in the Planets array
         * that points to the values pointed by aPlanet object, if we run these lines
         * the existing aPlanet object will change and point to the new value.
         * So we would have duplicate object at index 3 and 7.
         */
        
        aPlanet.setName( "Earth" );
        aPlanet.setDensity( 5.514 );
        aPlanet.setOrbitalPeriod( 365.256363004 );
        aPlanet.setNumberOfMoons( 1 );
        aSolarSystem.setPlanet( 3, aPlanet );

        System.out.println( aSolarSystem );
	}
}
