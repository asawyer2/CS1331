
// Created for CS1331 at Georgia Tech
// By Allison Sawyer
// Spring 2019




import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * This class represents a GREP object
 * @author asawyer8
 * @version 1.0
 */

public class Grep {
    /**
    * This class represents a Grep method
    * @param args (string array for parameters)
    */
    public static void main(String[] args) {
        try {
            if (args.length != 3 && args.length != 2) {
                throw new IllegalArgumentException();
            }
            if (args.length == 3) {
                System.out.print(grep(new File(args[2]), args[1], false));
            } else if (args.length == 2) {
                // if two augments, isSensitive will defalt to true
                System.out.print(grep(new File(args[1]), args[0], true));
            }
        } catch (IOException e) {
            //catches files that dont exist
            System.out.println("File does not exist.");
        } catch (InvalidSearchStringException e) {
            //catches phrases that dont exist
            System.out.println(e.getMessage() + "Phrase does not exist.");
        }
    }

    /**
    * This class represents a GREP method
    * @param f  file
    * @param s  string
    * @param isSensitive case sensitivity
    * @return grep as sting
    */
    public static String grep(File f, String s, boolean isSensitive)
        throws IOException, InvalidSearchStringException {
        //initialize grep string to be returned
        String grep = "";
        if (s.contains("\n")) {
            //catches instances of newline characters inside the lines
            throw new InvalidSearchStringException();
        }

        if (f.isDirectory()) {
            //recursion through the directory of all files
            File[] allFiles = f.listFiles();
            for (int i = 0; i < allFiles.length; i++) {
                grep += grep(allFiles[i], s, isSensitive);
            }
        } else {
            Scanner input = new Scanner(f);
            //start searching a new file on line one
            int lineNumber = 1;
            String pathName = f.getPath();
            String thisLine = input.nextLine();
            while (input.hasNextLine()) {
                if (isSensitive) {
                    //check case sensitivity
                    if (thisLine.contains(s)) {
                        //adds found line to grep string
                        grep += pathName + String.format(":%d:", lineNumber);
                        grep += thisLine + ("\n");
                    }
                } else {
                    String upperCaseLine = thisLine.toUpperCase();
                    String upperS = s.toUpperCase();
                    if (upperCaseLine.contains(upperS)) {
                       //adds found line to grep string
                        grep += pathName + String.format(":%d:", lineNumber);
                        grep += thisLine + ("\n");
                    }

                }
                //move to check next line in the current file
                lineNumber++;
                thisLine = input.nextLine();
            }

        }
        return grep;
    }

}

