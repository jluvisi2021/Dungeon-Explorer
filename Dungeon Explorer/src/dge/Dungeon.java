package dge;

import dge.command.Command;
import dge.enemies.*;
import dge.items.ApplePieItem;
import dge.items.BossKeyItem;
import dge.items.EnergyDrinkItem;
import dge.items.HealingPotionItem;
import dge.items.Weapons.EnchantedSwordItem;
import dge.items.Weapons.KatanaItem;
import dge.items.Weapons.SteelSwordItem;
import dge.player.Player;
import dge.rooms.Room;
import dge.util.ArrayHelper;
import dge.util.GameLogic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {
    private static Room r0;

    private static Random rand;

    private static void generateLevel() {
        //TODO: Generate Items.
        // Healing Items.

        //TODO: Generate Monsters.
        //TODO: Generate Rooms
        r0 = new Room("Room Zero");
        Room r1 = new Room("Room One");
        Room r2 = new Room("Room Two");
        Room r3 = new Room("Room Three");
        Room r4 = new Room("Room Four");
        Room r5 = new Room("Room Five");
        Room r6 = new Room("Room Six");
        Room r7 = new Room("Room Seven");
        Room r8 = new Room("Room Eight");
        Room r9 = new Room("Room Nine");
        Room r10 = new Room("Room Ten");
        Room r11 = new Room("Room Eleven");
        Room r12 = new Room("Room Twelve");
        Room r13 = new Room("Room Thirteen");
        Room r14 = new Room("Room Fourteen");
        Room r15= new Room("Room Fifteen");
        Room r16 = new Room("Room Sixteen");

        r11.setBossRoom(true);
        // Setup Locations
        // NORTH, SOUTH, WEST, EAST
        r0.setRoomsBordering(new ArrayList<>(Arrays.asList(r1, null, r3, r2)));
        r1.setRoomsBordering(new ArrayList<>(Arrays.asList(r5, r0, null, null)));
        r2.setRoomsBordering(new ArrayList<>(Arrays.asList(r3, null, r0, null)));
        r3.setRoomsBordering(new ArrayList<>(Arrays.asList(r4, null, null, r0)));
        r4.setRoomsBordering(new ArrayList<>(Arrays.asList(null, r3, null, null)));
        r5.setRoomsBordering(new ArrayList<>(Arrays.asList(r12, r1, r9, r6)));
        r6.setRoomsBordering(new ArrayList<>(Arrays.asList(r13, null, r5, r7)));
        r7.setRoomsBordering(new ArrayList<>(Arrays.asList(null, r8, r6, null)));
        r8.setRoomsBordering(new ArrayList<>(Arrays.asList(r7, null, null, null)));
        r9.setRoomsBordering(new ArrayList<>(Arrays.asList(r10, null, null, r5)));
        r10.setRoomsBordering(new ArrayList<>(Arrays.asList(null, r9, r11, null)));
        r11.setRoomsBordering(new ArrayList<>(Arrays.asList(null, null, null, r10)));
        r12.setRoomsBordering(new ArrayList<>(Arrays.asList(null, r5, null, null)));
        r13.setRoomsBordering(new ArrayList<>(Arrays.asList(null, r6, null, r14)));
        r14.setRoomsBordering(new ArrayList<>(Arrays.asList(r15, null, r13, r16)));
        r15.setRoomsBordering(new ArrayList<>(Arrays.asList(null, r14, null, null)));
        r16.setRoomsBordering(new ArrayList<>(Arrays.asList(null, null, r14, null)));
        // Setup Monsters
        r0.addItem(new EnergyDrinkItem(4));

        r4.addMonster(new Minion());
        r4.addItem(new EnchantedSwordItem(false));

        r5.addItem(new SteelSwordItem(false));

        r2.addItem(new EnergyDrinkItem(3));

        r3.addMonster(new Spider());

        r5.addMonster(new Spider());
        r5.addMonster(new Minion());
        r5.addItem(new HealingPotionItem(5));

        r12.addMonster(new Spider());
        r12.addMonster(new Minion());
        r12.addMonster(new Wolf());

        r9.addMonster(new Minion());
        r9.addItem(new EnergyDrinkItem(5));
        r9.addItem(new HealingPotionItem(4));

        r10.addItem(new ApplePieItem());
        r10.addMonster(new Giant());

        r7.addMonster(new Minion());

        r8.addMonster(new Wolf());
        r8.addItem(new HealingPotionItem(10));
        r8.addItem(new KatanaItem(false));

        r13.addMonster(new Spider());
        r13.addItem(new HealingPotionItem(3));
        r13.addItem(new EnergyDrinkItem(3));

        r14.addItem(new EnchantedSwordItem(false));

        r16.addMonster(new Demon());
        r16.addMonster(new Demon());
        r16.addMonster(new Demon());
        r16.addItem(new ApplePieItem());

        r15.addMonster(new Wolf());
        r15.addMonster(new Minion());
        r15.addMonster(new Giant());
        r15.addItem(new BossKeyItem());

        r11.addMonster(new DragonBoss());

    }

    public static void invokeBattle(Player p, BaseMonster monster) {
        rand = new Random();
        /**
         * Call this method when the player walks into a room
         * and needs to fight a monster.
         * This method should provide the player with loot when
         * the monster is dead instead of handling it in the monster class.
         * @param p
         * @param monster
         */
        // 1 in 5 to skip battle.
        if(rand.nextInt(5) == 3 && !(monster instanceof DragonBoss)) {
            return;
        }
        p.setFighting(true);
        System.out.println("Woah! A " + monster.getName() + " appeared infront of you!");
        if(monster.isBoss()) {
            // Do boss stuff.
            System.out.println("-- FINAL BOSS FIGHT --");
        }else{
            System.out.println("-- BATTLE START --");
        }
        System.out.println();

        while(true) {
            // Perform Fight Loop
            Scanner scan = new Scanner(System.in);
            System.out.print(">> ");
            Command command = new Command(ArrayHelper.toList(scan.nextLine().split(" ")), p, monster);
            if(!command.playerUsedBatttleMove(p)) {
                continue;
            }
            // Execute monster
            playerDead(p, monster);
            if(monster.getHealth() <= 0) {
                System.out.println("Defeated " + monster.getName());
                if(monster.isBoss()) {
                    for(int i = 0; i < 200; i++) {
                        System.out.println();
                    }

                        System.out.println();
                        System.out.println("--> YOU WIN! <--");
                        System.out.println("Time Won: " + LocalDateTime.now());
                        System.out.println("Final Health: " + p.getHealth());
                        System.out.println("Final Energy: " + p.getEnergy());
                        System.out.println("Difficulty Won: " + GameMain.gameDifficulty.toString());
                        System.out.println("Thank you for playing Dungeon Explorer!");
                        if(GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
                            System.out.println("WOW! YOU BEAT THE GAME ON VETERAN DIFFICULTY! GOOD JOB! :D");
                        }

                    System.exit(0);
                }
                // Provide Loot
                if(monster.getLootItems().size() != 0) {
                    System.out.print("You obtained: ");
                    //50% chance to drop an item.
                    rand = new Random();
                    if(rand.nextInt(10) < 5) {
                        monster.getLootItems().forEach((m)->System.out.print(m.getName() + ", "));
                        p.getBagItems().addAll(monster.getLootItems());
                    }else{
                        System.out.print("No items... :(");
                    }
                    System.out.println();
                }
                break;
            }
            if(command.getArgs().get(0).equalsIgnoreCase("block")) {
                if(GameLogic.isBlocked()) {
                    System.out.println("You blocked the attack!");
                    if(GameLogic.isCountered()) {
                        System.out.println("You counter-attacked!");
                        if(GameLogic.isMissedAttack()) {
                            System.out.println("Your counter attack missed!");
                            System.out.println();
                            return;
                        }
                        monster.inflictDamage(p.getAttack());
                    }
                    continue;
                }
                System.out.println("Your attempt to block failed!");
            }
            monster.attackPlayer(p);
            playerDead(p, monster);
            System.out.println();
        }
        p.setFighting(false);
        p.setSkillUsed(false);
        p.getCurrentRoom().removeMonster(monster);
    }

    private static void playerDead(Player p, BaseMonster monster) {
        if(p.getHealth() <= 0) {
            for(int i = 0; i < 50; i++) {
                System.out.println();
            }
            System.out.println("You died!");
            System.out.println("Died to " + monster.getName());
            System.exit(0);
        }
    }

    public static void setupPlayer(Player p) {
        generateLevel();
        // Set players room.
        p.setCurrentRoom(r0);
        System.out.println("--- You entered the Dungeon ---");
        System.out.println("Current Room: " + p.getCurrentRoom().getName());
        if(GameMain.gameDifficulty == GameMain.Difficulty.EASY) {
            p.setEnergy((int) (p.getEnergy()*1.5));
        }

    }
}
