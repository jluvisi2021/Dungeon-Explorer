package dge.items.skills;

import dge.enemies.BaseMonster;
import dge.player.Player;

import java.util.Random;

public class LifeStealSkill extends BaseSkill{

    public LifeStealSkill() {
        super("Life Steal");
    }

    private Random rand;

    /*
    Does damage to the monster and heals you for the amount of damage/2 you have done.
     */
    @Override
    public void skillAction(Player p, BaseMonster monster) {
        p.setHealth(p.getHealth()+p.getAttack()/2);
        System.out.println("Regenerated " + p.getAttack()/2 + " health.");
        monster.inflictDamage(p.getAttack());
        p.setSkillUsed(true);
    }

    @Override
    public String toString() {
        return "Life Steal";
    }
}
