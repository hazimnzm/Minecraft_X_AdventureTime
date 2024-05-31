package GUI;

import Pages.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    public
    final int Width = 1280, Height = 720;
    
    GamePanel(Game game){
        this.setPreferredSize(new Dimension(Width,Height));
        this.setBackground(Color.white);
        this.setLayout(null);
        this.add(game.startingPage);
    }
}
