package dge.items.Weapons;

import dge.items.skills.RepetitionSkill;
import dge.items.skills.SweepingEdgeSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KatanaItem extends Weapon {

    public KatanaItem(boolean isEquipped) {
        super("Katana", 3, new ArrayList<>(Arrays.asList(new RepetitionSkill(), new SweepingEdgeSkill())), isEquipped);
    }
}
