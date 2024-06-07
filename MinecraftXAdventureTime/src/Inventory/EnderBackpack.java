/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;
import GUI.Game;
import Input.KeyboardInput;
import Item.Items;
import Pages.GamePage;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
/**
 *
 * @author Syukri
 */
public class EnderBackpack<T extends Items> extends JPanel{
    
    public ArrayList<T> inventory = new ArrayList(); 
    public ArrayList<BackpackButton> slot = new ArrayList(); 
    public boolean isExpanded = false;
    Color defaultColor = new Color(181,192,210);
    Color chosenColor = new Color(21,91,99);
    private Border defaultBorder = BorderFactory.createLineBorder(defaultColor,2);
    private Border chosenBorder = BorderFactory.createLineBorder(chosenColor,3);
    GamePage gamePage;
    //constructor
    public EnderBackpack(GamePage gamePage) {
        this.inventory = new ArrayList<>();
        this.setBackground(Color.DARK_GRAY);
        this.setBounds(1280/2-389/2,650,389,45);
        this.setLayout(null);
        this.gamePage = gamePage;
    }
    
    public void addItem(T item, BackpackButton newButton) {
        inventory.add(item);
        slot.add(newButton);
        updateInventory();
    }

    public void removeItem(int index) {
        try {
            slot.remove(index);
            Items removed = inventory.remove(index);
            if(gamePage.onHand!=null && gamePage.onHand.equals(removed))
                gamePage.onHand = null;
            updateInventory();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeItem(T item){
        for(int i=0 ; i<inventory.size() ; i++){
            if(item.equals(inventory.get(i))){
                removeItem(i);
            }
        }
    }
    public int getInventorySize() {
        return inventory.size();
    }
    
    public void chooseItem(int index, GamePage gamePage){
        gamePage.onHand = inventory.get(index);
        for(int i=0 ; i<slot.size() ; i++){
            if(index==i)
                slot.get(i).setBorder(chosenBorder);
            else
                slot.get(i).setBorder(defaultBorder);
        }
    }
    
    public void expand(){
        this.removeAll();
        this.setBounds(1280/2-389/2,720/2-174/2,389,174);
        for(int i=0 ; i<slot.size() ; i++){
            slot.get(i).setBounds(2+43*(i%9),2+43*(i/9),41,41);
            this.add(slot.get(i));
        }
        isExpanded = true;
    }
    
    public void minimize(){
        this.removeAll();
        this.setBounds(1280/2-389/2,650,389,45);
        for(int i=0 ; i<slot.size() && i<9 ; i++){
            slot.get(i).setBounds(2+43*(i%9),2+43*(i/9),41,41);
            this.add(slot.get(i));
        }
        isExpanded = false;
    }
    
    public void setupInput(Game game){
        this.addKeyListener(new KeyboardInput(game));
    }
    
    public Items getItem(int index){
        return inventory.get(index);
    }
    public void updateInventory(){
        if(isExpanded)
            expand();
        else
            minimize();
    }
}
