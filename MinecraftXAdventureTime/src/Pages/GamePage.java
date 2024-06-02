package Pages;

import Characters.Hero;
import Constants.*;
import GUI.Game;
import Input.*;
import Inventory.*;
import Item.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
    public MainChest mainChest;
    public TeleportationTool teleporter;
    public ArrayList<JLabel> potionEffect= new ArrayList();
    public boolean enableMovement = true;
    public boolean enableScatchel = true;
    public boolean enableDiary = true;
    public String currentLocation = Constants.treeHouse;
    public Image backgroundImage;
    public boolean drawName = false;
    public int timer = 0;
    
    public GamePage(Game game) throws IOException{
        this.game = game;
        this.setBounds(0,0,Width,Height);
        this.setBackground(Color.white);
        this.setLayout(null);
        enderBackpack = new EnderBackpack();
        this.add(enderBackpack);
        mainChest = new MainChest(this);
        this.add(mainChest);
        teleporter = new TeleportationTool(this);
        this.add(teleporter);
        currentHero = game.heroes[heroIndex];
        backgroundImage = Constants.TreeHouseBackground;
        repaint();
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
        g.setColor(Color.red);
        g.fillRect(playerXPosition + 4, playerYPosition-(int)jumpPosition - 9, (int)((double)58*(game.finn.getHp()/game.finn.getHpCapacity())), 10);    
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.PLAIN, 10));
        g.drawString((int)currentHero.getHp() + "",playerXPosition + 21,playerYPosition-(int)jumpPosition);
        if(onHand != null && onHand.getClass() == AdventurerDiary.class){
            AdventurerDiary temp = (AdventurerDiary)onHand;
            if(temp.isExpanded)
                temp.expandDiary(g);
        }
        if(teleporter.isExpanded)
            teleporter.drawMenu(g);
        if(onHand!=null && drawName){
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            g.drawString(onHand.toString(), 520, 620);
        }
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
}
