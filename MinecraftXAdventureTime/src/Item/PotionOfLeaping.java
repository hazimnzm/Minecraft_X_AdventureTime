/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

import Characters.Hero;
import GUI.Game;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class PotionOfLeaping extends Potion implements Runnable{
    int duration = 15;
    Hero hero;
    Thread timer;
    Game game;
    JLabel effectLabel;
    ImageIcon effectIcon;
    public PotionOfLeaping(Game game) {
        icon = new ImageIcon("Item Icon/PotionOfLeaping.png");
        this.game = game;
        name = "Potion of Leaping";
        effectIcon = new ImageIcon("PotionEffectIcon/PotionOfLeapingEffect.png");
        effectLabel = new JLabel();
        effectLabel.setOpaque(false);
        effectLabel.setIcon(effectIcon);
    }

    @Override
    public void effect(Hero hero) {
        this.hero = hero;
        timer = new Thread(this);
        timer.start();
    }

    @Override
    public void run() {
        game.gamePage.addEffect(effectLabel);
        hero.jumpHeight+=50;
        hero.jumpSpeed+=5;
        long lastCheck = System.nanoTime();
        long currentTime;
        while(duration>0 || game.gamePage.isJumping){
            currentTime = System.nanoTime();
            if(currentTime-lastCheck>=1000000000){
                lastCheck = currentTime;
                duration--;
            }
        }
        game.gamePage.removeEffect(effectLabel);
        hero.jumpHeight-=50;
        hero.jumpSpeed-=5;
    }
}
