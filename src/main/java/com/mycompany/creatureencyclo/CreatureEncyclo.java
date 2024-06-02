/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.creatureencyclo;

/**
 *
 * @author aliyaishak
 */
import java.util.*;

class Creature {
    private String name;
    private String type;
    private String habitat;
    private List<String> drops;
    private List<String> strengths;
    private List<String> weaknesses;
    private int healthPoints;
    private List<String> notes;

    public Creature(String name, String type, String habitat, List<String> drops, List<String> strengths, List<String> weaknesses, int healthPoints) {
        this.name = name;
        this.type = type;
        this.habitat = habitat;
        this.drops = drops;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.healthPoints = healthPoints;
        this.notes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addNotes(String note) {
        notes.add(note);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Habitat: ").append(habitat).append("\n");
        sb.append("Health Points: ").append(healthPoints).append("\n");
        sb.append("Drops: ").append(String.join(", ", drops)).append("\n");
        sb.append("Strengths: ").append(String.join(", ", strengths)).append("\n");
        sb.append("Weaknesses: ").append(String.join(", ", weaknesses)).append("\n");
        sb.append("Notes: ");
        if (notes.isEmpty()) {
            sb.append("No notes for ").append(name).append("\n");
        } else {
            sb.append("\n");
            for (String note : notes) {
                sb.append("- ").append(note).append("\n");
            }
        }
        return sb.toString();
    }
}