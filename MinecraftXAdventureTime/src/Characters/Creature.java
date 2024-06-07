/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Characters;

import Item.Items;
import Item.MultiTools;
import Pages.GamePage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author User
 */
public class Creature {
    public String name;
    public String habitat;
    public String weakness;
    public int xPosition;
    public int yPosition = 516;
    public int initialPosition;
    public int speed;
    public double hp;
    public double hpCapacity;
    GamePage gamePage;
    private String note;
    public int destination;
    public Image[] image;
    protected final int RIGHT = 0;
    protected final int LEFT = 1;
    protected int direction;
    Random rand = new Random();
    
    public Creature(String name, String habitat, String weakness, int initialPosition, int speed, double hp , Image[] image, GamePage gamePage) {
        this.name = name;
        this.habitat = habitat;
        this.weakness = weakness;
        this.xPosition = initialPosition;
        this.initialPosition = initialPosition;
        this.speed = speed;
        this.hp = hp;
        this.hpCapacity = hp;
        this.image = image;
        this.gamePage = gamePage;
        direction = RIGHT;
        destination = rand.nextInt(1200);
    }
    
    public void move(){
        if(xPosition > destination){
            xPosition-=speed;
            direction = LEFT;
        }
        else if(xPosition < destination){
            xPosition+=speed;
            direction = RIGHT;
        }
        else if(xPosition == destination)
            destination = rand.nextInt(1200);
    }
    
    public void drawCreature(Graphics g){
        g.drawImage(image[direction], xPosition, yPosition, null);
        g.setColor(Color.black);
        g.fillRect(xPosition - 5, yPosition - 20, 30, 12);    
        g.setColor(Color.red);
        g.fillRect(xPosition - 4, yPosition - 19, (int)((double)28*(hp/hpCapacity)), 10);    
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.PLAIN, 10));
        g.drawString((int)hp + "",xPosition,yPosition - 10);
        move();
    }
    
    public boolean inRange(){
        if(gamePage.aniRow%2==0 && xPosition - gamePage.playerXPosition <100 && xPosition - gamePage.playerXPosition > 20){
            return true;
        }
        else if(gamePage.aniRow%2==1 && gamePage.playerXPosition - xPosition <35 && gamePage.playerXPosition - xPosition > -20){
            return true;
        }
        return false;
    }
    
    public void setNotes(String note) {
        this.note = note;
    }
    
    public void damaged(Items multiTools){
        MultiTools temp = (MultiTools)multiTools;
        int damage = temp.getCurrentTool().getDamage();
        if(inRange() && weakness.equals(temp.getCurrentTool().getType())){
            hp-=damage;
        }
        if(hp<=0)
            gamePage.creatures.remove(this);
    }

    @Override
    public String toString(){
        return name;
    } 
}
