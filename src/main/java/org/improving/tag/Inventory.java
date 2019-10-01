package org.improving.tag;

import org.improving.tag.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items = new ArrayList<>();

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getInventoryDisplay() {
        String displayString = "You have these Items: ";
        for (Item item : items) {
            //displayString = displayString + "\n" + item;
            displayString += "\n" + item;
        }
        return displayString;
    }
}
