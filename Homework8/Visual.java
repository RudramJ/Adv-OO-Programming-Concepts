/**
 * Visual.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * original from: http://rosettacode.org/wiki/Pi_set#Java
 *
 * @Main-author Prof HP
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
// modified for color

import java.awt.Color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.zip.GZIPInputStream;

public class Visual extends JFrame {

    private final int LENGTH_OF_SQUARE = 3;
    private final int LENGTH 	       = 330;
    private final int LENGTH_OF_WINDOW = LENGTH * LENGTH_OF_SQUARE;
    private BufferedImage theImage;
    private String fileName = null;
    Reader input;

    public Visual() {
        super("Visual");

        setBounds(100, 100, LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public Visual(String fileName) {
        this();
        this.fileName = fileName;
    }

    /**
     * This method returns the next digit from the input file
     * It ignores the non-digit characters
     * @param input BufferedReader Object input
     * @return  Digit character of file
     */
    private char nextDigit(BufferedReader input)	{
        char buf[] = new char[1];
        try {
            char returnValue = (char)input.read();
            if(returnValue == '.') {
                returnValue = (char)input.read();
            }
            buf[0] = returnValue;
        }
        catch (Exception e){
            System.err.println("Error");
        }
        return buf[0];
    }

    private void saveImage(BufferedImage theImage)	{
        String suffix = "png";
        String outputFileName = fileName == null ? "output" : fileName + "_output" + "." + suffix ;
        try {
            File outputfile = new File(outputFileName);
            ImageIO.write(theImage, suffix, outputfile);
        } catch (Exception e )	{
            e.printStackTrace();
        }

    }
    public void fillSquare(int xOrig, int yOrig, int color)	{
        for (int x = 0; x < LENGTH_OF_SQUARE; x ++ )
            for (int y = 0; y < LENGTH_OF_SQUARE; y ++ )
                theImage.setRGB(xOrig + x, yOrig + y , color);
    }
    public void createImage()	{
        theImage = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
        int red = Color.RED.getRGB();		// you might like to change the colors if you like
        int blue = Color.BLUE.getRGB();		// you might like to change the colors if you like
        int colorUsed;
        /*
         * This try resources statement is used to read input from standard input as well as command line
         * This could also read text as well as the gzipped file
         */
        try (
                BufferedReader input = new BufferedReader(this.fileName == null ? new InputStreamReader(System.in):
                ((this.fileName.endsWith("txt") ? new InputStreamReader( new FileInputStream(this.fileName)) :
                        new InputStreamReader(new GZIPInputStream( new FileInputStream( this.fileName))))))
        ) {
            for (int y = 0; y < getHeight(); y += LENGTH_OF_SQUARE) {
                for (int x = 0; x < getWidth(); x += LENGTH_OF_SQUARE) {
                    char digit = nextDigit(input);
                    fillSquare(x, y,  digit % 2 == 0 ? red : blue );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        repaint();
        saveImage(theImage);
        System.exit(0);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(theImage, 0, 0, this);
    }

    public static void main(String[] args) {
        Visual aVisual = new Visual(args.length == 1 ? args[0] : null );
        aVisual.setVisible(true);
        aVisual.createImage();
    }
}