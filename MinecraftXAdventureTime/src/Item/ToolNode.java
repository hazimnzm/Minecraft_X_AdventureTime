/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

import javax.swing.ImageIcon;

/**
 *
 * @author Syukri
 */
public class ToolNode {
    private String type;
    private int level;
    private final int maxLevel = 11;
    private int damage;
    ImageIcon icon;
    ToolNode prev;
    ToolNode next;
    public int index;
    
    //Constructor, type, level and initial damage are initialized by the user
    public ToolNode(String type, int level, int initialDamage, int index) {
        this.type = type;
        this.level = level;
        this.damage = initialDamage;
        this.prev = null;
        this.next = null;
        this.icon = new ImageIcon("Item Icon/Sword" + index + ".png");
        this.index = index;
    }
    
    public String getType() {
        return type;
    }
    
    
    public int getLevel() {
        return level;
    }
    
    
    
    public int getDamage() {
        return damage;
    }
    
    
    public void upgrade() {
        if(this.level<maxLevel){
            this.level++;
            increaseDamage(10); // Increase damage by 10 when upgrading
        }
    }
    
    
    
    public void downgrade() {
        if (this.level > 1) {
            this.level--;
            decreaseDamage(10); // Decrease damage by 10 when downgrading
        }
    }
    
    
    private void increaseDamage(int amount) {
        this.damage += amount;
    }
    
    
    
    private void decreaseDamage(int amount) {
        this.damage -= amount;
    }
    
    
    
    public void applyDamage(int amount) {
        increaseDamage(amount);
    }
    
    
    
    public void repair(int amount) {
        decreaseDamage(amount); // Decrease the damage by the specified amount
    }
    
    
    
    @Override
    public String toString() {
        return type + "  Level " + level;
    }
}
