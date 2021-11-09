/**
 * AssemblyLine.java
 *
 * Version:
 * 	   $Id$
 *
 * Revisions:
 * 	   $Log$
 */
/**
 * The class AssemblyLine shows the operation of assembly line
 * for car production. Except of station 1, all station has 1 worker short.
 * This worker sequentially completes the tasks of every station based
 * on number of cars to produce.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
public class AssemblyLine extends Thread {
    // Instance Variables
    private int 		stationId;
    private Object 		first;
    private Object 		second;
    // Static Variables
    private static final int  	ASSEMBLIES = 4;
    private static final int  	CAR_TO_PRODUCE = 2;

    private static Object 		objectLocks[] = new Object[ASSEMBLIES];
    private static int 		stationNumber = 1;
    private static int 		lastSeen = 0;
    private static int 		numberOfTiers = 0;

    /*
     * Static initializer block. It is used to initialize the static object array
     * declared in the class.
     */
    static {
        for ( int index = 0; index < ASSEMBLIES; index ++ )
            objectLocks[index] = new Object();
    }

    /**
     * The parameterized constructor for the Assembly line. This initializes the Station number and the objects
     * for the corresponding station number thread.
     * @param id        Station number
     * @param first     First object
     * @param second    Second object
     */
    public AssemblyLine (int id, Object first, Object second) {
        this.stationId     	= id;
        this.first  	= first;
        this.second 	= second;
    }

    /**
     * This method is used for testing the entire operation of the car production.
     * If worker does not come to station i from station i-1, the error message
     * is printed and the JVM is terminated.
     */
    public void test () {
        if ( lastSeen + 1 != stationId )	{
            System.out.println("Something went wrong. Last Station/worker = " + lastSeen + "/" + stationId );
            System.exit(1);
        }
        lastSeen = ( lastSeen + 1 ) % ASSEMBLIES;
    }

    /**
     * This method prints the output based on the current thread implementing
     * the synchronized block. (Worker in nth station)
     * @param id Station number
     */
    public void print_output(int id) {
        if(id == 1) {
            System.out.println("Station 1: Mounts tires on rims and send 4 tires to station 2");
        }
        else if(id == 2) {
            System.out.println("\tStation 2: Mounts tires on car and sends the car to station 3");
        }
        else if(id == 3) {
            System.out.println("\t\tStation 3: Put engine in car and sends the car to station 4");
        }
        else if(id == 4) {
            System.out.println("\t\t\tStation 4: Add doors to car and sends the car out the factory");
        }
    }

    /**
     * The run method. This method is invoked everytime the start method
     * of a thread is called.
     * In this method, sequential calling of threads is achieved by two synchronized
     * block with first and second object.
     * Threads notify order(1->2->3->4->1)
     */
    public void run () {
        for(int i = 0; i < CAR_TO_PRODUCE; i++)	{
            try { sleep(300); } catch (  InterruptedException e ) { }
            synchronized ( first ) {
                synchronized ( second ) {
                    second.notify();
                    test();
                    print_output(stationId);
                    try {
                        if ( stationNumber <= ASSEMBLIES )	{
                            stationNumber++;
                            ( new AssemblyLine(stationNumber, objectLocks[stationNumber-1],
                                    objectLocks[stationNumber%ASSEMBLIES]) ).start();
                        }
                    } catch ( Exception e ) { }
                }
                try {
                    if(stationId == 1) {
                        while (numberOfTiers < 4) {
                            numberOfTiers ++;
                        }
                    }
                    else if(stationId == 2) {
                        if(numberOfTiers != 4) {
                            System.out.println("Number of tiers are not 4");
                            System.exit(0);
                        }
                        else {
                            numberOfTiers = 0;
                        }
                    }
                    if(i != CAR_TO_PRODUCE-1) {
                        first.wait();
                    }
                } catch ( Exception e ) { }
            }
        }
    }

    /**
     * The main method. This method creates and starts the thread for station 1 by specifying
     * the station ID and corresponding objects.
     * @param args None
     */
    public static void main (String[] args) {
        // Station 1 has all the worker
        new AssemblyLine(1, objectLocks[0], objectLocks[1]).start();
    }
}