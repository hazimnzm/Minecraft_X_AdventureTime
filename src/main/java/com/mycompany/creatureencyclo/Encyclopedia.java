/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.creatureencyclo;

/**
 *
 * @author aliyaishak
 */
import java.util.*;

public class Encyclopedia {
    private Map<String, Creature> creatures;

    public Encyclopedia() {
        creatures = new HashMap<>();
    }

    public void addCreature(Creature creature) {
        creatures.put(creature.getName().toLowerCase(), creature);
    }

    public Creature getCreature(String name) {
        return creatures.get(name.toLowerCase());
    }

    public void addNoteToCreature(String name, String note) {
        Creature creature = getCreature(name);
        if (creature != null) {
            creature.addNotes(note);
        } else {
            System.out.println("Creature not found.");
        }
    }

    public void listCreatures() {
        for (Creature creature : creatures.values()) {
            System.out.println(creature);
            System.out.println();  // Add a blank line between creatures
        }
    }

    void displayCreatureNotes(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
