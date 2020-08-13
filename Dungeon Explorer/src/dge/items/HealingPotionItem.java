package dge.items;

import dge.player.Player;

public class HealingPotionItem extends BaseItem {

    private final int healAmount;
    public HealingPotionItem(int healAmount) {
        super("Healing Potion", "An item to be used outside of battle\nIncreases the user health by " + healAmount + " health.");
        this.healAmount = healAmount;
    }

    @Override
    public void performAction(Player p) {
        p.setHealth(p.getHealth() + healAmount);
        System.out.println();
        System.out.println("Healed by " + healAmount);
        System.out.println("New Health: " + p.getHealth());
        System.out.println();
    }
}
