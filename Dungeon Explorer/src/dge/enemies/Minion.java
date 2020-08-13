package dge.enemies;

import dge.GameMain;
import dge.items.ApplePieItem;
import dge.items.EnergyDrinkItem;
import dge.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Minion extends BaseMonster {

    public Minion() {
        super("Minion", 12, 3, new ArrayList<>(Collections.singletonList(new EnergyDrinkItem(3))), false);
        setDifficultyValues();
    }

    @Override
    public void setDifficultyValues() {
        if(GameMain.gameDifficulty == GameMain.Difficulty.EASY) {
            this.setHealth(10);
            this.setAttack(2);
        }else if(GameMain.gameDifficulty == GameMain.Difficulty.HARD || GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
            this.setHealth(15);
            this.setAttack(4);
        }
    }


    @Override
    public void attackPlayer(Player p) {
        System.out.println(getName() + " hit you for " + getAttack() + "!");
        p.setHealth(p.getHealth()-this.getAttack());
    }
}
