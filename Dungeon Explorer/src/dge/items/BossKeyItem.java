package dge.items;

import dge.player.Player;

public class BossKeyItem extends BaseItem {

    public BossKeyItem() {
        super("Boss Key", "An item that can be used to unlock the boss room.");
    }

    @Override
    public void performAction(Player p) {
        System.out.println("This is a key used to unlock the boss room.\nFind the boss room!");
    }
}
