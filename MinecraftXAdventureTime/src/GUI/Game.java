package GUI;

import Characters.Hero;
import Inventory.EnderBackpack;
import Item.*;
import Pages.*;
import java.io.IOException;

public class Game implements Runnable{
    public GamePanel gamePanel;
    public GameWindow gameWindow;
    public StartingPage startingPage;
    public GamePage gamePage;
    public Hero finn = new Hero(5000 , "Finn", 4,6);
    public Hero jake = new Hero(5000 , "Jake", 4,6);
    public Hero[] heroes = {finn,jake};
    private Thread gameThread; 
    private final int FPS_SET = 100;
    private final int UPS_SET = 120;
    
    public Game() throws IOException{
        finn.loadAnimation();
        jake.loadAnimation();
        gamePage = new GamePage(this);
        startingPage = new StartingPage(this);
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        startingPage.initializeStartButton();
        startGameLoop();
    }
    
    
    
    public void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        long previousFrame = System.nanoTime();
        long currentFrame;
        double timePerFrame = 1000000000/FPS_SET;
        long previousUpdate = System.nanoTime();
        long currentUpdate;
        double timePerUpdate = 1000000000/UPS_SET;
        double DeltaF=0;
        double DeltaU=0;
        int frame = 0;
        int update = 0;
        double lastCheck = System.currentTimeMillis();
        
        while(true){
            currentFrame = System.nanoTime();
            DeltaF += (currentFrame - previousFrame)/timePerFrame;
            previousFrame = currentFrame;
            
            currentUpdate = System.nanoTime();
            DeltaU += (currentUpdate - previousUpdate)/timePerUpdate;
            previousUpdate = currentUpdate;
            
            if(DeltaF>=1){
                DeltaF--;
                gamePage.repaint();
                gamePage.requestFocus();
                frame++;
            }
            
            if(DeltaU>=1){
                DeltaU--;
                //update();
                update++;
            }
            
                    
            if(System.currentTimeMillis() - lastCheck >=1000){
                lastCheck = System.currentTimeMillis();
//                System.out.println("FPS = " + frame);
//                System.out.println("UPS = " + update);
                frame = 0;
                update = 0;
                gamePage.timer++;
                if(gamePage.timer==3){
                    gamePage.timer = 0;
                    gamePage.drawName = false;
                }
                for(int i=0 ; i<5 ; i++){
                    gamePage.cropField[i].grow();
                }
                
            }
        }
    }
}
