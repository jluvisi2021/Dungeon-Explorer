package dge.rooms;

import dge.Dungeon;
import dge.enemies.BaseMonster;
import dge.items.BaseItem;
import dge.items.BossKeyItem;
import dge.player.Player;
import dge.util.GameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Room {
    public enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }
    /*
    Rooms bordering tells the rooms to the NORTH, SOUTH, WEST, EAST respectively.
     */

    private String name;
    private List<Room> roomsBordering;
    private List<BaseItem> items;
    private List<BaseMonster> monsters;
    private boolean bossRoom = false;

    /**
     * Create the default constructor for the room to fill in with data later.
     *
     * @param name
     */
    public Room(String name) {
        this.name = name;
        this.roomsBordering = new ArrayList<>(Arrays.asList(null, null, null, null));
        this.items = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.bossRoom = false;
    }

    public Room(String name, List<Room> roomsBordering, List<BaseItem> items, List<BaseMonster> monsters) {
        this.name = name;
        this.roomsBordering = roomsBordering;
        this.items = items;
        this.monsters = monsters;
    }

    public Room(String name, List<Room> roomsBordering, List<BaseItem> items, List<BaseMonster> monsters, boolean bossRoom) {
        this.name = name;
        this.roomsBordering = roomsBordering;
        this.items = items;
        this.monsters = monsters;
        this.bossRoom = bossRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRoomsBordering() {
        return roomsBordering;
    }

    public Room getNorth() {
        return this.roomsBordering.get(0);
    }

    public Room getSouth() {
        return this.roomsBordering.get(1);
    }

    public Room getWest() {
        return this.roomsBordering.get(2);
    }

    public Room getEast() {
        return this.roomsBordering.get(3);
    }

    private static void checkIfPlayerHasNoEnergy(Player p) {
        p.setEnergy(p.getEnergy()-1);
        if(p.getEnergy() == 0) {
            System.out.println();
            System.out.println("You died!\nReason: Ran out of energy.");
            System.exit(0);
        }
    }
    /**
     * Attempts to move the player a specific direction.
     * @param p
     * @param direction
     */
    public static void movePlayer(Player p, Direction direction) {
        Room currentRoom =p.getCurrentRoom();
        switch (direction) {
            case NORTH -> {
                if (currentRoom.getNorth() != null) {
                    if(p.getCurrentRoom().getNorth().isBossRoom()) {
                        if (bossRoomEnterCheck(p)) return;
                    }
                    p.setCurrentRoom(p.getCurrentRoom().getNorth());
                    checkIfPlayerHasNoEnergy(p);
                    System.out.println("Entered room " + p.getCurrentRoom().getName());
                    if (p.getCurrentRoom().monsters.size() >= 1) {
                        // Invoke Battle
                        Dungeon.invokeBattle(p, GameLogic.chooseRandomMonster(p.getCurrentRoom().getMonsters()));
                    }
                    return;
                }
                System.out.println("There is no room to the North!");
            }
            case SOUTH -> {
                if (currentRoom.getSouth() != null) {
                    if(p.getCurrentRoom().getSouth().isBossRoom()) {
                        if (bossRoomEnterCheck(p)) return;
                    }
                    p.setCurrentRoom(p.getCurrentRoom().getSouth());
                    checkIfPlayerHasNoEnergy(p);
                    System.out.println("Entered room " + p.getCurrentRoom().getName());
                    if (p.getCurrentRoom().monsters.size() >= 1) {
                        // Invoke Battle
                        Dungeon.invokeBattle(p, GameLogic.chooseRandomMonster(p.getCurrentRoom().getMonsters()));
                    }
                    return;
                }
                System.out.println("There is no room to the South!");
            }
            case WEST -> {
                if (currentRoom.getWest() != null) {
                    if(p.getCurrentRoom().getWest().isBossRoom()) {
                        if (bossRoomEnterCheck(p)) return;
                    }
                    p.setCurrentRoom(p.getCurrentRoom().getWest());
                    checkIfPlayerHasNoEnergy(p);
                    System.out.println("Entered room " + p.getCurrentRoom().getName());
                    if (p.getCurrentRoom().monsters.size() >= 1) {
                        // Invoke Battle

                        Dungeon.invokeBattle(p, GameLogic.chooseRandomMonster(p.getCurrentRoom().getMonsters()));
                    }
                    return;
                }
                System.out.println("There is no room to the West!");
            }
            case EAST -> {
                if (currentRoom.getEast() != null) {
                    if(p.getCurrentRoom().getEast().isBossRoom()) {
                        if (bossRoomEnterCheck(p)) return;
                    }
                    p.setCurrentRoom(p.getCurrentRoom().getEast());
                    checkIfPlayerHasNoEnergy(p);
                    System.out.println("Entered room " + p.getCurrentRoom().getName());
                    if (p.getCurrentRoom().monsters.size() >= 1) {
                        // Invoke Battle
                        Dungeon.invokeBattle(p, GameLogic.chooseRandomMonster(p.getCurrentRoom().getMonsters()));
                    }
                    return;
                }
                System.out.println("There is no room to the East!");
            }
        }
    }

    /**
     * Returns if the player has the key to enter the boss room and
     * if they want to enter.
     * @param p
     * @return
     */
    private static boolean bossRoomEnterCheck(Player p) {
        System.out.println("This is the boss room!");
        boolean hasKey = false;
        for(BaseItem item : p.getBagItems()) {
            if (item instanceof BossKeyItem) {
                hasKey = true;
                break;
            }
        }
        if(hasKey) {
            System.out.println();
            System.out.println("Are you sure you want to enter? (Enter Y to enter)");
            System.out.println();
            Scanner temp = new Scanner(System.in);
            if(!temp.nextLine().equalsIgnoreCase("Y")) {
                System.out.println("You choose not to enter.");
                return true;
            }
        }else{
            System.out.println("You need to find the boss key to enter.");
            return true;
        }
        return false;
    }

    public void setNorth(Room a) {
        try {
            this.roomsBordering.set(0, a);
        } catch (IndexOutOfBoundsException e) {
            this.roomsBordering.add(0, a);
        }
    }

    public void setSouth(Room a) {
        try {
            this.roomsBordering.set(1, a);
        } catch (IndexOutOfBoundsException e) {
            this.roomsBordering.add(1, a);
        }
    }

    public void setWest(Room a) {
        try {
            this.roomsBordering.set(2, a);
        } catch (IndexOutOfBoundsException e) {
            this.roomsBordering.add(2, a);
        }
    }

    public void setEast(Room a) {
        try {
            this.roomsBordering.set(3, a);
        } catch (IndexOutOfBoundsException e) {
            this.roomsBordering.add(3, a);
        }
    }

    public void addItem(BaseItem item) {
        this.items.add(item);
    }

    public void addMonster(BaseMonster monster) {
        this.monsters.add(monster);
    }

    public void removeItem(BaseItem item) {
        int index = items.indexOf(item);
        if(index == -1) {
            System.out.println("Error removing item. Item does not exist in room.");
            return;
        }
        items.remove(index);
    }

    public void removeMonster(BaseMonster monster) {
        int index = monsters.indexOf(monster);
        if(index == -1) {
            System.out.println("Error removing monster. Monster does not exist in room.");
            return;
        }
        monsters.remove(index);
    }

    public void setRoomsBordering(List<Room> roomsBordering) {
        this.roomsBordering = roomsBordering;
    }

    public List<BaseItem> getItems() {
        return items;
    }

    public String getItemsAsString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.items.size(); i++) {
            if (i == this.items.size() - 1) {
                builder.append(items.get(i).getName());
                continue;
            }
            builder.append(items.get(i).getName()).append(", ");
        }
        return builder.toString();
    }

    public void setItems(List<BaseItem> items) {
        this.items = items;
    }

    public List<BaseMonster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<BaseMonster> monsters) {
        this.monsters = monsters;
    }

    public boolean isBossRoom() {
        return bossRoom;
    }

    public void setBossRoom(boolean bossRoom) {
        this.bossRoom = bossRoom;
    }

}

