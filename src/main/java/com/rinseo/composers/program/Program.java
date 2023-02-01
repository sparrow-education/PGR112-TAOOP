package com.rinseo.composers.program;


import com.rinseo.composers.model.Composer;
import com.rinseo.composers.model.ComposerRepository;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ComposerRepository composerRepository = new ComposerRepository();

    public Program() {
        runProgram();
    }

    public void runProgram() {
        String logo = """
               +--------------------------------------------------------------------+
               |           .-.                                                      |
               |     .--.-'                                                         |
               |    (  (_)       .-.    .-.      ).--.    ).--.   .-._.  `)    (    |
               |     `-.         /  )  (  |     /        /       (   )   /  .   )   |
               |   _    )       /`-'    `-'-'  /        /         `-'   (_.' `-'    |
               |  (_.--'       /                                                    |
               |                                                                    |
               +--------------------------------------------------------------------+
               """;
        System.out.println(logo);

        while (true) {
            choiceMenu();
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("6")) {
                System.out.println("Exit");
                System.exit(0);
            }
            choiceHandler(choice);
        }
    }

    private static void choiceMenu() {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("+--------------------------------+");
        menu.add("| 1. Show composers              |");
        menu.add("| 2. Search composer             |");
        menu.add("| 3. Search by composer style:   |");
        menu.add("| 4. Add composer                |");
        menu.add("| 5. Remove composer             |");
        menu.add("| 6. Exit                        |");
        menu.add("+--------------------------------+");
        for (String s : menu) {
            System.out.println(s);
        }
    }

    private static void choiceHandler(String choice) {
        String formattedChoice = choice.stripIndent().toLowerCase().trim();
        switch (formattedChoice) {
            case "1" -> {
                getAllComposers();
            }
            case "2" -> {
                getComposer();
            }
            case "3" -> {
                System.out.println("Search by composer style");
            }
            case "4" -> {
                insertComposer();
            }
            case "5" -> {
                removeComposer();
            }
            default -> {
                System.out.println("Not an option");
            }
        }
    }



    private static ArrayList<Composer> getAllComposers() {
        ComposerRepository composerRepository = new ComposerRepository();
        return composerRepository.getAllComposers();
    }



    private static Composer getComposer() {
        System.out.print("Search composer: ");
        String userInput = scanner.nextLine();
        String toASCII = Normalizer
                .normalize(userInput, Normalizer.Form.NFD)
                .replaceAll("[^\\x00-\\x7F]", "");
        Composer result = composerRepository.getComposer(toASCII);

        if (result == null) {
            System.out.println("No composer found");
            return null;

        } else {
            System.out.println(result);

            System.out.print("\nDo you want to search again? (y/n) ");
            String choice = scanner.nextLine();
            while (true) {
                if (choice.equalsIgnoreCase("y")) {
                    getComposer();
                } else {
                    System.out.println("Returning to main menu..");
                    break;
                }
            }
            return result;
        }
    }

    private static boolean insertComposer() {
        System.out.print("Add composer name: ");
        String name = scanner.nextLine();
        try {
            System.out.println("Composer birth and death year, format must be `yyyy-yyyy` (e.g. 1990-1999)");
            System.out.print("Add year: ");
            String bio = scanner.nextLine();
            int birth = Integer.parseInt(bio.substring(0,4));
            int death = Integer.parseInt(bio.substring(5,9));

            return composerRepository.insertComposer(new Composer(name, birth, death));

        } catch (NumberFormatException e) {
            System.out.println("Year format must be `yyyy-yyyy` (e.g. 1990-1999)");
            return false;
        }
    }

    private static boolean removeComposer() {
        Composer remove = getComposer();
        assert remove != null;
        return composerRepository.removeComposer(remove);
    }


}
