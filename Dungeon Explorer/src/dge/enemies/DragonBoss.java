package dge.enemies;

import dge.GameMain;
import dge.items.EnergyDrinkItem;
import dge.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DragonBoss extends BaseMonster {

    public DragonBoss() {
        // Add Sword
        super("Dragon Boss", 35, 8, new ArrayList<>(), true);
        setDifficultyValues();
    }

    @Override
    public void setDifficultyValues() {
        if(GameMain.gameDifficulty == GameMain.Difficulty.EASY) {
            this.setHealth(32);
            this.setAttack(6);
        }else if(GameMain.gameDifficulty == GameMain.Difficulty.HARD || GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
            this.setHealth(39);
            this.setAttack(8);
        }
    }

    @Override
    public void attackPlayer(Player p) {
        System.out.println(getName() + " hit you for " + getAttack() + "!");
        p.setHealth(p.getHealth()-this.getAttack());
    }
}
