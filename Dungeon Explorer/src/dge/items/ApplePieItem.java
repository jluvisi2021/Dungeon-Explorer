package dge.items;

import dge.player.Player;

public class ApplePieItem extends BaseItem {

    public ApplePieItem() {
        super("Apple Pie", "An item to be used outside of battle\nIncreases the user health and energy by 8.");
    }

    @Override
    public void performAction(Player p) {
        p.setEnergy(p.getEnergy()+8);
        p.setHealth(p.getHealth()+8);
        System.out.println();
        System.out.println("Health and energy restored by 8 points.");
        System.out.println("New Health: " + p.getHealth());
        System.out.println("New Energy: " + p.getEnergy());
        System.out.println();
    }
}
