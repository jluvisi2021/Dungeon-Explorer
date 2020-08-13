package dge.items.skills;

import dge.enemies.BaseMonster;
import dge.player.Player;

public abstract class BaseSkill {
    private String name;

    public BaseSkill(String name) {
        this.name = name;
    }

    public abstract void skillAction(Player p, BaseMonster monster);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
