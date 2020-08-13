package dge.items.Weapons;

import dge.items.skills.LifeStealSkill;
import dge.items.skills.RepetitionSkill;
import dge.items.skills.SweepingEdgeSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EnchantedSwordItem extends Weapon {

    public EnchantedSwordItem(boolean isEquipped) {
        super("Enchanted Sword", 6, new ArrayList<>(Collections.singletonList(new SweepingEdgeSkill())), isEquipped);
    }
}
