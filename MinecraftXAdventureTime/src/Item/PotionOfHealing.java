package Item;

import Characters.Hero;
import javax.swing.ImageIcon;

public class PotionOfHealing extends Potion{
    
    public PotionOfHealing() {
        icon = new ImageIcon("Item Icon/PotionOfHealing.png");
        name = "Potion of Healing";
    }

    
    
    @Override
    public void effect(Hero hero) {
        hero.regen(1500);
    }
}
