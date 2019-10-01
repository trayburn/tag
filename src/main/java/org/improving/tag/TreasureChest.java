package org.improving.tag;

import org.improving.tag.items.Item;
import org.improving.tag.items.UniqueItems;

public class TreasureChest {
    public static final TreasureChest NO_TREASURE = new TreasureChest(UniqueItems.NOTHING, "");
    private final Item item;
    private final String description;

    public TreasureChest(Item item, String description) {
        this.item = item;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String toString() {
        return description;
    }
}
