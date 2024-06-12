package Pages;

import Constants.Constants;
import GUI.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoadingPage extends JPanel{
    
    public JButton startButton;
    public final static int Width = 1280, Height = 720;
    public boolean isLoading = false;
    public double loading = 0;
    Random rand = new Random();
    Game game;
    public LoadingPage(Game game){
        this.game = game;
        this.setBounds(0,0,Width,Height);
        this.setBackground(Color.black);
        this.setLayout(null);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constants.LoadingPageBackground,0,0,null);
        increaseLoadingPercentage();
        g.setColor(Color.lightGray);
        g.drawRect(1280/2-500, 600, 1000, 20);
        g.fillRect(1280/2-500+2, 602, (int)loading, 16);
    }
    public void increaseLoadingPercentage(){
        int add = rand.nextInt(4);
        loading += add;
        if(loading>996){
            loading = 996;
            isLoading = false;
            game.gamePanel.remove(game.loadingPage);
            game.gamePanel.add(game.gamePage);
            game.gamePage.setupInput();
            game.gamePage.repaint();
        }
    }
    
}   
