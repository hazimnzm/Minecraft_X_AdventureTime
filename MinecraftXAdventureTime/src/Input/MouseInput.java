package Input;

import Constants.Constants;
import GUI.*;
import Inventory.BackpackButton;
import Item.*;
import Pages.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseInput implements MouseListener{
    StartingPage startingPage;
    GamePanel gamePanel;
    GamePage gamePage;
    Game game;

    public MouseInput(Game game){
        this.game = game;
        gamePage = this.game.gamePage;
        startingPage = this.game.startingPage;
        gamePanel = this.game.gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i=0 ; i<gamePage.enderBackpack.slot.size() ; i++){
            if(e.getSource()==gamePage.enderBackpack.slot.get(i)){
                if(gamePage.onHand!=null && gamePage.onHand.getClass() == PotionScatchel.class && gamePage.enderBackpack.inventory.get(i) instanceof Potion && gamePage.enderBackpack.isExpanded){
                    PotionScatchel temp = (PotionScatchel)gamePage.onHand;
                    if(temp.isExpanded && temp.addPotion((Potion)gamePage.enderBackpack.inventory.get(i))){
                        gamePage.enderBackpack.removeItem(i);
                    }
                }
                else if(gamePage.mainChest.isOpen && !gamePage.mainChest.isFull()){
                    gamePage.mainChest.addItem((Items)gamePage.enderBackpack.inventory.get(i));
                    gamePage.enderBackpack.removeItem(i);
                }
                else if(!gamePage.enderBackpack.isExpanded){
                    gamePage.enderBackpack.chooseItem(i, gamePage);
                    System.out.println(gamePage.onHand);
                    gamePage.timer = 0;
                    gamePage.drawName = true;
                }
            return;
            }
        }
        
        for(int i=0 ; i<9 ; i++){
            if(e.getSource()==gamePage.mainChest.slot[i] && gamePage.mainChest.slot[i].item != null){
                Items temp  = gamePage.mainChest.removeItem(i);
                gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), temp);
            }
        }
        
        if(gamePage.onHand!=null && gamePage.onHand.getClass() == PotionScatchel.class){
            PotionScatchel temp = (PotionScatchel)gamePage.onHand;
            if(temp.isExpanded){
                for(int i=0 ; i<temp.slot.size() ; i++){
                    if(e.getSource() == temp.slot.get(i)){
                        gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(),temp.removeItem(i));
                    }
                }
            }
        }
        
        if(gamePage.onHand != null){
            if(gamePage.onHand.getClass() == MultiTools.class && !gamePage.isAttacking && !gamePage.isJumping){
                gamePage.aniCol = 0;
                MultiTools temp = (MultiTools)gamePage.onHand;
                gamePage.weaponIndex = temp.getCurrentTool().index;
                gamePage.isAttacking = true;
            }
            else if(gamePage.onHand instanceof Potion){
                Potion temp = (Potion)gamePage.onHand;
                temp.effect(gamePage.currentHero);
                gamePage.enderBackpack.removeItem(temp);
                gamePage.onHand = null;
            }
            else if(gamePage.onHand.getClass() == PotionScatchel.class){
                PotionScatchel temp = (PotionScatchel)gamePage.onHand;
                if(!temp.isExpanded)
                    temp.useNextPotion(gamePage.currentHero);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==startingPage.startButton){
            startingPage.startButton.setIcon(Constants.startButton[2]);
        }
        else if(e.getSource() == gamePage.mainChest && gamePage.playerXPosition - 100 >= -65 && gamePage.playerXPosition - 100 < 32){
            if(gamePage.mainChest.isOpen){
                gamePage.mainChest.setIcon(gamePage.mainChest.closeImage);
                gamePage.mainChest.isOpen = false;
                gamePage.enableMovement = true;
                gamePage.enableInventory = true;
                gamePage.enableDiary = true;
                gamePage.enableScatchel = true;
                gamePage.mainChest.minimize();;
            }
            else{
                gamePage.mainChest.setIcon(gamePage.mainChest.openImage);
                gamePage.mainChest.isOpen = true;
                gamePage.enableMovement = false;
                gamePage.enableInventory = false;
                gamePage.enableDiary = false;
                gamePage.enableScatchel = false;
                gamePage.move = false;
                gamePage.mainChest.expand();
            }
            
        }
        else if(e.getSource() == gamePage.teleporter && gamePage.playerXPosition - 1120 >= -65 && gamePage.playerXPosition - 1120 < 100){
            if(gamePage.teleporter.isExpanded){
                gamePage.teleporter.minimize();
                gamePage.teleporter.isExpanded = false;
                gamePage.enableMovement = true;
                gamePage.enableInventory = true;
                gamePage.enableDiary = true;
                gamePage.enableScatchel = true;
            }
            else{
                gamePage.teleporter.expand();
                gamePage.teleporter.isExpanded = true;
                gamePage.enableMovement = false;
                gamePage.enableInventory = false;
                gamePage.enableDiary = false;
                gamePage.enableScatchel = false;
                gamePage.move = false;
            }
        }
        else if(e.getSource() == gamePage.teleporter.treeHouseButton)
            try {
                gamePage.teleporter.checkAndTeleport(gamePage.currentLocation, Constants.treeHouse);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(e.getSource() == gamePage.teleporter.iceKingdomButton)
            try {
                gamePage.teleporter.checkAndTeleport(gamePage.currentLocation, Constants.iceKingdom);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(e.getSource() == gamePage.teleporter.fireKingdomButton)
            try {
                gamePage.teleporter.checkAndTeleport(gamePage.currentLocation, Constants.fireKingdom);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(e.getSource() == gamePage.teleporter.candyKingdomButton)
            try {
                gamePage.teleporter.checkAndTeleport(gamePage.currentLocation, Constants.candyKingdom);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==startingPage.startButton){
            startingPage.startButton.setIcon(Constants.startButton[1]);
            gamePanel.remove(startingPage);
            gamePanel.add(gamePage);
            gamePage.setupInput();
            gamePage.diary = new AdventurerDiary();
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), gamePage.diary);
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), new MultiTools(gamePage.enderBackpack.inventory, gamePage.enderBackpack.slot));
            gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), new PotionScatchel(game.gamePage));
            gamePage.mainChest.createSlot();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==startingPage.startButton){
            startingPage.startButton.setIcon(Constants.startButton[1]);
        }
        else if(e.getSource()==gamePage.teleporter.candyKingdomButton && gamePage.teleporter.candyKingdomButton.isEnabled()){
            gamePage.teleporter.candyKingdomButton.setIcon(Constants.candyKingdomButton[1]);
        }
        else if(e.getSource()==gamePage.teleporter.iceKingdomButton && gamePage.teleporter.iceKingdomButton.isEnabled()){
            gamePage.teleporter.iceKingdomButton.setIcon(Constants.iceKingdomButton[1]);
        }
        else if(e.getSource()==gamePage.teleporter.fireKingdomButton && gamePage.teleporter.fireKingdomButton.isEnabled()){
            gamePage.teleporter.fireKingdomButton.setIcon(Constants.fireKingdomButton[1]);
        }
        else if(e.getSource()==gamePage.teleporter.treeHouseButton && gamePage.teleporter.treeHouseButton.isEnabled()){
            gamePage.teleporter.treeHouseButton.setIcon(Constants.treeHouseButton[1]);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==startingPage.startButton ){
            startingPage.startButton.setIcon(Constants.startButton[0]);
        }
        else if(e.getSource()==gamePage.teleporter.candyKingdomButton && gamePage.teleporter.candyKingdomButton.isEnabled()){
            gamePage.teleporter.candyKingdomButton.setIcon(Constants.candyKingdomButton[0]);
        }
        else if(e.getSource()==gamePage.teleporter.iceKingdomButton && gamePage.teleporter.iceKingdomButton.isEnabled()){
            gamePage.teleporter.iceKingdomButton.setIcon(Constants.iceKingdomButton[0]);
        }
        else if(e.getSource()==gamePage.teleporter.fireKingdomButton & gamePage.teleporter.fireKingdomButton.isEnabled()){
            gamePage.teleporter.fireKingdomButton.setIcon(Constants.fireKingdomButton[0]);
        }
        else if(e.getSource()==gamePage.teleporter.treeHouseButton && gamePage.teleporter.treeHouseButton.isEnabled()){
            gamePage.teleporter.treeHouseButton.setIcon(Constants.treeHouseButton[0]);
        }
    }
        
}
