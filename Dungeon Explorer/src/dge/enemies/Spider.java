package dge.enemies;

import dge.GameMain;
import dge.items.EnergyDrinkItem;
import dge.player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Spider extends BaseMonster {

    public Spider() {
        super("Spider", 8, 6, new ArrayList<>(Collections.singletonList(new EnergyDrinkItem(4))), false);
        setDifficultyValues();
    }

    @Override
    public void setDifficultyValues() {
        if(GameMain.gameDifficulty == GameMain.Difficulty.EASY) {
            this.setHealth(6);
            this.setAttack(5);
        }else if(GameMain.gameDifficulty == GameMain.Difficulty.HARD || GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
            this.setHealth(10);
            this.setAttack(7);
        }
    }

    @Override
    public void attackPlayer(Player p) {
        System.out.println(getName() + " hit you for " + getAttack() + "!");
        p.setHealth(p.getHealth()-this.getAttack());
    }
}
