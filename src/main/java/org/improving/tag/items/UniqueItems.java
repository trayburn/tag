package org.improving.tag.items;

public enum UniqueItems implements Item {
    THE_ONE_RING("A golden ring", "The One Ring"),
    BLUE_SHELL("A blue shell with wings", "Blue Shell"),
    EGGO_WAFFLE("Part of a balanced breakfast", "EGGO Waffle"),
    UNFORGETTABLE_MUSHROOM("An edible toad", "Unforgettable Mushroom"),
    EVERLASTING_GOBSTOPPER("A gobstopper that never loses its flavor", "Everlasting Gobstopper"),
    NOTHING("", "");

    private final String description;
    private final String name;

    UniqueItems(String description, String name) {
        this.description = description;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }

    @Override
    public String getName() {
        return name;
    }
}
