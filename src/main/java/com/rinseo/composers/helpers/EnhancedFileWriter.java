package com.rinseo.composers.helpers;

import com.rinseo.composers.model.Composer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jack
 * @version 1.0
 * @since 2023-01-30
 */
public class EnhancedFileWriter {
    /**
     * Static method to write to a file and adding specific formatted lines to an Arraylist of composer objects.
     * @param path - the path to the file to be read
     * @param composers - the ArrayList to which the read lines are added
     */
    public static void enhancedWriteFile(String path, ArrayList<Composer> composers) {

        try (FileWriter writer = new FileWriter(path,true)
        ) {
            for (Composer composer : composers) {
                writer.write(composer.getName() + System.lineSeparator());
                writer.write(composer.getBirth() + "-" + composer.getDeath() + System.lineSeparator());
                writer.write("---" + System.lineSeparator());
                writer.flush();
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
