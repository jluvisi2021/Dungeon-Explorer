package dge.player;

import dge.items.BaseItem;
import dge.rooms.Room;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public static int DEFAULT_ATTACK = 3;
    private int health;
    private int energy;
    private int attack;
    private List<BaseItem> bagItems;
    private Room currentRoom;
    private boolean isFighting = false;
    private boolean skillUsed = false;
    public Player() {
        this.health = 20;
        this.energy = 5;
        this.attack = DEFAULT_ATTACK;
        this.bagItems = new ArrayList<BaseItem>();
        this.currentRoom = null;
        this.isFighting = false;
    }

    public Player(int health, int energy, int attack, List<BaseItem> bagItems, Room currentRoom, boolean isFighting) {
        this.health = health;
        this.energy = energy;
        this.attack = attack;
        this.bagItems = bagItems;
        this.currentRoom = currentRoom;
        this.isFighting = isFighting;
    }

    public void setSkillUsed(boolean bool) {
        skillUsed = bool;
    }

    public boolean getSkillUsed() {
        return skillUsed;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public List<BaseItem> getBagItems() {
        return bagItems;
    }

    public void setBagItems(List<BaseItem> bagItems) {
        this.bagItems = bagItems;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean bagHasItemByName(String itemName) {
        for(BaseItem item : bagItems) {
            if(item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public BaseItem getItemBuName(String itemName) {
        for(BaseItem item : bagItems) {
            if(item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean isFighting() {
        return isFighting;
    }

    public void setFighting(boolean fighting) {
        isFighting = fighting;
    }
}
