package Characters;

import GUI.Game;
import Item.Items;
import Item.MultiTools;
import Pages.GamePage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Monster {
    public boolean isAlive = true;
    private String weakness;
    public int xPosition;
    public int yPosition = 516;
    GamePage gamePage;
    public int speed = 2;
    public double hp = 1000;
    public double hpCapacity = 1000;
    public int damage = 2;
    public Monster(GamePage gamePage, int xPosition, String weakness){
        this.gamePage = gamePage;
        this.xPosition = xPosition;
        this.weakness = weakness;
    }
    
    public void drawMonster(Graphics g){
        g.setColor(Color.red);
        g.fillRect(xPosition,yPosition,20,35);
        g.setColor(Color.black);
        g.fillRect(xPosition - 5, yPosition - 20, 30, 12);    
        g.setColor(Color.red);
        g.fillRect(xPosition - 4, yPosition - 19, (int)((double)28*(hp/hpCapacity)), 10);    
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.PLAIN, 10));
        g.drawString((int)hp + "",xPosition,yPosition - 10);
    }
    public boolean inRange(){
        if(gamePage.aniRow%2==0 && xPosition - gamePage.playerXPosition <100 && xPosition - gamePage.playerXPosition > 30){
            return true;
        }
        else if(gamePage.aniRow%2==1 && gamePage.playerXPosition - xPosition <20 && gamePage.playerXPosition - xPosition > -20){
            return true;
        }
        return false;
    }
    public void move(){
        if(xPosition - 50 > gamePage.playerXPosition){
            xPosition-=speed;
        }
        else if(xPosition < gamePage.playerXPosition){
            xPosition+=speed;
        }
    }
    public void damaged(Items multiTools){
        MultiTools temp = (MultiTools)multiTools;
        int damage = temp.getCurrentTool().getDamage();
        if(inRange() && weakness.equals(temp.getCurrentTool().getType())){
            hp-=damage;
        }
        if(hp<=0)
            isAlive = false;
    }
    
    public void giveDamage(){
        if(isAttacking()){
            gamePage.currentHero.damaged(damage);
        }
    }
    
    public boolean isAttacking(){
        if(xPosition - 52 < gamePage.playerXPosition && xPosition + 2 > gamePage.playerXPosition){
            return true;
        }
        return false;
    }
}
