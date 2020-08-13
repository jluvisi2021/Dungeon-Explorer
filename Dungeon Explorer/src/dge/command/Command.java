package dge.command;

import dge.Dungeon;
import dge.GameMain;
import dge.enemies.BaseMonster;
import dge.items.BaseItem;
import dge.items.BossKeyItem;
import dge.items.Weapons.Weapon;
import dge.items.skills.BaseSkill;
import dge.player.Player;
import dge.rooms.Room;
import dge.util.GameLogic;

import java.util.*;

public class Command {
    private final List<String> args;

    public Command(List<String> args, Player p) {
        this.args = args;
        performAction(p, null);
    }
    public Command(List<String> args, Player p, BaseMonster monster) {
        this.args = args;
        performAction(p, monster);
    }

    private void performAction(Player p, BaseMonster monster) {
        Room currentRoom = p.getCurrentRoom();
        if(args.get(0).equalsIgnoreCase("help") || args.get(0).equalsIgnoreCase("?")) {

            System.out.println();
            helpMessage();
            System.out.println();
            return;

        }else if(args.get(0).equalsIgnoreCase("exit") || args.get(0).equalsIgnoreCase("quit")) {

            System.out.println();
            System.out.println("-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-");
            System.out.println("Thank you for playing Dungeon Master.");
            System.out.println("-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-");
            System.exit(0);

        }else if(args.get(0).equalsIgnoreCase("play") || args.get(0).equalsIgnoreCase("start")) {

            if(currentRoom != null) {
                wrongExecutionMessage();
                return;
            }
            // Clear console
            for (int i = 0; i < 50; ++i) System.out.println();
            Dungeon.setupPlayer(p);
            //TODO: Begin play sequence.
            return;
        }else if(args.get(0).equalsIgnoreCase("info")) {
            // Display info.
            if(currentRoom != null) {
                System.out.println();
                System.out.println("You cannot use this command right now!");
                System.out.println();
                return;
            }
            infoMessage();
            return;
        } else if (args.get(0).equalsIgnoreCase("difficulty")) {
            if(currentRoom != null) {
                System.out.println();
                System.out.println("You cannot use this command right now!");
                System.out.println();
                return;
            }
            System.out.println();
            System.out.println("Input a number to select a difficulty.");
            System.out.println();
            System.out.println("(1) EASY -> Mob stats reduced, higher critical hit chance, more starting energy.");
            System.out.println("(2) MEDIUM -> Default Difficulty");
            System.out.println("(3) HARD -> Miss rate increased, Mob Stats buffed.");
            System.out.println("(4) VETERAN -> HARD + Energy Potion Effects reduced, No Skills allowed.");
            System.out.println();
            Scanner s = new Scanner(System.in);
            while(true) {
                try {
                    System.out.println("Current Difficulty: " + GameMain.gameDifficulty.toString());
                    System.out.print("New Difficulty: ");
                    int diff = Integer.parseInt(s.nextLine());
                    //Easy
                    switch (diff) {
                        case 1 -> {
                            GameMain.gameDifficulty = GameMain.Difficulty.EASY;
                            System.out.println("Game Difficulty: Set to EASY.");
                            System.out.println();
                            return;
                        }
                        case 2 -> {
                            GameMain.gameDifficulty = GameMain.Difficulty.MEDIUM;
                            System.out.println("Game Difficulty: Set to MEDIUM.");
                            System.out.println();
                            return;
                        }
                        case 3 -> {
                            GameMain.gameDifficulty = GameMain.Difficulty.HARD;
                            System.out.println("Game Difficulty: Set to HARD.");
                            System.out.println();
                            return;
                        }
                        case 4 -> {
                            GameMain.gameDifficulty = GameMain.Difficulty.VETERAN;
                            System.out.println("Game Difficulty: Set to VETERAN.");
                            System.out.println();
                            return;
                        }
                        default -> {
                            System.out.println("Unrecognized difficulty.\nPlease try again!");
                            System.out.println();
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Unrecognized difficulty.\nPlease try again!");
                    System.out.println();
                }
            }
        } else if (args.get(0).equalsIgnoreCase("stats")) {
            if (currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            System.out.println("-----------------");
            System.out.println("Player Stats:");
            System.out.println("Health: " + p.getHealth());
            System.out.println("Attack Power: " + p.getAttack());
            System.out.println("Energy: " + p.getEnergy());
            System.out.println("# of Bag Items: " + p.getBagItems().size());
            System.out.println("-----------------");
            return;
        } else if (args.get(0).equalsIgnoreCase("bag")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            System.out.println("---------------------------");
            System.out.println("# of Bag Items: " + p.getBagItems().size());
            for (BaseItem item : p.getBagItems()) {
                System.out.print(item.getName() + ", ");
            }
            System.out.println();
            System.out.println("---------------------------");
            return;
        } else if (args.get(0).equalsIgnoreCase("use")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            if (args.size() < 2) {
                System.out.println("Incorrect Command.");
                return;
            }
            if (args.get(1).equalsIgnoreCase("item")) {
                StringBuilder x = new StringBuilder();
                convertExtraArgumentsToItemName(x);
                if (p.bagHasItemByName(x.toString())) {
                    BaseItem item = p.getItemBuName(x.toString());
                    item.performAction(p); // Make the item perform its specific action.
                    if (!((item instanceof Weapon) || (item instanceof BossKeyItem))) {
                        p.getBagItems().remove(item);
                    }
                } else {
                    System.out.println();
                    System.out.println("Could not find item " + args.get(2) + " in bag.");
                    System.out.println();
                }
                return;
            }

            //TODO: Work on below methods.
        } else if (args.get(0).equalsIgnoreCase("item")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            if (args.size() < 2) {
                System.out.println("Incorrect Command.");
                return;
            }

            if (args.get(1).equalsIgnoreCase("info")) {
                var x = new StringBuilder();
                convertExtraArgumentsToItemName(x);
                if (p.bagHasItemByName(x.toString())) {
                    BaseItem item = p.getItemBuName(x.toString());
                    System.out.println();
                    System.out.println(item.getName() + " info: ");
                    System.out.println(item.getDescription());
                } else {
                    System.out.println();
                    System.out.println("Could not find item " + x.toString() + " in bag.");
                }
                System.out.println();
            }
            return;
        } else if (args.get(0).equalsIgnoreCase("room")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            if (args.size() < 2) {
                System.out.println("Incorrect Command!");
                return;
            }
            if (args.get(1).equalsIgnoreCase("info")) {
                System.out.println("------------------");
                System.out.println("Current Room: " + p.getCurrentRoom().getName());
                System.out.println("Is Boss Room: " + p.getCurrentRoom().isBossRoom());
                System.out.println("Items Available: " + p.getCurrentRoom().getItemsAsString());
                System.out.println("------------------");
            }
            return;
        } else if (args.get(0).equalsIgnoreCase("up") || args.get(0).equalsIgnoreCase("north") || args.get(0).equalsIgnoreCase("^") || args.get(0).equalsIgnoreCase("w")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            Room.movePlayer(p, Room.Direction.NORTH);
            return;
        } else if (args.get(0).equalsIgnoreCase("down") || args.get(0).equalsIgnoreCase("south") || args.get(0).equalsIgnoreCase("v") || args.get(0).equalsIgnoreCase("s")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            Room.movePlayer(p, Room.Direction.SOUTH);
            return;
        } else if (args.get(0).equalsIgnoreCase("left") || args.get(0).equalsIgnoreCase("west") || args.get(0).equalsIgnoreCase("<") || args.get(0).equalsIgnoreCase("a")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            Room.movePlayer(p, Room.Direction.WEST);
            return;
        } else if (args.get(0).equalsIgnoreCase("right") || args.get(0).equalsIgnoreCase("east") || args.get(0).equalsIgnoreCase(">") || args.get(0).equalsIgnoreCase("d")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            Room.movePlayer(p, Room.Direction.EAST);
            return;
        } else if (args.get(0).equalsIgnoreCase("pickup")) {
            if (p.isFighting() || currentRoom == null) {
                wrongExecutionMessage();
                return;
            }
            StringBuilder item = new StringBuilder();
            for (int i = 1; i < args.size(); i++) {
                if (i == args.size() - 1) {
                    item.append(args.get(i));
                    continue;
                }
                item.append(args.get(i)).append(" ");
            }
            for (BaseItem roomItem : currentRoom.getItems()) {
                if (roomItem.getName().equalsIgnoreCase(item.toString())) {
                    System.out.println();
                    System.out.println("You picked up a " + roomItem.getName());
                    System.out.println("You can check this item in your bag!");
                    System.out.println();
                    p.getBagItems().add(roomItem);
                    currentRoom.getItems().remove(roomItem);
                    return;
                }
            }
            System.out.println("Could not find item " + item + " in room.");
            return;
        } else if (args.get(0).equalsIgnoreCase("attack")) {
            if (!p.isFighting() || currentRoom == null || monster == null) {
                wrongExecutionMessage();
                return;
            }
            if (GameLogic.isMissedAttack()) {
                System.out.println("Your attack missed!");
                System.out.println();
                return;
            }
            monster.inflictDamage(p.getAttack());
            return;
        } else if (args.get(0).equalsIgnoreCase("block")) {
            if (!p.isFighting() || currentRoom == null || monster == null) {
                wrongExecutionMessage();
                return;
            }

            return;
        } else if (args.get(0).equalsIgnoreCase("skills")) {
            if (!p.isFighting() || currentRoom == null || monster == null) {
                wrongExecutionMessage();
                return;
            }
            Weapon weapon = null;
            for (BaseItem item : p.getBagItems()) {
                if (item instanceof Weapon) {
                    Weapon a = (Weapon) item;
                    if (a.isEquipped()) {
                        weapon = a;
                        break;
                    }
                }
            }
            System.out.println();
            if (weapon == null) {
                System.out.println("Possible Sword Skills: " + "None");
            } else {
                System.out.println("Possible Sword Skills: " + weapon.getSkillList());
            }
            return;
        } else if (args.get(0).equalsIgnoreCase("skill")) {
            if (!p.isFighting() || currentRoom == null || monster == null) {
                wrongExecutionMessage();
                return;
            }
            if(GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
                p.setSkillUsed(true);
                System.out.println("You cannot use skills on VETERAN difficulty!");
                this.args.set(0, "");
                return;
            }
            if (p.getSkillUsed()) {
                System.out.println("You cannot use another skill this fight!");
                this.args.set(0, "");
                return;
            }
            // Get the players weapon.
            ArrayList<Weapon> weaponArrayList = new ArrayList<>();
            Weapon equippedWeapon = null;
            for (BaseItem item : p.getBagItems()) {
                if (item instanceof Weapon) {
                    weaponArrayList.add((Weapon) item);
                }
            }
            for (Weapon w : weaponArrayList) {
                if (w.isEquipped()) {
                    equippedWeapon = w;
                    break;
                }
            }
            if (equippedWeapon == null) {
                System.out.println("You have no skills to use!");
                this.args.set(0, "");
                return;
            }
            if (equippedWeapon.getSkillList().isEmpty()) {
                System.out.println("You have no skills to use!");
                this.args.set(0, "");//Stop monster from attacking
                return;
            }
            System.out.println();
            System.out.println("Available Skills: ");
            for (int i = 0; i < equippedWeapon.getSkillList().size(); i++) {
                System.out.println("(" + (i + 1) + ") " + equippedWeapon.getSkillList().get(i));
            }
            while (true) {
                Scanner s = new Scanner(System.in);
                try {
                    System.out.print(">>> ");
                    int selected = Integer.parseInt(s.nextLine());
                    BaseSkill selectedSkill = equippedWeapon.getSkillList().get(selected - 1);
                    selectedSkill.skillAction(p, monster);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Could not find skill.");
                }
            }
            return;
        } else if (args.get(0).equalsIgnoreCase("enemy")) {
            if (!p.isFighting() || currentRoom == null || monster == null) {
                wrongExecutionMessage();
                return;
            }
            if (args.size() == 1) {
                System.out.println("Incorrect Command!");
                return;
            }
            if (args.get(1).equalsIgnoreCase("stats")) {
                // Modify if monster is a boss.
                System.out.println("-------------");
                System.out.println("Name: " + monster.getName());
                System.out.println("Health: " + monster.getHealth());
                System.out.println("Attack: " + monster.getAttack());
                System.out.print("Possible Drops (50%): ");
                monster.getLootItems().forEach((item) -> System.out.print(item.getName() + ", "));
                System.out.println();
                System.out.println("-------------");
            }
            return;
        }
        unknownCommandMessage();
    }

    private void convertExtraArgumentsToItemName(StringBuilder x) {
        for(int i = 2; i < args.size(); i++) {
            if(i == args.size()-1) {
                x.append(args.get(i));
                continue;
            }
            x.append(args.get(i)).append(" ");
        }
    }
    public List<String> getArgs() {
        return this.args;
    }
    /**
     * Returns if the player used a command that does not effect the enemy.
     * @return if the player used a battling move.
     */
    public boolean playerUsedBatttleMove(Player p) {
        if(args.get(0).equalsIgnoreCase("attack")) {
            return true;
        }
        if(args.get(0).equalsIgnoreCase("block")) {
            return true;
        }
        return args.get(0).equalsIgnoreCase("skill");
    }


    private void wrongExecutionMessage() {
        System.out.println();
        System.out.println("You cannot use this command right now!");
        System.out.println();
    }

    //TODO: Add additional text methods.

    public void infoMessage() {
        System.out.println();
        System.out.println("-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-");
        System.out.println("Dungeon Master is a text-based adventure game made in Java.");
        System.out.println("The goal of the game is to explore through each of the avaliable rooms");
        System.out.println("to find the \"BOSS KEY\" which is used to unlock the final boss of the story.");
        System.out.println("As the player you have no knowledge of what the map first looks like so you");
        System.out.println("must try to enter different rooms through trail and error to find the best path.");
        System.out.println("Some rooms have items that you can obtain and others have monsters you will need to fight.");
        System.out.println("In this game you have three primary stats you need to focus on: Health, Attack, and Energy.");
        System.out.println("Health is the stat determined to check if you are alive after or during a boss fight.");
        System.out.println("Energy is a stat that is decreased each time you enter a different room. If you lose all of your energy you lose.");
        System.out.println("Both health and energy can be resotred from obtaining certain items in rooms or killing monsters.");
        System.out.println("Your attack stat dictates how much damage you will do to opposing monsters. The stat increases based on if your weapon.");
        System.out.println("Different types of weapons with different skills can be found in rooms or obtained from monsters.");
        System.out.println("You can activate one of your weapons skills each battle. They can be used only once per battle.");
        System.out.println("-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-");
        System.out.println();
    }

    /**
     * message to be displayed when an unrecognized command is input.
     */
    public static void unknownCommandMessage() {
        System.out.println();
        System.out.println("Unknown Command!");
        System.out.println("Type \"help\" for a list of commands!");
        System.out.println();
    }

    private void helpMessage() {
        System.out.println();
        System.out.println("-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-");
        System.out.println("--> Main Menu Commands <--");
        System.out.println("help -> View the commands page.");
        System.out.println("exit -> Leave the game.");
        System.out.println("play -> Begin playing the game.");
        System.out.println("info -> View information about the game.");
        System.out.println("difficulty -> Change the difficulty (Default: Medium)");
        System.out.println();
        System.out.println("--> Game Commands <--");
        System.out.println("help -> View the commands page.");
        System.out.println("exit -> Leave the game.");
        System.out.println("up/down/left/right -> Move up, down, left, or right.");
        System.out.println("pickup (item name) -> Pick up an item in a room.");
        System.out.println("stats -> View your current player stats.");
        System.out.println("bag -> View the items in your bag.");
        System.out.println("use item (item name) -> Use an item in your bag.");
        System.out.println("item info (item name) -> View information about an item in your bag.");
        System.out.println("room info -> View info about the current room you are in.");
        System.out.println();
        System.out.println("--> Battle Commands <--");
        System.out.println("attack -> Attempt to attack an enemy.");
        System.out.println("block -> Attempt to block an attack from the enemy.");
        System.out.println("stats -> View your current stats.");
        System.out.println("enemy stats -> View the stats of the current enemy");
        System.out.println("skills -> View the skills you can use.");
        System.out.println("skill (skill number) -> Activate a skill. (Can be only used once per battle).");
        System.out.println("-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-");
        System.out.println();
    }

}
