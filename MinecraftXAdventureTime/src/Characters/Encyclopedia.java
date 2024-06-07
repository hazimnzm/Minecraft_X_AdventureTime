/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Characters;

import Constants.Constants;
import Pages.GamePage;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class Encyclopedia extends JButton{
    private Map<String, Creature> creatures;
    public GamePage gamePage;
    private Random rand = new Random();
    public boolean isExpanded = false;
    
    public Encyclopedia(GamePage gamePage) {
        creatures = new HashMap<>();
        this.gamePage = gamePage;
        initializeCreature();
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setIcon(Constants.encyclopediaButton[0]);
        this.setBounds(0,0,232,64);
    
    }
    public void addCreature(Creature creature) {
        creatures.put(creature.name, creature);
    }

    public Creature getCreature(String name) {
        return creatures.get(name);
    }
    
    public Monster getMonster(String name) {
        Creature creature = creatures.get(name);
        Monster monster = (Monster)creature;
        return monster;
    }
    
    public void addNoteToCreature(String name, String note) {
        Creature creature = getCreature(name);
        if (creature != null) {
            creature.setNotes(note);
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
    public Creature createCreature(String name){
        Creature creature = getCreature(name);
        return new Creature(creature.name, creature.habitat, creature.weakness, rand.nextInt(1280), creature.speed, creature.hp, creature.image, gamePage);
    }
    public Monster createMonster(String name){
        Monster creature = getMonster(name);
        return new Monster(creature.name, creature.habitat, creature.weakness, rand.nextInt(1280), creature.speed, creature.hp, creature.image, gamePage, creature.damage);
    }
    
    public void initializeCreature(){
        addCreature(new Creature("Sheep", Constants.treeHouse, "Normal Sword", 0, 1, 300, Constants.SheepImage, gamePage));
        addCreature(new Monster("Ice Golem", Constants.iceKingdom, "Golden Ice Sword", 0, 2, 1000, Constants.IceGolemImage, gamePage,1));
        addCreature(new Monster("Lava Golem", Constants.fireKingdom, "Lava Sword", 0, 2, 1000, Constants.LavaGolemImage, gamePage,1));
        addCreature(new Monster("Bubble Gum Golem", Constants.candyKingdom, "Gummy Bear Sword", 0, 2, 1000, Constants.BubbleGumGolemImage, gamePage,1));
    }
    public void expand(Graphics g){
        g.drawImage(new ImageIcon("Item Image/Encyclopedia.png").getImage(),1280/2 - 732/2,720/2 - 552/2,null);
    }
}
