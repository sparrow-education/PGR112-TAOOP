package com.rinseo.composers.helpers;

import com.rinseo.composers.model.Composer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jack
 * @version 1.0
 * @since 2023-01-30
 */
public class EnhancedFileReader {
    /**
     * Static method to read a file and adding specific formatted lines to an Arraylist of composer objects.
     * @param path - the path to the file to be read
     * @param composers - the ArrayList to which the read lines are added
     */
    public static void enhancedReadFile(String path, ArrayList<Composer> composers) {
        try {
            Scanner scan = openFile(path);
            while (scan.hasNextLine()) {
                Composer composer = readComposer(scan);
                composers.add(composer);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }






    /**
     * Encapsulated and extracted class method
     * Open file and return a Scanner object
     *
     * @param path path to file
     * @return Scanner object
     * @throws FileNotFoundException
     */
    private static Scanner openFile(String path) throws FileNotFoundException {
        File file = new File(path);
        return new Scanner(file);
    }

    /**
     * Encapsulated and extracted class method
     * Read a composer from a Scanner object
     *
     * @param scan Scanner object
     * @return Composer object
     */
    private static Composer readComposer(Scanner scan) {
        String composerName = scan.nextLine();
        scan.useDelimiter("-");
        int birth = Integer.parseInt(scan.next());
        int death = Integer.parseInt(scan.next().strip());
        scan.nextLine();
        return new Composer(composerName, birth, death);
    }
}