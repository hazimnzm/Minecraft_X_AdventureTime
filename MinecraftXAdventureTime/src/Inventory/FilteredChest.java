/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventory;

import Item.Items;
import Pages.GamePage;

/**
 *
 * @author User
 */
public class FilteredChest extends Chest implements Comparable<FilteredChest>{
    public String filteration;
    public FilteredChest(GamePage gamePage, String filteration) {
        super(gamePage);
        this.filteration = filteration;
    }

    @Override
    public int compareTo(FilteredChest o) {
        return filteration.compareToIgnoreCase(o.filteration);
    }

    @Override
    public boolean addItem(Items item){
        if(!isFull() && item.name.equals(filteration)){
            for(int i=0 ; i<9 ; i++){
                if(slot[i].item==null){
                    slot[i].addItem(item);
                    size++;
                    return true;
                }
            }
        }
        return false;
    }
}
