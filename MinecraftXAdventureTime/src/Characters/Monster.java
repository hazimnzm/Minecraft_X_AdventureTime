package Characters;

import Pages.GamePage;
import java.awt.Graphics;
import java.awt.Image;

public class Monster extends Creature{
   
    public int damage;

    public Monster(String name, String habitat, String weakness, int initialPosition, int speed, double hp, Image[] image,GamePage gamePage, int damage) {
        super(name, habitat, weakness, initialPosition, speed, hp, image, gamePage);
        this.damage = damage;
    }

    @Override
    public void drawCreature(Graphics g) {
        super.drawCreature(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        giveDamage();
    }
    
    
    @Override
    public void move(){
        if(xPosition - 50 > gamePage.playerXPosition){
            xPosition-=speed;
            direction = LEFT;
        }
        else if(xPosition < gamePage.playerXPosition){
            xPosition+=speed;
            direction = RIGHT;
        }
    }
    
    public void giveDamage(){
        if(isAttacking()){
            gamePage.currentHero.damaged(damage);
        }
    }
    
    public boolean isAttacking(){
        if(xPosition - 75 < gamePage.playerXPosition && xPosition + 2 > gamePage.playerXPosition){
            return true;
        }
        return false;
    }
}
