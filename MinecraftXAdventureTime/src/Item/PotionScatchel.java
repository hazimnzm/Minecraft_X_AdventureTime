/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;
import Characters.Hero;
import Pages.GamePage;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author Syukri
 */
public class PotionScatchel extends Items{
    public boolean isExpanded = false;
    int size = 0;
    private PotionNode head; //Head of the linked list
    private int capacity = 5;
    public JPanel inventory = new JPanel();
    public ArrayList<PotionScatchelButton> slot = new ArrayList();
    GamePage gamePage;
    //Constructor
    public PotionScatchel(GamePage gamePage){
        this.gamePage = gamePage;
        icon = new ImageIcon("Item Icon/PotionScatchel.png");
        this.head = null;
        inventory.setBackground(Color.darkGray);
        inventory.setBounds(1280/2-389/2-100,720/2-174/2-20,45,217);
        inventory.setLayout(null);
    }
    
    //Method add potion into scctchel
    public boolean addPotion (Potion potion){
        if(size==capacity)
            return false;
        slot.add(gamePage.createPotionButton(potion));
        PotionNode newNode = new PotionNode(potion);
        if (head == null) head = newNode;
        else {
            PotionNode current = head;
            //iterate sampai habis list
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        update();
        return true;
    }
    
    
    //Method untuk use the next potion in the scatchel
    public void useNextPotion(Hero hero){
        if (head!=null){
            PotionNode usedPotion = head;
            head = head.next; //Move to the next potion
            usedPotion.potion.effect(hero);
            System.out.println("Used potion : "+usedPotion.potion);
            slot.remove(0);
            size--;
            update();
        }
        else {
            System.out.println("No more potion left in scatchel");
        }
    }
    
    public Potion removeItem(int index) {
        PotionNode current = head;
        Potion removed = null;
        try {
            if(index == 0){
                removed = head.potion;
                head = head.next;
                size--;
            }
            else{
                for(int i=1 ; i<index ; i++){
                    current = current.next;
                }
                removed = current.next.potion;
                current.next = current.next.next;
                size--;
            }
            slot.remove(index);
            update();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return removed;
    }
    
    public Potion removeItem(Potion potion){
        PotionNode current = head;
        for(int i=0 ; i<size-1 ; i++){
            if(current.potion.equals(potion)){
                return removeItem(i);
            }
        }
        return null;
    }
    
    //Method to display the potion in the scatchel
    public void displayPotions(){
        System.out.println("Potions in the scatchel : ");
        PotionNode current = head;
        if(current.next != null){
            System.out.println(current.potion.name);
            current = current.next;
        }
    }
    
    public void expand(){
        inventory.removeAll();
        for(int i=0 ; i<size ; i++){
            slot.get(i).setBounds(2,2+43*i,41,41);
            inventory.add(slot.get(i));
        }
        isExpanded = true;
        gamePage.add(inventory);
        gamePage.enderBackpack.expand();
        gamePage.enableInventory = false;
    }
    
    public void minimize(){
        inventory.removeAll();
        gamePage.remove(inventory);
        isExpanded = false;
        gamePage.enderBackpack.minimize();
        gamePage.enableInventory = true;
    }
    
    public void update(){
        if(isExpanded)
            expand();
        else
            minimize();
    }
}



    
    
    

