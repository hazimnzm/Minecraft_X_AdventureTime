package Item;

import Constants.Constants;
import Pages.GamePage;
import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;


//ALL OF THIS IS TELEPORTATION TOOL AND BENDA YG AKU NK DISPALAY AKU STUPID BUT KAU MODIFY IKUT KAU OUNYA GUI
public class TeleportationTool extends JButton {
    private TheMap<String, Integer> tool;
    GamePage gamePage;
    private ImageIcon itemImage = new ImageIcon("Item Image/Teleporter.png");
    public Image itemMenu = new ImageIcon("Item Image/TeleporterMenu.png").getImage();
    public boolean isExpanded = false;
    public JButton treeHouseButton;
    public JButton iceKingdomButton;
    public JButton candyKingdomButton;
    public JButton fireKingdomButton;
    
    public TeleportationTool(GamePage gamePage) {
        this.gamePage = gamePage;
        this.setBounds(1120,466,101,122);
        this.setBackground(Color.black);
        this.setOpaque(false);
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setIcon(itemImage);
        treeHouseButton = new JButton();
        treeHouseButton.setOpaque(false);
        treeHouseButton.setBorderPainted(false);
        treeHouseButton.setContentAreaFilled(false);
        treeHouseButton.setIcon(Constants.treeHouseButton[0]);
        treeHouseButton.setBounds(557,54,166,166);
        candyKingdomButton = new JButton();
        candyKingdomButton.setOpaque(false);
        candyKingdomButton.setBorderPainted(false);
        candyKingdomButton.setContentAreaFilled(false);
        candyKingdomButton.setIcon(Constants.candyKingdomButton[0]);
        candyKingdomButton.setBounds(1005,54,166,166);
        fireKingdomButton = new JButton();
        fireKingdomButton.setOpaque(false);
        fireKingdomButton.setBorderPainted(false);
        fireKingdomButton.setContentAreaFilled(false);
        fireKingdomButton.setIcon(Constants.fireKingdomButton[0]);
        fireKingdomButton.setBounds(557,304,166,166);
        iceKingdomButton = new JButton();
        iceKingdomButton.setOpaque(false);
        iceKingdomButton.setBorderPainted(false);
        iceKingdomButton.setContentAreaFilled(false);
        iceKingdomButton.setIcon(Constants.iceKingdomButton[0]);
        iceKingdomButton.setBounds(1005,304,166,166);
        
        tool = new TheMap<>();
        tool.addVertex("Tree House");
        tool.addVertex("Ice Kingdom");
        tool.addVertex("Candy Kingdom");
        tool.addVertex("Fire Kingdom");

        tool.addEdge("Tree House", "Ice Kingdom", 200, 60);
        tool.addEdge("Tree House", "Candy Kingdom", 700, 120);
        tool.addEdge("Tree House", "Fire Kingdom", 500, 80);
        tool.addEdge("Ice Kingdom", "Tree House", 200, 60);
        tool.addEdge("Candy Kingdom", "Tree House", 700, 120);
        tool.addEdge("Fire Kingdom", "Tree House", 500, 80);
        tool.addEdge("Ice Kingdom", "Candy Kingdom", 400, 70);
        tool.addEdge("Candy Kingdom", "Fire Kingdom", 800, 140);
        
    }
    
    
    
    public void checkAndTeleport(String source, String destination) throws NoSuchAlgorithmException {
    if (tool.hasVertex(source) && tool.hasVertex(destination)) {
        if (tool.hasEdge(source, destination)) {
            gamePage.remove(gamePage.mainChest);
            gamePage.remove(gamePage.sortingSystem.healthPotionChest);
            gamePage.remove(gamePage.sortingSystem.regenerationPotionChest);
            gamePage.remove(gamePage.sortingSystem.leapingPotionChest);
            gamePage.remove(gamePage.sortingSystem.swiftnessPotionChest);
            for(int i=0 ; i<5 ; i++){
                gamePage.remove(gamePage.cropField[i]);
            }
            //showKingdomInfo(destination);
            gamePage.currentLocation = destination;
            if(gamePage.currentLocation == Constants.treeHouse){
                gamePage.add(gamePage.mainChest);
                gamePage.add(gamePage.sortingSystem.healthPotionChest);
                gamePage.add(gamePage.sortingSystem.regenerationPotionChest);
                gamePage.add(gamePage.sortingSystem.leapingPotionChest);
                gamePage.add(gamePage.sortingSystem.swiftnessPotionChest);
                for(int i=0 ; i<5 ; i++){
                gamePage.add(gamePage.cropField[i]);
            }
            }
            if(gamePage.currentLocation.equals(Constants.treeHouse)){
                gamePage.backgroundImage = Constants.TreeHouseBackground;
                gamePage.diary.addEntry("You're back at the Tree House!!!");
            }
            else if(gamePage.currentLocation.equals(Constants.iceKingdom)){
                gamePage.backgroundImage = Constants.IceKingdomBackground;
                gamePage.diary.addEntry("You Visited Ice Kingdom");
            }
            else if(gamePage.currentLocation.equals(Constants.fireKingdom)){
                gamePage.diary.addEntry("You Visited Fire Kingdom");
                gamePage.backgroundImage = Constants.FireKingdomBackground;
            }
            else if(gamePage.currentLocation.equals(Constants.candyKingdom)){
                gamePage.backgroundImage = Constants.CandyKingdomBackground;
                gamePage.diary.addEntry("You Visited Candy Kingdom");
            }
            minimize();
            expand();
            
        } else {
            System.out.println("No path exists between " + source + " and " + destination);
        }
    }
}

    private void showKingdomInfo(String kingdom) {
        String info = getKingdomInfo(kingdom);
        if (!info.isEmpty()) {
            JOptionPane.showMessageDialog(this, info, kingdom, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String getKingdomInfo(String kingdom) {
        // Provide information for each kingdom
        switch (kingdom) {
            case "Tree House":
                return "Welcome to the Tree House! This is a peaceful place surrounded by nature.";
            case "Ice Kingdom":
                return "Welcome to the Ice Kingdom! Be prepared for cold weather and icy landscapes.";
            case "Candy Kingdom":
                return "Welcome to the Candy Kingdom! Everything here is made of sweets and treats.";
            case "Fire Kingdom":
                return "Welcome to the Fire Kingdom! Watch out for hot temperatures and fiery creatures.";
            default:
                return "There is no Kingdom with that name"; // No information available
        }
    }

    public void drawMenu(Graphics g){
        g.drawImage(itemMenu,69,6,null);
    }
   
    public void expand(){
        if(tool.hasEdge(gamePage.currentLocation, Constants.treeHouse))
            treeHouseButton.setEnabled(true);
        else
            treeHouseButton.setEnabled(false);

        if(tool.hasEdge(gamePage.currentLocation, Constants.iceKingdom))
            iceKingdomButton.setEnabled(true);
        else
            iceKingdomButton.setEnabled(false);
        if(tool.hasEdge(gamePage.currentLocation, Constants.fireKingdom))
            fireKingdomButton.setEnabled(true);
        else
            fireKingdomButton.setEnabled(false);
        if(tool.hasEdge(gamePage.currentLocation, Constants.candyKingdom))
            candyKingdomButton.setEnabled(true);
        else
            candyKingdomButton.setEnabled(false);
        treeHouseButton.setIcon(Constants.treeHouseButton[0]);
        gamePage.add(treeHouseButton);
        iceKingdomButton.setIcon(Constants.iceKingdomButton[0]);
        gamePage.add(iceKingdomButton);
        fireKingdomButton.setIcon(Constants.fireKingdomButton[0]);
        gamePage.add(fireKingdomButton);
        candyKingdomButton.setIcon(Constants.candyKingdomButton[0]);
        gamePage.add(candyKingdomButton);
    }
    
    public void minimize(){
        gamePage.remove(treeHouseButton);
        gamePage.remove(iceKingdomButton);
        gamePage.remove(fireKingdomButton);
        gamePage.remove(candyKingdomButton);
    }
 
}