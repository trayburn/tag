package org.improving.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Exit {
    private String name;
    private Location destination;
    private int destinationId;

    private List<String> aliases = new ArrayList<>();

    public Exit() { }

    public Exit(String name, Location destination, String...aliases) {
        this.name = name;
        this.destination = destination;
        this.aliases.addAll(Arrays.asList(aliases));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, destination);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Exit) {
            Exit exit = (Exit) obj;
            return this.getName().equals(exit.getName()) &&
                    this.getDestination().equals(exit.getDestination());
        }
        return super.equals(obj);
    }
}
