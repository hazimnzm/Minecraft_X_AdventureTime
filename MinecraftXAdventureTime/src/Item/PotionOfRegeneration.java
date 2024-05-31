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
public class PotionOfRegeneration extends Potion implements Runnable {
    int duration = 10;
    Hero hero;
    Thread timer;
    JLabel effectLabel;
    ImageIcon effectIcon;
    Game game;
    public PotionOfRegeneration(Game game) {
        this.game = game;
        icon = new ImageIcon("Item Icon/PotionOfRegeneration.png");
        name = "Potion of Regeneration";
        effectIcon = new ImageIcon("PotionEffectIcon/PotionOfRegenerationEffect.png");
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
        long lastCheck = System.nanoTime();
        long currentTime;
        while(duration>0){
            currentTime = System.nanoTime();
            if(currentTime-lastCheck>=1000000000){
                lastCheck = currentTime;
                hero.regen(100);
                duration--;
            }
        }
        game.gamePage.removeEffect(effectLabel);
    }
    
}
