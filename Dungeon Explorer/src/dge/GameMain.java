package dge;

import dge.command.Command;
import dge.player.Player;
import dge.util.ArrayHelper;

import java.io.IOException;
import java.util.Scanner;

public class GameMain {
    public enum Difficulty {
        EASY,MEDIUM,HARD,VETERAN
    }
    public static Difficulty gameDifficulty = Difficulty.MEDIUM;

    public static void main(String[] args) {
        System.out.println("-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-");
        System.out.println("Welcome to Dungeon Explorer!");
        System.out.println("Please enter \"help\" if you want to see the available commands.");
        System.out.println("-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-=-=*=-");

        Scanner scan = new Scanner(System.in);
        Player p = new Player();
        while(true) {
            System.out.print("> ");
            Command command = new Command(ArrayHelper.toList(scan.nextLine().split(" ")), p);
        }

    }



}
