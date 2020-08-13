package dge.enemies;

import dge.GameMain;
import dge.items.ApplePieItem;
import dge.items.EnergyDrinkItem;
import dge.items.HealingPotionItem;
import dge.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Giant extends BaseMonster {

    public Giant() {
        super("Giant", 28, 4, new ArrayList<>(Arrays.asList(new ApplePieItem(), new EnergyDrinkItem(8), new HealingPotionItem(3))), false);
        setDifficultyValues();
    }

    @Override
    public void setDifficultyValues() {
        if(GameMain.gameDifficulty == GameMain.Difficulty.EASY) {
            this.setHealth(25);
            this.setAttack(3);
        }else if(GameMain.gameDifficulty == GameMain.Difficulty.HARD || GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
            this.setHealth(30);
            this.setAttack(5);
        }
    }

    @Override
    public void attackPlayer(Player p) {
        System.out.println(getName() + " hit you for " + getAttack() + "!");
        p.setHealth(p.getHealth()-this.getAttack());
    }
}
