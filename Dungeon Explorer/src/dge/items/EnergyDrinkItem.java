package dge.items;

import dge.GameMain;
import dge.player.Player;

public class EnergyDrinkItem extends BaseItem {

    private final int energyRestoreAmount;
    public EnergyDrinkItem(int energyRestoreAmount) {
        super("Energy Drink", "An item to be used outside of battle.\nIncreases the user's energy by " + energyRestoreAmount + ".");
        if(GameMain.gameDifficulty == GameMain.Difficulty.VETERAN) {
            this.energyRestoreAmount = (int) (energyRestoreAmount / 1.5);
            this.setDescription("An item to be used outside of battle.\nIncreases the user's energy by " + energyRestoreAmount + ".");
        }else{
            this.energyRestoreAmount = energyRestoreAmount;
        }
    }

    /**
     * Restore the energy.
     * @param p
     */
    @Override
    public void performAction(Player p) {
        p.setEnergy(p.getEnergy() + energyRestoreAmount);
        System.out.println();
        System.out.println("Energy restored by " + energyRestoreAmount);
        System.out.println("New Energy: " + p.getEnergy());
        System.out.println();
    }
}
