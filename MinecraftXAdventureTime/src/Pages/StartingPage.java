package Pages;

import Constants.Constants;
import GUI.*;
import Input.MouseInput;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartingPage extends JPanel{
    public JButton startButton;
    public final static int Width = 1280, Height = 720;
    Game game;
    public StartingPage(Game game){
        this.game = game;
        this.setBounds(0,0,Width,Height);
        this.setBackground(Color.black);
        this.setLayout(null);
        repaint();
    }
    
    public void initializeStartButton(){
        int buttonWidth = 400, buttonHeight = 100;
        
        startButton = new JButton();
        startButton.setContentAreaFilled(false);
        startButton.setOpaque(false);
        startButton.setBorderPainted(false);
        startButton.addMouseListener(new MouseInput(game));
        startButton.setBounds(Width/2-(buttonWidth/2), Height/2-(buttonHeight/2)-100, buttonWidth, buttonHeight);
        startButton.setIcon(Constants.startButton[0]);
        this.add(startButton);
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Constants.startingPageBackground,0,0,null);  
    }
    
    
}
