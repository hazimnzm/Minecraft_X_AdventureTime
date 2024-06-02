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
public class PotionOfSwiftness extends Potion implements Runnable{
    int duration = 15;
    Hero hero;
    Thread timer;
    Game game;
    JLabel effectLabel;
    ImageIcon effectIcon;
    
    public PotionOfSwiftness(Game game) {
        this.game = game;
        icon = new ImageIcon("Item Icon/PotionOfSwiftness.png");
        name = "Potion of Swiftness";
        effectIcon = new ImageIcon("PotionEffectIcon/PotionOfSwiftnessEffect.png");
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
        hero.speed++;
        game.gamePage.addEffect(effectLabel);
        long lastCheck = System.nanoTime();
        long currentTime;
        while(duration>0){
            currentTime = System.nanoTime();
            if(currentTime-lastCheck>=1000000000){
                lastCheck = currentTime;
                duration--;
            }
        }
        hero.speed--;
        game.gamePage.removeEffect(effectLabel);
    }
       
}
