/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class PotionScatchelButton extends JButton{
    Potion potion;
    
    public PotionScatchelButton(Potion potion){
        this.setBackground(Color.DARK_GRAY);
        this.setIcon(potion.icon);
    }
    
    public void changeIcon(ImageIcon icon){
        this.setIcon(icon);
    }
}
