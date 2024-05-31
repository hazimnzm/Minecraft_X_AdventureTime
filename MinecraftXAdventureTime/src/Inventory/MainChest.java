package Inventory;

import Item.Items;
import Pages.GamePage;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainChest extends JButton{
    public boolean isOpen;
    public ImageIcon openImage = new ImageIcon("Item Image/ChestOpen.png");
    public ImageIcon closeImage = new ImageIcon("Item Image/ChestClose.png");
    public ChestButton[] slot = new ChestButton[9];
    public JPanel inventory = new JPanel();
    public boolean isExpanded = false;
    Color defaultColor = new Color(181,192,210);
    Color chosenColor = new Color(21,91,99);
    private Border defaultBorder = BorderFactory.createLineBorder(defaultColor,2);
    private Border chosenBorder = BorderFactory.createLineBorder(chosenColor,3);
    int size = 0;
    GamePage gamePage;
    public MainChest(GamePage gamePage){
        this.gamePage = gamePage;
        this.setBounds(100,513,22,40);
        this.setBackground(Color.black);
        this.setOpaque(false);
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setIcon(closeImage);
        inventory.setBackground(Color.darkGray);
        inventory.setBounds(1280/2-389/2-186,720/2-131/2,131,131);
        inventory.setLayout(null);
        
    }
    
    public void createSlot(){
        for(int i=0 ;i<9 ; i++){
            slot[i] = gamePage.createChestButton();
        }
    }
    public void addItem(Items item){
        if(!isFull()){
            for(int i=0 ; i<9 ; i++){
                if(slot[i].item==null){
                    slot[i].addItem(item);
                    size++;
                    return;
                }
            }
        }
    }
    
    public Items removeItem(int index){
        if(!isEmpty()){
            size--;
            return slot[index].removeItem();
        }
        return null;
    }
    public void expand(){
        inventory.removeAll();
        for(int i=0 ; i<slot.length ; i++){
            slot[i].setBounds(2+43*(i%3),2+43*(i/3),41,41);
            inventory.add(slot[i]);
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
    
    public boolean isFull(){
        return size==9;
    }
    public boolean isEmpty(){
        return size==0;
    }
}
