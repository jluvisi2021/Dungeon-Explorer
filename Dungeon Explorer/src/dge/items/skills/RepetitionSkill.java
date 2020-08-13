package dge.items.skills;

import dge.enemies.BaseMonster;
import dge.player.Player;

import java.util.Random;

public class RepetitionSkill extends BaseSkill{

    public RepetitionSkill() {
        super("Repetition Skill");
    }

    private Random rand;
    /*
    Has a chance to attack the monster multiple times. (Up to 4 times)
     */
    @Override
    public void skillAction(Player p, BaseMonster monster) {
        rand = new Random();
        int x = rand.nextInt(4)+1;
        for(int i = 0; i < x; i++) {
            monster.inflictDamage(p.getAttack());
        }
        System.out.println("// Hit " + x + " times //");
        p.setSkillUsed(true);
    }

    @Override
    public String toString() {
        return "Repetition";
    }
}
