package dge.enemies;

import dge.GameMain;
import dge.items.EnergyDrinkItem;
import dge.items.HealingPotionItem;
import dge.player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Wolf extends BaseMonster {

    public Wolf() {
        super("Wolf", 15, 4, new ArrayList<>(Collections.singletonList(new HealingPotionItem(7))), false);
        setDifficultyValues();
    }

    @Override
    public void setDifficultyValues() {
        if(GameMain.gameDifficulty == GameMain.Difficulty.EASY) {
            this.setHealth(13);
            this.setAttack(3);
        }else if(GameMain.gameDifficulty == GameMain.Difficulty.HARD || GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
            this.setHealth(17);
            this.setAttack(5);
        }
    }

    @Override
    public void attackPlayer(Player p) {
        System.out.println(getName() + " hit you for " + getAttack() + "!");
        p.setHealth(p.getHealth()-this.getAttack());
    }
}
