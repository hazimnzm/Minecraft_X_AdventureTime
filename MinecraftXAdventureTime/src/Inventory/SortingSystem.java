package Inventory;

import Item.*;
import Pages.GamePage;

public class SortingSystem {
    public FilteredChest healthPotionChest;
    public FilteredChest swiftnessPotionChest;
    public FilteredChest regenerationPotionChest;
    public FilteredChest leapingPotionChest;
    public BST<FilteredChest> tree = new BST();
    GamePage gamePage;
    
    public SortingSystem(GamePage gamePage){
        this.gamePage = gamePage;
        healthPotionChest = new FilteredChest(gamePage, "Potion of Healing");
        swiftnessPotionChest = new FilteredChest(gamePage, "Potion of Swiftness");
        regenerationPotionChest = new FilteredChest(gamePage, "Potion of Regeneration");
        leapingPotionChest = new FilteredChest(gamePage, "Potion of Leaping");
        healthPotionChest.setBounds(200,513,22,40);
        swiftnessPotionChest.setBounds(225,513,22,40);
        regenerationPotionChest.setBounds(200,483,22,40);
        leapingPotionChest.setBounds(225,483,22,40);
        tree.insert(healthPotionChest);
        tree.insert(leapingPotionChest);
        tree.insert(regenerationPotionChest);
        tree.insert(swiftnessPotionChest);
    }
    
    public boolean insert(Items item){
        if(item.name.equals("Potion of Healing")){
            return tree.search(healthPotionChest).addItem(item);
        }
        else if(item.name.equals("Potion of Regeneration")){
            return tree.search(regenerationPotionChest).addItem(item);
        }
        else if(item.name.equals("Potion of Swiftness")){
            return tree.search(swiftnessPotionChest).addItem(item);
        }
        else if(item.name.equals("Potion of Leaping")){
            return tree.search(leapingPotionChest).addItem(item);
        }
        return false;
    }
}
