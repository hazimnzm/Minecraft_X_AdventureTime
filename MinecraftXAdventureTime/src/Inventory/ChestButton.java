package Inventory;

import Item.Items;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class ChestButton extends JButton{
    public Items item;
    Color defaultColor = new Color(181,192,210);
    private Border defaultBorder = BorderFactory.createLineBorder(defaultColor,2);
    
    public ChestButton(Items item){
        this.setBackground(Color.lightGray);
        this.setBorder(defaultBorder);
        this.setIcon(item.icon);
    }
    public ChestButton(){
        this.setBackground(Color.lightGray);
        this.setBorder(defaultBorder);
    }
    
    public void addItem(Items item){
        this.item = item;
        this.setIcon(item.icon);
    }
    
    public Items removeItem(){
        Items temp = item;
        item = null;
        this.setIcon(null);
        return temp;
    }
}
