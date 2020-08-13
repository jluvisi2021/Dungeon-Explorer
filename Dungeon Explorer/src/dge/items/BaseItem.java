package dge.items;

import dge.player.Player;

public abstract class BaseItem {
    private String name;
    private String description;

    public BaseItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void performAction(Player p);
}
