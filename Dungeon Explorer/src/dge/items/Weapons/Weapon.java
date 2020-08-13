package dge.items.Weapons;

import dge.items.BaseItem;
import dge.items.skills.BaseSkill;
import dge.player.Player;

import java.util.List;

public abstract class Weapon extends BaseItem {

    private int attackIncrease;
    private List<BaseSkill> skillList;
    private boolean isEquipped;
    public Weapon(String name, int attackIncrease, List<BaseSkill> skills, boolean isEquipped) {
        super(name, "An item to be held by a player.\nIncreases the attack by " + attackIncrease + ".\nSword Skills: " + skills.toString());
        this.attackIncrease = attackIncrease;
        this.skillList = skills;
        this.isEquipped = isEquipped;
    }

    public int getAttackIncrease() {
        return attackIncrease;
    }

    public void setAttackIncrease(int attackIncrease) {
        this.attackIncrease = attackIncrease;
    }

    public List<BaseSkill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<BaseSkill> skillList) {
        this.skillList = skillList;
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }

    @Override
    public void performAction(Player p) {
        for(BaseItem baseItem : p.getBagItems()) {
            if(baseItem instanceof Weapon) {
                Weapon w = (Weapon) baseItem;
                if(w.isEquipped()) {
                    if(w==this) {
                        System.out.println("You already have this item equipped!");
                        break;
                    }
                    w.setEquipped(false);
                    System.out.println("You unequipped the " + w.getName());
                    break;
                }
            }
        }
        this.setEquipped(true);
        System.out.println("You equipped the " + getName());
        System.out.println("Your attack is now " + (attackIncrease + Player.DEFAULT_ATTACK));
        p.setAttack(attackIncrease + Player.DEFAULT_ATTACK);
    }

}
