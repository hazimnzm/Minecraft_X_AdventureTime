/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Item;

import Inventory.BackpackButton;
import java.util.ArrayList;

/**
 *
 * @author Syukri
 */
public class MultiTools extends Items{
    private ToolNode head;
    private ToolNode tail;
    public ToolNode current;
    ArrayList<BackpackButton> slot;
    ArrayList<Items> inventory;
    public MultiTools(ArrayList<Items> inventory, ArrayList<BackpackButton> slot) {
        this.head = null;
        this.tail = null;
        this.current = head;
        addTool("Normal Sword",1, 100, 1);
        addTool("Lava Sword",1, 100, 2);
        addTool("Golden Ice Sword",1, 100, 3);
        addTool("Gummy Bear Sword",1, 100, 4);
        this.icon = head.icon;
        this.slot = slot;
        this.inventory = inventory;
        name = "";
    }

    public void addTool(String type, int level, int initialDamage, int index) {
        ToolNode newTool = new ToolNode(type, level, initialDamage, index);
        if (head == null) {
            head = newTool;
            tail = newTool;
            current = newTool;
        } else {
            tail.next = newTool;
            newTool.prev = tail;
            tail = newTool;
        }
    }

    public void upgrade() {
        if (current != null) {
            current.upgrade();
        }
    }

    public void downgrade() {
        if (current != null) {
            current.downgrade();
        }
    }

    public void applyDamage(int amount) {
        if (current != null) {
            current.applyDamage(amount);
        }
    }

    public void repair(int amount) {
        if (current != null) {
            current.repair(amount);
        }
    }
    
    public void switchMode(String type) {
        ToolNode temp = head;
        while (temp != null) {
            if (temp.getType().equals(type)) {
                current = temp;
                icon = current.icon;
                System.out.println(this);
                slot.get(inventory.indexOf(this)).changeIcon(icon);
                return;
            }
            temp = temp.next;
        }
    }

    public ToolNode getCurrentTool() {
        return current;
    }

    public void printTools() {
        ToolNode temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
    
    @Override
    public String toString(){
        return getCurrentTool().toString();
    }
}
