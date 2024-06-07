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
                else if(gamePage.sortingSystem.healthPotionChest.isOpen && !gamePage.sortingSystem.healthPotionChest.isFull()){
                    if(gamePage.sortingSystem.healthPotionChest.addItem((Items)gamePage.enderBackpack.inventory.get(i)))
                        gamePage.enderBackpack.removeItem(i);
                }
                else if(gamePage.sortingSystem.regenerationPotionChest.isOpen && !gamePage.sortingSystem.regenerationPotionChest.isFull()){
                    if(gamePage.sortingSystem.regenerationPotionChest.addItem((Items)gamePage.enderBackpack.inventory.get(i)))
                        gamePage.enderBackpack.removeItem(i);
                }
                else if(gamePage.sortingSystem.leapingPotionChest.isOpen && !gamePage.sortingSystem.leapingPotionChest.isFull()){
                    if(gamePage.sortingSystem.leapingPotionChest.addItem((Items)gamePage.enderBackpack.inventory.get(i)))
                        gamePage.enderBackpack.removeItem(i);
                }
                else if(gamePage.sortingSystem.swiftnessPotionChest.isOpen && !gamePage.sortingSystem.swiftnessPotionChest.isFull()){
                    if(gamePage.sortingSystem.swiftnessPotionChest.addItem((Items)gamePage.enderBackpack.inventory.get(i)))
                        gamePage.enderBackpack.removeItem(i);
                }
                else if(gamePage.securedChest.isOpen && !gamePage.securedChest.isFull()){
                    if(gamePage.securedChest.addItem((Items)gamePage.enderBackpack.inventory.get(i)))
                        gamePage.enderBackpack.removeItem(i);
                }
                else if(!gamePage.enderBackpack.isExpanded){
                    gamePage.enderBackpack.chooseItem(i, gamePage);
                    if(gamePage.onHand.getClass() == MultiTools.class){
                        gamePage.add(gamePage.upgradeButton);
                        gamePage.add(gamePage.downgradeButton);
                    }
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
            else if(e.getSource()==gamePage.sortingSystem.healthPotionChest.slot[i] && gamePage.sortingSystem.healthPotionChest.slot[i].item != null){
                Items temp  = gamePage.sortingSystem.healthPotionChest.removeItem(i);
                gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), temp);
            }
            else if(e.getSource()==gamePage.sortingSystem.regenerationPotionChest.slot[i] && gamePage.sortingSystem.regenerationPotionChest.slot[i].item != null){
                Items temp  = gamePage.sortingSystem.regenerationPotionChest.removeItem(i);
                gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), temp);
            }
            else if(e.getSource()==gamePage.sortingSystem.leapingPotionChest.slot[i] && gamePage.sortingSystem.leapingPotionChest.slot[i].item != null){
                Items temp  = gamePage.sortingSystem.leapingPotionChest.removeItem(i);
                gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), temp);
            }
            else if(e.getSource()==gamePage.sortingSystem.swiftnessPotionChest.slot[i] && gamePage.sortingSystem.swiftnessPotionChest.slot[i].item != null){
                Items temp  = gamePage.sortingSystem.swiftnessPotionChest.removeItem(i);
                gamePage.addNewSlot(gamePage.enderBackpack.getInventorySize(), temp);
            }
            else if(e.getSource()==gamePage.securedChest.slot[i] && gamePage.securedChest.slot[i].item != null){
                Items temp  = gamePage.securedChest.removeItem(i);
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
        else if(e.getSource()==gamePage.encyclopedia){
            gamePage.encyclopedia.setIcon(Constants.encyclopediaButton[2]);
        }
        else if(e.getSource()==gamePage.upgradeButton){
            gamePage.upgradeButton.setIcon(Constants.upgradeButton[2]);
        }
        else if(e.getSource()==gamePage.downgradeButton){
            gamePage.downgradeButton.setIcon(Constants.downgradeButton[2]);
        }
        else if(e.getSource()==gamePage.autoFarmerBlock){
            gamePage.autoFarmerBlock.click();
        }
        
        else if(e.getSource()==gamePage.securedChest.finnAccessButton[0]){
            gamePage.securedChest.setPermissionAccess(game.finn,1);
        }
        else if(e.getSource()==gamePage.securedChest.finnAccessButton[1]){
            gamePage.securedChest.setPermissionAccess(game.finn,2);
        }
        else if(e.getSource()==gamePage.securedChest.jakeAccessButton[0]){
            gamePage.securedChest.setPermissionAccess(game.jake,1);
        }
        else if(e.getSource()==gamePage.securedChest.jakeAccessButton[1]){
            gamePage.securedChest.setPermissionAccess(game.jake,2);
        }
        else if(e.getSource()==gamePage.securedChest.finnButton){
            gamePage.securedChest.removePermission(game.finn);
        }
        else if(e.getSource()==gamePage.securedChest.jakeButton){
            gamePage.securedChest.removePermission(game.jake);
        }
        else if(e.getSource() == gamePage.mainChest && gamePage.playerXPosition - 300 >= -65 && gamePage.playerXPosition - 300 < 32){
            if(gamePage.mainChest.isOpen){
                gamePage.mainChest.setIcon(gamePage.mainChest.closeImage);
                gamePage.mainChest.isOpen = false;
                gamePage.enableMovement = true;
                gamePage.enableInventory = true;
                gamePage.enableDiary = true;
                gamePage.enableScatchel = true;
                gamePage.mainChest.minimize();
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
        
        else if(e.getSource() == gamePage.sortingSystem.healthPotionChest && gamePage.playerXPosition - 400 >= -65 && gamePage.playerXPosition - 400 < 32){
            if(gamePage.sortingSystem.healthPotionChest.isOpen){
                gamePage.sortingSystem.healthPotionChest.setIcon(gamePage.mainChest.closeImage);
                gamePage.sortingSystem.healthPotionChest.isOpen = false;
                gamePage.enableMovement = true;
                gamePage.enableInventory = true;
                gamePage.enableDiary = true;
                gamePage.enableScatchel = true;
                gamePage.sortingSystem.healthPotionChest.minimize();;
            }
            else{
                gamePage.sortingSystem.healthPotionChest.setIcon(gamePage.mainChest.openImage);
                gamePage.sortingSystem.healthPotionChest.isOpen = true;
                gamePage.enableMovement = false;
                gamePage.enableInventory = false;
                gamePage.enableDiary = false;
                gamePage.enableScatchel = false;
                gamePage.move = false;
                gamePage.sortingSystem.healthPotionChest.expand();
            }
        }
        
        else if(e.getSource() == gamePage.sortingSystem.regenerationPotionChest && gamePage.playerXPosition - 400 >= -65 && gamePage.playerXPosition - 400 < 32){
            if(gamePage.sortingSystem.regenerationPotionChest.isOpen){
                gamePage.sortingSystem.regenerationPotionChest.setIcon(gamePage.mainChest.closeImage);
                gamePage.sortingSystem.regenerationPotionChest.isOpen = false;
                gamePage.enableMovement = true;
                gamePage.enableInventory = true;
                gamePage.enableDiary = true;
                gamePage.enableScatchel = true;
                gamePage.sortingSystem.regenerationPotionChest.minimize();;
            }
            else{
                gamePage.sortingSystem.regenerationPotionChest.setIcon(gamePage.mainChest.openImage);
                gamePage.sortingSystem.regenerationPotionChest.isOpen = true;
                gamePage.enableMovement = false;
                gamePage.enableInventory = false;
                gamePage.enableDiary = false;
                gamePage.enableScatchel = false;
                gamePage.move = false;
                gamePage.sortingSystem.regenerationPotionChest.expand();
            }
        }
        
        else if(e.getSource() == gamePage.sortingSystem.swiftnessPotionChest && gamePage.playerXPosition - 400 >= -65 && gamePage.playerXPosition - 400 < 32){
            if(gamePage.sortingSystem.swiftnessPotionChest.isOpen){
                gamePage.sortingSystem.swiftnessPotionChest.setIcon(gamePage.mainChest.closeImage);
                gamePage.sortingSystem.swiftnessPotionChest.isOpen = false;
                gamePage.enableMovement = true;
                gamePage.enableInventory = true;
                gamePage.enableDiary = true;
                gamePage.enableScatchel = true;
                gamePage.sortingSystem.swiftnessPotionChest.minimize();;
            }
            else{
                gamePage.sortingSystem.swiftnessPotionChest.setIcon(gamePage.mainChest.openImage);
                gamePage.sortingSystem.swiftnessPotionChest.isOpen = true;
                gamePage.enableMovement = false;
                gamePage.enableInventory = false;
                gamePage.enableDiary = false;
                gamePage.enableScatchel = false;
                gamePage.move = false;
                gamePage.sortingSystem.swiftnessPotionChest.expand();
            }
        }
        
        else if(e.getSource() == gamePage.sortingSystem.leapingPotionChest && gamePage.playerXPosition - 400 >= -65 && gamePage.playerXPosition - 400 < 32){
            if(gamePage.sortingSystem.leapingPotionChest.isOpen){
                gamePage.sortingSystem.leapingPotionChest.setIcon(gamePage.mainChest.closeImage);
                gamePage.sortingSystem.leapingPotionChest.isOpen = false;
                gamePage.enableMovement = true;
                gamePage.enableInventory = true;
                gamePage.enableDiary = true;
                gamePage.enableScatchel = true;
                gamePage.sortingSystem.leapingPotionChest.minimize();;
            }
            else{
                gamePage.sortingSystem.leapingPotionChest.setIcon(gamePage.mainChest.openImage);
                gamePage.sortingSystem.leapingPotionChest.isOpen = true;
                gamePage.enableMovement = false;
                gamePage.enableInventory = false;
                gamePage.enableDiary = false;
                gamePage.enableScatchel = false;
                gamePage.move = false;
                gamePage.sortingSystem.leapingPotionChest.expand();
            }
        }
        
        else if(e.getSource() == gamePage.securedChest && gamePage.playerXPosition - 435 >= -65 && gamePage.playerXPosition - 435 < 32 && gamePage.securedChest.getPermissionLevel(gamePage.currentHero)>0){
            if(gamePage.securedChest.isOpen){
                gamePage.securedChest.setIcon(gamePage.securedChest.closeImage);
                gamePage.securedChest.isOpen = false;
                gamePage.enableMovement = true;
                gamePage.enableInventory = true;
                gamePage.enableDiary = true;
                gamePage.enableScatchel = true;
                gamePage.securedChest.minimize();;
            }
            else{
                gamePage.securedChest.setIcon(gamePage.securedChest.openImage);
                gamePage.securedChest.isOpen = true;
                gamePage.enableMovement = false;
                gamePage.enableInventory = false;
                gamePage.enableDiary = false;
                gamePage.enableScatchel = false;
                gamePage.move = false;
                gamePage.securedChest.expand();
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
                gamePage.creatures.clear();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(e.getSource() == gamePage.teleporter.iceKingdomButton)
            try {
                gamePage.teleporter.checkAndTeleport(gamePage.currentLocation, Constants.iceKingdom);
                gamePage.creatures.clear();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(e.getSource() == gamePage.teleporter.fireKingdomButton)
            try {
                gamePage.teleporter.checkAndTeleport(gamePage.currentLocation, Constants.fireKingdom);
                gamePage.creatures.clear();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(e.getSource() == gamePage.teleporter.candyKingdomButton)
            try {
                gamePage.teleporter.checkAndTeleport(gamePage.currentLocation, Constants.candyKingdom);
                gamePage.creatures.clear();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        else
            for(int i=0 ; i<5 ; i++){
                if(e.getSource() == gamePage.cropField[i]){
                    if(gamePage.cropField[i].isPlanted)
                        gamePage.cropField[i].harvest();
                    else
                        gamePage.cropField[i].plant();
                }
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
            gamePage.sortingSystem.healthPotionChest.createSlot();
            gamePage.sortingSystem.regenerationPotionChest.createSlot();
            gamePage.sortingSystem.swiftnessPotionChest.createSlot();
            gamePage.sortingSystem.leapingPotionChest.createSlot();
            gamePage.securedChest.createSlot();
        }
        else if(e.getSource()==gamePage.encyclopedia){
            gamePage.encyclopedia.setIcon(Constants.encyclopediaButton[0]);
            gamePage.encyclopedia.isExpanded = !gamePage.encyclopedia.isExpanded;
        }
        else if(e.getSource()==gamePage.upgradeButton){
            gamePage.upgradeButton.setIcon(Constants.upgradeButton[0]);
            MultiTools temp = (MultiTools)gamePage.onHand;
            temp.upgrade();
            gamePage.timer=0;
            gamePage.drawName = true;
        }
        else if(e.getSource()==gamePage.downgradeButton){
            gamePage.downgradeButton.setIcon(Constants.downgradeButton[0]);
            MultiTools temp = (MultiTools)gamePage.onHand;
            temp.downgrade();
            gamePage.timer=0;
            gamePage.drawName = true;
        }
        else if(e.getSource()==gamePage.autoFarmerBlock){
            gamePage.autoFarmerBlock.release();
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
        else if(e.getSource()==gamePage.encyclopedia){
            gamePage.encyclopedia.setIcon(Constants.encyclopediaButton[1]);
        }
        else if(e.getSource()==gamePage.upgradeButton){
            gamePage.upgradeButton.setIcon(Constants.upgradeButton[1]);
        }
        else if(e.getSource()==gamePage.downgradeButton){
            gamePage.downgradeButton.setIcon(Constants.downgradeButton[1]);
        }
        else if(e.getSource()==gamePage.autoFarmerBlock){
            gamePage.autoFarmerBlock.hover();
        }
        else{
            for(int i=0 ; i<5 ; i++){
                if(e.getSource() == gamePage.cropField[i]){
                    gamePage.cropField[i].hover();
                }
            }
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
        else if(e.getSource()==gamePage.encyclopedia){
            gamePage.encyclopedia.setIcon(Constants.encyclopediaButton[0]);
        }
        else if(e.getSource()==gamePage.upgradeButton){
            gamePage.upgradeButton.setIcon(Constants.upgradeButton[0]);
        }
        else if(e.getSource()==gamePage.downgradeButton){
            gamePage.downgradeButton.setIcon(Constants.downgradeButton[0]);
        }
        else if(e.getSource()==gamePage.autoFarmerBlock){
            gamePage.autoFarmerBlock.exit();
        }
        else{
            for(int i=0 ; i<5 ; i++){
                if(e.getSource() == gamePage.cropField[i]){
                    gamePage.cropField[i].update();
                }
            }
        }
    }
        
}
