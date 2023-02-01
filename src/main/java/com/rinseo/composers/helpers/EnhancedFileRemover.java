package com.rinseo.composers.helpers;

import com.rinseo.composers.model.Composer;

import java.io.*;
import java.util.ArrayList;

public class EnhancedFileRemover {
    public static boolean remover(String path, ArrayList<Composer> composer, String input) {
        try {
            File inputFile = new File(path);
            File tempFile = new File(path.substring(path.length()-3).concat( "_temp.txt"));

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            // currentLine will be used to clean the line
            // While the line is not empty and contains the input string, we found the line to be removed
            // If the line is a separator, we skip that line
            // I
            String currentLine;
            boolean found = false;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(input)) {
                    found = true;
                } else if (found && currentLine.startsWith("---")) {
                    found = false;
                } else if (!found) {
                    writer.write(currentLine + System.lineSeparator());
                    writer.flush();
                }
            }
            reader.close();
            writer.close();
            // Delete the original file and rename the new file to the original file name
            inputFile.delete();
            tempFile.renameTo(inputFile);
            return true;
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return false;
        }
    }
}
