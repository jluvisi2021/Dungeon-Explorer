package dge.items.Weapons;

import dge.items.skills.LifeStealSkill;
import dge.items.skills.SweepingEdgeSkill;

import java.util.ArrayList;
import java.util.Collections;

public class DemonSwordItem extends Weapon {

    public DemonSwordItem(boolean isEquipped) {
        super("Demon Sword", 12, new ArrayList<>(Collections.singletonList(new LifeStealSkill())), isEquipped);
    }
}
