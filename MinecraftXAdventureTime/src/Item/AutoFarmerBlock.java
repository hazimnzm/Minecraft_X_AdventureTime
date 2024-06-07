package Item;

import Constants.Constants;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;

public class AutoFarmerBlock extends JButton{
    private Queue<Crops> taskQueue;
    private boolean isActivated = false;
    private int index = 0;
    public AutoFarmerBlock() {
        this.taskQueue = new LinkedList<>();
        this.setIcon(Constants.autoFarmerButton[0]);
        this.setBounds(225,508, 47,47);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }

    public void addCrop(Crops crop) {
        crop.plant();
        taskQueue.offer(crop);
    }

    public void toggle(){
        isActivated = !isActivated;
        if(isActivated)
            index = 3;
        else 
            index = 0;
    }
    
    public void performTaskStep(Crops[] cropField) {
        if(!isActivated)
            return;
        
        if (!taskQueue.isEmpty()) {
            Crops crop = taskQueue.peek();
            if(crop.idReadyToHarvest()){
                crop.harvest();
                taskQueue.poll();
                crop.plant();
                taskQueue.add(crop);
            }
        } else {
            for(int i=0 ; i<cropField.length ; i++){
                // Add a new crop to the queue if it's empty for continuous operation
                addCrop(cropField[i]); // Example crop
            }
        }
    }
    
    public void hover(){
        this.setIcon(Constants.autoFarmerButton[1]);
    }
    
    public void click(){
        this.setIcon(Constants.autoFarmerButton[2]);
    }
    
    public void exit(){
        this.setIcon(Constants.autoFarmerButton[index]);
    }
    
    public void release(){
        this.setIcon(Constants.autoFarmerButton[1]);
        toggle();
    }
}

