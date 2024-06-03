/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.creatureencyclo;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aliyaishak
 */
public class TestCreature {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Encyclopedia encyclopedia = new Encyclopedia();
        CreatureInitializer.addPreExistingCreatures(encyclopedia);

        while (true) {
            System.out.println("1. Add Creature");
            System.out.println("2. Get Creature Information");
            System.out.println("3. Add Note to Creature");
            System.out.println("4. List All Creatures");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter creature name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter creature type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter creature habitat: ");
                    String habitat = scanner.nextLine();
                    System.out.print("Enter creature health points: ");
                    int healthPoints = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter drops (comma separated): ");
                    List<String> drops = Arrays.asList(scanner.nextLine().split(","));
                    System.out.print("Enter strengths (comma separated): ");
                    List<String> strengths = Arrays.asList(scanner.nextLine().split(","));
                    System.out.print("Enter weaknesses (comma separated): ");
                    List<String> weaknesses = Arrays.asList(scanner.nextLine().split(","));

                    Creature newCreature = new Creature(name, type, habitat, drops, strengths, weaknesses, healthPoints);
                    encyclopedia.addCreature(newCreature);
                    break;
                case 2:
                    System.out.print("Enter creature name: ");
                    String creatureName = scanner.nextLine();
                    Creature creature = encyclopedia.getCreature(creatureName);
                    if (creature != null) {
                        System.out.println(creature);
                    } else {
                        System.out.println("Creature not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter creature name: ");
                    String noteCreatureName = scanner.nextLine();
                    System.out.print("Enter note: ");
                    String note = scanner.nextLine();
                    encyclopedia.addNoteToCreature(noteCreatureName, note);
                    System.out.println("Note added.");
                    break;
                case 4:
                    encyclopedia.listCreatures();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
