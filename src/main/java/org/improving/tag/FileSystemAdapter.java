package org.improving.tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FileSystemAdapter {

    public String saveToFile(Map<String, String> contents) throws IOException {
        var file = File.createTempFile("saveGame", "");

        try (var writer = new FileWriter(file)) {
            for (var pair : contents.entrySet()) {
                writer.append(pair.getKey() + "|" + pair.getValue());
            }
        }

        return file.getAbsolutePath();
    }

    public Map<String, String> loadFile(final String path) throws IOException {
        Map<String, String> properties = new HashMap<>();

        List<String> contents = Files.readAllLines(Path.of(path));

        for (String line : contents) {
            String[] temp = line.split("\\|");
            properties.put(temp[0], temp[1]);
        }

        return properties;
    }
}
