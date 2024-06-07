package Item;

import Constants.Constants;
import java.util.Random;
import javax.swing.JButton;

public class Crops extends JButton{
    public int stage = 0;
    public boolean isPlanted = false;
    public int timer = 0;
    public Random rand = new Random();
    public Crops(int index){
        this.setBounds(0+45*index, 466, 45,92);
        this.setIcon(Constants.crops[0][0]);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }
    
    public void plant(){
        isPlanted = true;
        stage = 1;
        timer = rand.nextInt(5);
        update();
    }
    
    public void harvest(){
        isPlanted = false;
        stage = 0;
        update();
    }
    
    public void update(){
        this.setIcon(Constants.crops[0][stage]);
    }
    
    public void hover(){
        this.setIcon(Constants.crops[1][stage]);
    }
    
    public void grow(){
        if(isPlanted){
            timer++;
        }
        if(stage<4 && timer==10){
            stage++;
            update();
            timer = rand.nextInt(7);
        }
    }
    
    public boolean idReadyToHarvest(){
        return stage==4;
    }
}
