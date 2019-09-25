package org.improving.tag;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

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
}
