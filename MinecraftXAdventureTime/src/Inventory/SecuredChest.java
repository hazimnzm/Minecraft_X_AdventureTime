package Inventory;

import Characters.Hero;
import Constants.Constants;
import GUI.Game;
import Pages.GamePage;
import java.awt.Color;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
    
public class SecuredChest extends Chest{
    
    HashMap<Hero,Integer> access = new HashMap();     
    JPanel configPanel;
    public JButton finnButton;
    public JButton jakeButton;
    public JButton[] finnAccessButton = new JButton[2];
    public JButton[] jakeAccessButton = new JButton[2];
    Game game;
    // Constructor to initialize the Securechest HashMap

    public SecuredChest(GamePage gamePage, Game game) {
        super(gamePage);
        this.game = game;
        openImage = new ImageIcon("Item Image/SecureChestOpen.png");
        closeImage = new ImageIcon("Item Image/SecureChestClose.png");
        this.setIcon(closeImage);
        this.setBounds(470,513,22,40);
        configPanel = new JPanel();
        configPanel.setBackground(Color.darkGray);
        configPanel.setBounds(900,720/2-174/2,300,174);
        configPanel.setLayout(null);
        finnButton = new JButton();
        jakeButton = new JButton();
        finnButton.setBounds(11,11,70,70);
        jakeButton.setBounds(11,93,70,70);
        finnButton.setIcon(Constants.finnButton[0]);
        jakeButton.setIcon(Constants.jakeButton[0]);
        finnButton.setContentAreaFilled(false);
        finnButton.setBorderPainted(false);
        jakeButton.setContentAreaFilled(false);
        jakeButton.setBorderPainted(false);
        configPanel.add(jakeButton);
        configPanel.add(finnButton);
        finnAccessButton[0] = new JButton();
        finnAccessButton[1] = new JButton();
        finnAccessButton[0].setIcon(Constants.oneButton[0]);
        finnAccessButton[1].setIcon(Constants.twoButton[0]);
        finnAccessButton[0].setBorderPainted(false);
        finnAccessButton[1].setBorderPainted(false);
        finnAccessButton[0].setContentAreaFilled(false);
        finnAccessButton[1].setContentAreaFilled(false);
        finnAccessButton[0].setBounds(120,21,60,50);
        finnAccessButton[1].setBounds(200,21,60,50);
        jakeAccessButton[0] = new JButton();
        jakeAccessButton[1] = new JButton();
        jakeAccessButton[0].setIcon(Constants.oneButton[0]);
        jakeAccessButton[1].setIcon(Constants.twoButton[0]);
        jakeAccessButton[0].setBorderPainted(false);
        jakeAccessButton[1].setBorderPainted(false);
        jakeAccessButton[0].setContentAreaFilled(false);
        jakeAccessButton[1].setContentAreaFilled(false);
        jakeAccessButton[0].setBounds(120,103,60,50);
        jakeAccessButton[1].setBounds(200,103,60,50);
        configPanel.add(finnAccessButton[0]);
        configPanel.add(finnAccessButton[1]);
        configPanel.add(jakeAccessButton[0]);
        configPanel.add(jakeAccessButton[1]);
        setPermissionAccess(game.finn, 2);
    }
    
 
    //tambah permission to player to access the chest
    public void setPermissionAccess(Hero hero, int level){
        access.remove(hero);
        access.put(hero, level);
        update();
    }
    //remove permission player to acces the chaest
    public void removePermission(Hero remove){
        access.remove(remove);
        update();
    }
    
    public int getPermissionLevel(Hero hero){
        if(access.containsKey(hero))
            return access.get(hero);
        else
            return 0;
    }
    
    public void update(){
        int level;
        if(access.containsKey(game.finn)){
            finnButton.setIcon(Constants.finnButton[1]);
            level = access.get(game.finn);
            if(level==1){
                finnAccessButton[0].setIcon(Constants.oneButton[1]);
                finnAccessButton[1].setIcon(Constants.twoButton[0]);
            }        
            else{
                finnAccessButton[0].setIcon(Constants.oneButton[0]);
                finnAccessButton[1].setIcon(Constants.twoButton[1]);
            }
                
        }
        else{
            finnButton.setIcon(Constants.finnButton[0]);
            finnAccessButton[0].setIcon(Constants.oneButton[0]);
            finnAccessButton[1].setIcon(Constants.twoButton[0]);
        }
        if(access.containsKey(game.jake)){
            jakeButton.setIcon(Constants.jakeButton[1]);
            level = access.get(game.jake);
            if(level==1){
                jakeAccessButton[0].setIcon(Constants.oneButton[1]);
                jakeAccessButton[1].setIcon(Constants.twoButton[0]);
            }        
            else{
                jakeAccessButton[0].setIcon(Constants.oneButton[0]);
                jakeAccessButton[1].setIcon(Constants.twoButton[1]);
            }
        }
        else{
            jakeButton.setIcon(Constants.jakeButton[0]);
            jakeAccessButton[0].setIcon(Constants.oneButton[0]);
            jakeAccessButton[1].setIcon(Constants.twoButton[0]);
        }
        
    }
    
    //chcek ada permission tak by input the nama player
//    public boolean checkpermission(String player){
//        return Securechest.containsKey(player);
//    }
    //nak nama player yang ada access to the chest

    @Override
    public void expand() {
        super.expand();
        if(getPermissionLevel(gamePage.currentHero)==2)
            gamePage.add(configPanel);
    }

    @Override
    public void minimize() {
        super.minimize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        gamePage.remove(configPanel);
    }
    
    
}
