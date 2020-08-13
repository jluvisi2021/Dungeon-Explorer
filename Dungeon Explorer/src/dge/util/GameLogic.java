package dge.util;

import dge.GameMain;
import dge.enemies.BaseMonster;

import java.util.List;
import java.util.Random;

public class GameLogic {
    static Random rand;
    public static boolean isCriticalHit() {
        rand = new Random();
        // 2 in 16 chance for critical hit (EASY)
        if(GameMain.gameDifficulty == GameMain.Difficulty.EASY) {
            return rand.nextInt(16) > 13;
        }
        // 1 in 16 chance for critical hit
        return rand.nextInt(16) == 5;
    }
    public static boolean isMissedAttack() {
        rand = new Random();
        if(GameMain.gameDifficulty == GameMain.Difficulty.HARD || GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
            return rand.nextInt(16) > 13;
        }
        // 1 in 16 chance for miss
        return rand.nextInt(16) == 9;
    }

    public static BaseMonster chooseRandomMonster(List<BaseMonster> monsterList) {
        rand = new Random();
        int index = rand.nextInt(monsterList.size());
        return monsterList.get(index);
    }

    public static boolean isBlocked() {
        rand = new Random();
        int x = rand.nextInt(10)+1;
        // Block Failed
        return x > 6;
    }

    // 1 in 4 counter attack
    public static boolean isCountered() {
        rand = new Random();
        int x = rand.nextInt(4);
        return x == 3;
    }
}
