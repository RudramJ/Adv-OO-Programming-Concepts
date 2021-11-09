/**
 * Philosopher.java
 *
 * Version:
 * 	   $Id$
 *
 * Revisions:
 * 	   $Log$
 */
/**
 * The class Philosopher shows the classic Philosophers problem.
 * In this program, the problem that every philosopher will be able to eat
 * is solved(Avoiding deadlock) by breaking the circular wait.
 *
 * @author Prof. HP
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
import java.util.Random;
import java.util.Vector;

public class Philosopher extends Thread {

    protected static Random random = new Random();        // randomize
    protected int 	  me;          			// number for trace
    protected Integer       left, right; 			// my chopsticks

    public Philosopher (int me, Integer left, Integer right) {
        this.me = me;
        this.left = left;
        this.right = right;
    }
    // philosopher's behavior: thinks and eat 5 times
    public void run () {
        while ( true )	{
            System.out.println(me+" thinks");
            try { Thread.sleep((long)(random.nextFloat()*1000)); } catch(Exception e) { }

            if  ( me != 4 )	{
                synchronized ( right )  {
                    System.out.println("\t" + me + " takes right fork");
                    synchronized ( left )  {
                        System.out.println("\t\t" + me + " takes left fork");
                        System.out.println("\t\t" + me + " eats");
                        try {
                            Thread.sleep((long)(random.nextFloat()*1000)); } catch(Exception e) { }
                    }
                }
            } else {
                synchronized ( left )  {
                    System.out.println("\t" + me + " takes left fork");
                    synchronized ( right )  {
                        System.out.println("\t" + me + " takes right fork");
                        System.out.println("\t\t" + me + " eats");
                        try {
                            Thread.sleep((long)(random.nextFloat()*1000)); } catch(Exception e) { }
                    }
                }
            }
        }
    }
    public static void main (String args []) {
        Integer forks[] = new Integer[5];
        for (int n = 0; n <= 3; ++ n)
            forks[n] = new Integer(n);
        Philosopher thePhilosophers[] = new Philosopher[4];
        // Breaking the circular wait to avoid deadlock
        thePhilosophers[0] = new Philosopher(0, forks[0], forks[3]);      // backwards
        for (int n = 1; n <= 3; ++ n)
            thePhilosophers[n] = new Philosopher(n, forks[n-1], forks[n]);
        for (int n = 0; n < 4; ++ n) thePhilosophers[n].start();
    }
}