package Pages;

import Characters.*;
import Constants.*;
import GUI.Game;
import Input.*;
import Inventory.*;
import Item.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePage extends JPanel{
    public final static int Width = 1280, Height = 720;
    
    public int aniCol = 3;
    public int aniRow = 0;
    public double aniSpeed = 6;
    public int horizontalDir = 0;
    public int verticalDir = 0;
    public int attackOffset;
    public int playerXPosition = 0;
    public int playerYPosition = 490;
    public double jumpPosition = 0;
    int aniChange = 0;
    public boolean move = false;
    public boolean isJumping = false;
    public boolean isAttacking = false;
    public int weaponIndex = 1;
    public EnderBackpack enderBackpack;
    public AdventurerDiary diary;
    public Game game;
    public KeyboardInput keyIn;
    public MouseInput mouseIn;
    public ImageIcon test;
    public Items onHand;
    public Hero currentHero;
    public int heroIndex = 0;
    public boolean enableInventory = true;
    public Chest mainChest;
    public SecuredChest securedChest;
    public SortingSystem sortingSystem;
    public TeleportationTool teleporter;
    public ArrayList<JLabel> potionEffect= new ArrayList();
    public boolean enableMovement = true;
    public boolean enableScatchel = true;
    public boolean enableDiary = true;
    public String currentLocation = Constants.treeHouse;
    public Image backgroundImage;
    public boolean drawName = false;
    public int timer = 0;
    public ArrayList<Creature> creatures = new ArrayList();
    public Random rand = new Random();
    public Encyclopedia encyclopedia;
    public JButton upgradeButton = new JButton();
    public JButton downgradeButton = new JButton();
    public Crops[] cropField;
    public AutoFarmerBlock autoFarmerBlock;
    public GamePage(Game game) throws IOException{
        this.game = game;
        this.setBounds(0,0,Width,Height);
        this.setBackground(Color.white);
        this.setLayout(null);
        enderBackpack = new EnderBackpack(this);
        this.add(enderBackpack);
        mainChest = new Chest(this);
        this.add(mainChest);
        teleporter = new TeleportationTool(this);
        this.add(teleporter);
        sortingSystem = new SortingSystem(this);
        this.add(sortingSystem.healthPotionChest);
        this.add(sortingSystem.swiftnessPotionChest);
        this.add(sortingSystem.regenerationPotionChest);
        this.add(sortingSystem.leapingPotionChest);
        encyclopedia = new Encyclopedia(this);
        this.add(encyclopedia);
        autoFarmerBlock = new AutoFarmerBlock();
        this.add(autoFarmerBlock);
        securedChest = new SecuredChest(this, game);
        this.add(securedChest);
        currentHero = game.heroes[heroIndex];
        backgroundImage = Constants.TreeHouseBackground;
        initializeButton();
        initializeCrop();
        repaint();
    }
    public void initializeCrop(){
        cropField = new Crops[5];
        for(int i=0 ; i<5 ; i++){
            cropField[i] = new Crops(i);
            this.add(cropField[i]);
        }
    }
    public void initializeButton(){
        upgradeButton.setIcon(Constants.upgradeButton[0]);
        downgradeButton.setIcon(Constants.downgradeButton[0]);
        upgradeButton.setBounds(20,200,63,46);
        downgradeButton.setBounds(20,260,63,46);
        upgradeButton.setContentAreaFilled(false);
        upgradeButton.setBorderPainted(false);
        downgradeButton.setContentAreaFilled(false);
        downgradeButton.setBorderPainted(false);
    }
    public void addEffect(JLabel effect){
        for(int i=0 ; i<potionEffect.size() ; i++){
            remove(potionEffect.get(i));
        }
        potionEffect.add(effect);
        for(int i=0 ; i<potionEffect.size() ; i++){
            potionEffect.get(i).setBounds(1280-242-30,20 + (71*i),242,66);
            add(potionEffect.get(i));
        }
    }
    
    public void removeEffect(JLabel effect){
        for(int i=0 ; i<potionEffect.size() ; i++){
            remove(potionEffect.get(i));
        }
        potionEffect.remove(effect);
        for(int i=0 ; i<potionEffect.size() ; i++){
            potionEffect.get(i).setBounds(1280-242-30,20 + (71*i),242,66);
            add(potionEffect.get(i));
        }
    }
    
    public void addNewSlot(int index, Items item){
        BackpackButton newButton = new BackpackButton(index, item);
        newButton.addMouseListener(new MouseInput(game));
        enderBackpack.addItem(item, newButton);
    }
    
    public PotionScatchelButton createPotionButton(Potion potion){
        PotionScatchelButton newButton = new PotionScatchelButton(potion);
        newButton.addMouseListener(new MouseInput(game));
        return newButton;
    }
    
    public ChestButton createChestButton(){
        ChestButton newButton = new ChestButton();
        newButton.addMouseListener(new MouseInput(game));
        return newButton;
    }
    
    public void setupInput(){
        keyIn = new KeyboardInput(game);
        mouseIn = new MouseInput(game);
        this.addKeyListener(keyIn);
        this.addMouseListener(mouseIn);
        this.requestFocusInWindow();
        mainChest.addMouseListener(mouseIn);
        teleporter.addMouseListener(mouseIn);
        teleporter.candyKingdomButton.addMouseListener(mouseIn);
        teleporter.fireKingdomButton.addMouseListener(mouseIn);
        teleporter.iceKingdomButton.addMouseListener(mouseIn);
        teleporter.treeHouseButton.addMouseListener(mouseIn);
        sortingSystem.healthPotionChest.addMouseListener(mouseIn);
        sortingSystem.regenerationPotionChest.addMouseListener(mouseIn);
        sortingSystem.swiftnessPotionChest.addMouseListener(mouseIn);
        sortingSystem.leapingPotionChest.addMouseListener(mouseIn);
        encyclopedia.addMouseListener(mouseIn);
        upgradeButton.addMouseListener(mouseIn);
        downgradeButton.addMouseListener(mouseIn);
        for(int i=0 ; i<cropField.length ; i++){
            cropField[i].addMouseListener(mouseIn);
        }
        autoFarmerBlock.addMouseListener(mouseIn);
        securedChest.addMouseListener(mouseIn);
        securedChest.finnAccessButton[0].addMouseListener(mouseIn);
        securedChest.finnAccessButton[1].addMouseListener(mouseIn);
        securedChest.jakeAccessButton[0].addMouseListener(mouseIn);
        securedChest.jakeAccessButton[1].addMouseListener(mouseIn);
        securedChest.finnButton.addMouseListener(mouseIn);
        securedChest.jakeButton.addMouseListener(mouseIn);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage,0,0,null);
        if(move || isJumping || isAttacking)
            animateCharacter();
        if(isAttacking)
            g.drawImage(currentHero.animation[aniRow][aniCol], playerXPosition+attackOffset, playerYPosition - 45 - (int)jumpPosition,128,128, this);   
        else    
            g.drawImage(currentHero.animation[aniRow][aniCol], playerXPosition, playerYPosition - (int)jumpPosition,64,64, this);   
        g.setColor(Color.black);
        g.fillRect(playerXPosition + 3, playerYPosition-(int)jumpPosition - 10, 60, 12);    
        g.setColor(Color.green);
        g.fillRect(playerXPosition + 4, playerYPosition-(int)jumpPosition - 9, (int)((double)58*(currentHero.getHp()/currentHero.getHpCapacity())), 10);    
        g.setColor(Color.darkGray);
        g.setFont(new Font("Serif", Font.PLAIN, 10));
        g.drawString((int)currentHero.getHp() + "",playerXPosition + 21,playerYPosition-(int)jumpPosition);
        if(onHand != null && onHand.getClass() == AdventurerDiary.class){
            AdventurerDiary temp = (AdventurerDiary)onHand;
            if(temp.isExpanded)
                temp.expandDiary(g);
        }
        if(onHand!=null && !(onHand.getClass() == MultiTools.class)){
            this.remove(upgradeButton);
            this.remove(downgradeButton);
        }
            
        if(onHand!=null && drawName){
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            g.drawString(onHand.toString(), 520, 620);
        }
        for(int i=0 ; i<creatures.size() ; i++){
            creatures.get(i).drawCreature(g);
        }
        if(teleporter.isExpanded)
            teleporter.drawMenu(g);
        if(encyclopedia.isExpanded)
            encyclopedia.expand(g);
        spawningAlgorithm();
        if(!currentHero.isAlive())
            resetGame();
        autoFarmerBlock.performTaskStep(cropField);
    }
    
    public void animateCharacter(){
        if(isAttacking){
            if(aniRow==0){
                aniRow = 2 + 2*(weaponIndex-1);
                attackOffset = -13;
            }
            else if(aniRow==1){
                aniRow=3 + 2*(weaponIndex-1);
                attackOffset = -20;
            }
            
            aniChange++;
            if(aniChange>=50/currentHero.attackSpeed){
                if(aniCol==3){
                    for(int i=0 ; i<creatures.size() ; i++){
                        creatures.get(i).damaged(onHand);
                    }
                    isAttacking = false;
                    aniRow = aniRow%2==0 ? 0 : 1;
                    aniCol = 2;
                }
                aniCol++;
                aniChange = 0;
            }
                return;
        }
        
        if(isJumping){
            jumpPosition += 1*currentHero.jumpSpeed*verticalDir*((currentHero.jumpHeight+10 - jumpPosition)/currentHero.jumpHeight);
            if(jumpPosition >= currentHero.jumpHeight){
                verticalDir = -1;
            }
            else if(jumpPosition <= 0){
                isJumping = false;
                jumpPosition = 0;
            }
        }
       
        if(move){
            if(horizontalDir == 1)
                aniRow = 0;
            else if(horizontalDir == -1)
                aniRow = 1;
            aniChange++;
            if(aniCol==5){
                aniCol = 0;
            }

            if(aniChange>=50/aniSpeed){
                aniCol++;
                aniChange = 0;
            }

            if(playerXPosition + currentHero.speed * horizontalDir>-10 && playerXPosition + currentHero.speed * horizontalDir<1228)
                playerXPosition += currentHero.speed*horizontalDir;
        }
    }
    
    public void spawningAlgorithm(){
        int num = rand.nextInt(600);
        if(num==40){
            switch(currentLocation){
                case Constants.iceKingdom:
                    creatures.add(encyclopedia.createMonster("Ice Golem"));
                    break;
                case Constants.fireKingdom:
                    creatures.add(encyclopedia.createMonster("Lava Golem"));
                    break;
                case Constants.candyKingdom:
                    creatures.add(encyclopedia.createMonster("Bubble Gum Golem"));
                    break;
                case Constants.treeHouse:
                    creatures.add(encyclopedia.createCreature("Sheep"));
                    break;
            }
        }
    }
    
    public void resetGame(){
        playerXPosition = 0;
        creatures.clear();
        currentLocation = Constants.treeHouse;
        backgroundImage = Constants.TreeHouseBackground;
        game.finn.respawn();
        game.jake.respawn();
        add(mainChest);
        add(sortingSystem.healthPotionChest);
        add(sortingSystem.regenerationPotionChest);
        add(sortingSystem.leapingPotionChest);
        add(sortingSystem.swiftnessPotionChest);
        add(securedChest);
        add(autoFarmerBlock);
        for(int i=0 ; i<cropField.length ; i++){
            this.add(cropField[i]);
        }
    }
}