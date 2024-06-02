package Inventory;

import Item.Items;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class BackpackButton extends JButton{
    Items item;
    Color defaultColor = new Color(181,192,210);
    private Border defaultBorder = BorderFactory.createLineBorder(defaultColor,2);
    public BackpackButton(int index, Items item){
        this.setBackground(Color.DARK_GRAY);
        this.setBorder(defaultBorder);
        this.setBounds(2+43*(index%9),2+43*(index/9),41,41);
        this.setIcon(item.icon);
    }
    
    public void changeIcon(ImageIcon icon){
        this.setIcon(icon);
    }
}
