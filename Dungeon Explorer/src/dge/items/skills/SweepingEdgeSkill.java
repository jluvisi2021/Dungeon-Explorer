package dge.items.skills;

import dge.enemies.BaseMonster;
import dge.player.Player;

import java.util.Random;

public class SweepingEdgeSkill extends BaseSkill{

    public SweepingEdgeSkill() {
        super("Sweeping Edge");
    }
    private Random rand;
    /*
    Does a random amount of damage to
    attackpower / 2 to attackpower * 2
     */
    @Override
    public void skillAction(Player p, BaseMonster monster) {
        rand = new Random();
        int power = rand.nextInt((int) (p.getAttack() * 1.5)) + p.getAttack() / 2;
        // Inflict damage to monster
        monster.inflictDamage(power);
        p.setSkillUsed(true);
    }

    @Override
    public String toString() {
        return "Sweeping Edge";
    }
}
