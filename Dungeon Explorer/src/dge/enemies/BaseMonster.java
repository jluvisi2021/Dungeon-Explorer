package dge.enemies;

import dge.items.BaseItem;
import dge.player.Player;
import dge.util.GameLogic;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMonster {
    private String name;
    private int health;
    private int attack;
    private boolean isBoss = false;
    private List<BaseItem> lootItems = new ArrayList<>();
    public BaseMonster(String name, int health, int attack, List<BaseItem> lootItems) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.lootItems = lootItems;
    }
    public BaseMonster(String name, int health, int attack, List<BaseItem> lootItems, boolean isBoss) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.lootItems = lootItems;
        this.isBoss = isBoss;
    }

    public abstract void attackPlayer(Player p);
    public abstract void setDifficultyValues();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public List<BaseItem> getLootItems() {
        return lootItems;
    }

    public void setLootItems(List<BaseItem> lootItems) {
        this.lootItems = lootItems;
    }

    /**
     * Returns if the monster is dead.
     * @return
     */
    public boolean isDead() {
        if(this.getHealth() <= 0) {
            // Give loot items when monster dies.
            return true;
        }
        return false;
    }

    /**
     * Use when inflicting damage to a monster.
     * @param amount
     */
    public void inflictDamage(int amount) {
        System.out.println();
        if(GameLogic.isCriticalHit()) {
            amount *= 2;
            System.out.println("-- CRITICAL HIT --");
        }
        System.out.println("Hit " + name + " for " + amount + " damage!");
        this.setHealth(this.getHealth()-amount);
        System.out.println();
    }
}
