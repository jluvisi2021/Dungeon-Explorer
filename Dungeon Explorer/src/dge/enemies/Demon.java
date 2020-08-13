package dge.enemies;

import dge.GameMain;
import dge.items.ApplePieItem;
import dge.items.EnergyDrinkItem;
import dge.items.HealingPotionItem;
import dge.items.Weapons.DemonSwordItem;
import dge.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Demon extends BaseMonster {

    public Demon() {
        // Add Sword
        super("Demon", 20, 5, new ArrayList<>(Arrays.asList(new EnergyDrinkItem(4), new DemonSwordItem(false))), false);
        setDifficultyValues();
    }

    @Override
    public void setDifficultyValues() {
        if(GameMain.gameDifficulty == GameMain.Difficulty.EASY) {
            this.setHealth(18);
            this.setAttack(4);
        }else if(GameMain.gameDifficulty == GameMain.Difficulty.HARD || GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
            this.setHealth(22);
            this.setAttack(6);
        }
    }
    @Override
    public void attackPlayer(Player p) {
        System.out.println(getName() + " hit you for " + getAttack() + "!");
        p.setHealth(p.getHealth()-this.getAttack());
    }
}
