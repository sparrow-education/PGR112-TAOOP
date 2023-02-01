package com.rinseo.composers.model;

import com.rinseo.composers.helpers.EnhancedFileReader;
import com.rinseo.composers.helpers.EnhancedFileRemover;
import com.rinseo.composers.helpers.EnhancedFileWriter;

import java.util.ArrayList;
import java.util.Set;

public class ComposerRepository {
    private static final String PATH = "src/main/java/com/rinseo/composers/composers.txt";
    private final ArrayList<Composer> COMPOSERS = new ArrayList<>();

    public ComposerRepository() {
        // FileReader.readFile("src/main/java/com/rinseo/composers/composers.txt", COMPOSERS);
        EnhancedFileReader.enhancedReadFile(PATH, COMPOSERS);
    }

    public ArrayList<Composer> getAllComposers() {
        if (COMPOSERS.size() == 0) {
            System.out.println("List is empty!");
        } else {
            for (Composer composer : COMPOSERS) {
                System.out.println(composer.toString());
            }
            return COMPOSERS;
        }
        return null;
    }

    public Composer getComposer(String name) {
        for (Composer composer : COMPOSERS) {
            if (composer.getName().equalsIgnoreCase(name.toLowerCase().trim())) {
                return composer;
            }
        }
        return null;
    }

    public boolean insertComposer(Composer composer) {
        if (getComposer(composer.getName()) != null) {
            System.out.println("Composer already exists");
            return false;
        } else {
            ArrayList<Composer> temp = new ArrayList<>(Set.of(composer));
            EnhancedFileWriter.enhancedWriteFile(PATH, temp);
            System.out.printf("%s added.\n", composer.getName());
            return true;
        }
    }

    public boolean removeComposer(Composer composer) {
        if (COMPOSERS.size() == 0 && getComposer(composer.getName()) == null) {
            return false;
        } else {
            return EnhancedFileRemover.remover(PATH, COMPOSERS, composer.getName());
        }
    }
}
