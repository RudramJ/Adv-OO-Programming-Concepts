/**
 * NumberCounter.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class reads and processes the file containing data
 * of the NY-10 lottery winning numbers.
 * The number of threads are provided to calculate the
 * number count by dividing it by lines of input file.
 * It is done to assign particular line range for thread to compute
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
import java.io.*;

public class NumberCounter extends Thread{
    // Global Variables
    // Static variables
    private static final int FILE_LINES = 12278;
    private static int [] numberCount = new int[81];
    private static int numberOfThreads = 0;
    // Instance variables
    private int threadID;
    private int linesToSkip = 0;
    private String fileName;

    /**
     * The parameterized constructor
     * This will store the thread ID, file name and lines to skip
     * and process
     * @param id        Thread ID
     * @param file      File name
     * @param skipLines Lines for a thread
     */
    public NumberCounter(int id, String file, int skipLines) {
        this.threadID = id;
        this.fileName = file;
        this.linesToSkip = skipLines;
    }

    /**
     * This static method is called for printing the output
     */
    private static void printOutput() {
        for(int index = 1; index < numberCount.length; index += 4) {
            System.out.print(index+":"+numberCount[index]+"\t\t");
            System.out.print((index+1)+":"+numberCount[index+1]+"\t\t");
            System.out.print((index+2)+":"+numberCount[index+2]+"\t\t");
            System.out.print((index+3)+":"+numberCount[index+3]);
            System.out.println();
        }
    }

    /**
     * Function to get the stream of numbers per line
     * and increase the frequency of the indexes in the array
     * @param lineOfFile  Line of file to store information
     */
    private static void getLines(String lineOfFile) {
        lineOfFile = lineOfFile.substring(lineOfFile.indexOf(",") + 1);
        String[] lineCharacter = lineOfFile.split("\\s");

        for(int index = 0 ; index < lineCharacter.length; index++) {
            numberCount[Integer.parseInt(lineCharacter[index])]++;
        }
    }

    /**
     * Reads the line of file based on number of threads
     * provided
     */
    private void readFile() {
        try (
                BufferedReader input = new BufferedReader(new FileReader(this.fileName) )
            )
        {
            int startLine = (this.threadID * this.linesToSkip) ;
            int endLine = ((this.threadID+1) * this.linesToSkip) ;

            if(numberOfThreads == this.threadID+1) {
                endLine = FILE_LINES + 1;
            }
            if(numberOfThreads == 0) {
                startLine = 0;
                endLine = FILE_LINES + 1;
            }
            int counter = 0;

            String line = input.readLine();
            while((line = input.readLine()) != null) {
                if(counter > endLine) {
                    break;
                }
                else if(counter >= startLine && counter < endLine) {
                    getLines(line);
                }
                counter++;
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            System.out.println("No argument provided");
            System.exit(0);
        }
    }

    /**
     * This method is called when thread starts
     * Calls method read file to read and process output
     * based on thread count
     */
    public void run() {
        readFile();
    }

    /**
     * Main method
     * Checks for the input and creates the thread accordingly
     * to get the count of numbers in NY-10 lottery
     * @param args Thread number and FileName
     */
    public static void main(String[] args) throws InterruptedException {
        // To get number of line for each thread
        int lineForAThread = 0;

        if(args.length == 0 | args.length == 1) {
            System.err.println("Please provide the correct arguments");
            System.exit(0);
        }
        else if(args.length == 2) {
            try {
                numberOfThreads = Integer.parseInt(args[0]);
                if(numberOfThreads > FILE_LINES) {
                    System.err.println("Enter valid number of threads!");
                    System.exit(0);
                }
            }
            catch (Exception e) {
                System.err.println("Check the number of threads provided");
            }
        }
        if(numberOfThreads != 0) {
            lineForAThread = (FILE_LINES/numberOfThreads);
            NumberCounter[] threadsForCalculation = new NumberCounter[numberOfThreads+1];

            for(int i = 0; i < numberOfThreads; i++) {
                threadsForCalculation[i] = new NumberCounter(i, args[1], lineForAThread);
                threadsForCalculation[i].start();
                threadsForCalculation[i].join();
            }
        }
        else {
            NumberCounter mainThreadForCalculation = new NumberCounter(0, args[1], lineForAThread);
            mainThreadForCalculation.readFile();
        }
        printOutput();
    }
}
