package org.improving.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SaveGameFactory {
    private final FileSystemAdapter fsa;
    private final InputOutput io;

    public SaveGameFactory(FileSystemAdapter fsa, InputOutput io) {
        this.fsa = fsa;
        this.io = io;
    }

    public String save(Game game) {
        var locationName = game.getPlayer().getLocation().getName();
        Map<String, String> saveContents = new HashMap<>();
        saveContents.put("location", locationName);

        String path = null;
        try {
            path = fsa.saveToFile(saveContents);
            io.displayText("Saved : " + path);
        } catch (IOException ex) {
            io.displayText(ex.toString());
        }

        return path;
    }

    public void load(final String path, final Game game) {
        Map<String, String> saveContents;
        try {
            saveContents = fsa.loadFile(path);
        } catch (IOException ex) {
            io.displayText(ex.toString());
            io.displayText("Failed to load File");
            return;
        }

        Location lastKnownLocation = game.getLocationOf(saveContents.get("location"));
        game.getPlayer().setLocation(lastKnownLocation);
    }
}
