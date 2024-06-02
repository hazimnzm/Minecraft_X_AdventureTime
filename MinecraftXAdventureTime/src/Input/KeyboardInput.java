package Input;

import GUI.Game;
import GUI.GamePanel;
import Item.*;
import Pages.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardInput implements KeyListener{
    
    StartingPage startingPage;
    GamePanel gamePanel;
    GamePage gamePage;
    Game game;
    boolean rightPressed = false;
    boolean leftPressed = false;
    public KeyboardInput(Game game){
        this.game = game;
        gamePage = this.game.gamePage;
        startingPage = this.game.startingPage;
        gamePanel = this.game.gamePanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()=='x'){
            gamePage.currentHero.damaged(100);
        }
        if(e.getKeyChar()=='z'){
            gamePage.currentHero.regen(100);
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gamePage.enableMovement){
            if(e.getKeyChar()=='d' || e.getKeyChar()=='D'){
                gamePage.move = true;
                rightPressed = true;
                gamePage.horizontalDir = 1;
            }
            else if(e.getKeyChar()=='a' || e.getKeyChar()=='A'){
                gamePage.move = true;
                leftPressed = true;
                gamePage.horizontalDir = -1;
            }
            else if(e.getKeyChar()=='w' || e.getKeyChar()=='W'){
                if(!gamePage.isJumping){
                    gamePage.isJumping = true;
                    gamePage.verticalDir = 1;
                }
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            System.out.println("up");
            gamePage.diary.offset = gamePage.diary.entries.size()-gamePage.diary.offset>11 ? gamePage.diary.offset+1 : gamePage.diary.offset;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            System.out.println("down");
            gamePage.diary.offset = gamePage.diary.offset>0 ? gamePage.diary.offset-1 : gamePage.diary.offset;
        }
       
        if(e.getKeyChar()=='e' || e.getKeyChar()=='E'){
            if(gamePage.enableInventory){
                if(!gamePage.enderBackpack.isExpanded)
                    gamePage.enderBackpack.expand();
                else
                    gamePage.enderBackpack.minimize();
                gamePage.revalidate();
                gamePage.repaint();
            }
        }
        if(e.getKeyChar()=='q' || e.getKeyChar()=='Q'){
            if(gamePage.onHand!=null && gamePage.onHand.getClass()==PotionScatchel.class && gamePage.enableScatchel){
                PotionScatchel temp = (PotionScatchel)gamePage.onHand;
                if(temp.isExpanded)
                    temp.minimize();
                else
                    temp.expand();
            }
            else if(gamePage.onHand!=null && gamePage.onHand.getClass()==AdventurerDiary.class && gamePage.enableDiary){
                AdventurerDiary temp = (AdventurerDiary)gamePage.onHand;
                temp.isExpanded = !temp.isExpanded;
            }
        }
        
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(gamePage.heroIndex==game.heroes.length-1)
                gamePage.heroIndex = 0;
            else
                gamePage.heroIndex++;
            gamePage.currentHero = game.heroes[gamePage.heroIndex];
        }
        
        
        
        
        
        
        else if(e.getKeyChar()=='p'){
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), new PotionScatchel(gamePage));
        }
        else if(e.getKeyChar()=='o'){
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), new MultiTools(gamePage.enderBackpack.inventory,gamePage.enderBackpack.slot));
        }
        else if(e.getKeyChar()=='i'){
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), new PotionOfHealing());
        }
        else if(e.getKeyChar()==';'){
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), new PotionOfRegeneration(game));
        }
        else if(e.getKeyChar()=='l'){
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), new PotionOfSwiftness(game));
        }
        else if(e.getKeyChar()=='k'){
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), new PotionOfLeaping(game));
        }
        
        
        else if(gamePage.onHand!=null){
            if(e.getKeyChar()=='1' && !gamePage.isAttacking){
                if(gamePage.onHand.getClass()==MultiTools.class){
                    MultiTools temp = (MultiTools)gamePage.onHand;
                    temp.switchMode("Sword " + 1);
                    System.out.println(1);
                    gamePage.timer = 0;
                    gamePage.drawName = true;
                }
            }
            else if(e.getKeyChar()=='2' && !gamePage.isAttacking){
                if(gamePage.onHand.getClass()==MultiTools.class){
                    MultiTools temp = (MultiTools)gamePage.onHand;
                    temp.switchMode("Sword " + 2);
                    System.out.println(2);
                    gamePage.timer = 0;
                    gamePage.drawName = true;
                }
            }
            else if(e.getKeyChar()=='3' && !gamePage.isAttacking){
                if(gamePage.onHand.getClass()==MultiTools.class){
                    MultiTools temp = (MultiTools)gamePage.onHand;
                    temp.switchMode("Sword " + 3);
                    System.out.println(3);
                    gamePage.timer = 0;
                    gamePage.drawName = true;
                }
            }
            else if(e.getKeyChar()=='4' && !gamePage.isAttacking){
                if(gamePage.onHand.getClass()==MultiTools.class){
                    MultiTools temp = (MultiTools)gamePage.onHand;
                    temp.switchMode("Sword " + 4);
                    System.out.println(4);
                    gamePage.timer = 0;
                    gamePage.drawName = true;
                }
            }

            if(gamePage.enderBackpack.isExpanded)
                gamePage.enderBackpack.expand();
            else
                gamePage.enderBackpack.minimize();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyChar()=='d' || e.getKeyChar()=='D'){
            rightPressed = false;
            if(!leftPressed){
                gamePage.move = false;
                gamePage.horizontalDir = 0;
                gamePage.aniCol = 3;
            }
            else{
                gamePage.horizontalDir = -1;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyChar()=='a' || e.getKeyChar()=='A'){
            leftPressed = false;
            if(!rightPressed){
                gamePage.move = false;
                gamePage.horizontalDir = 0;
                gamePage.aniCol = 3;
            }
            else
                gamePage.horizontalDir = 1;
        }
    }
    
}
