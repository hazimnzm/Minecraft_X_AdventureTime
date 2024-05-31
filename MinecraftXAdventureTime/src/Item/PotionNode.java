/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

/**
 *
 * @author Syukri
 */
public class PotionNode {
    //singly linked list
    Potion potion;
    PotionNode next;
    
    //Constructor
    public PotionNode (Potion potion){
        this.potion = potion;
        this.next = null;
    }
}
