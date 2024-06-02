package GUI;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
    GameWindow(GamePanel gamePanel){
        this.setTitle("Minecraft X Adventure Time");
        this.add(gamePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
