import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Map;

import org.improving.tag.FileSystemAdapter;
import org.improving.tag.Game;
import org.improving.tag.SaveGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class SaveGameFactoryTests {

    private TestInputOutput io;
    private FileSystemAdapter fsa;
    private SaveGameFactory target;
    private Game g;

    @BeforeEach
    public void setup() {
        io = new TestInputOutput();
        fsa = mock(FileSystemAdapter.class);
        target = new SaveGameFactory(fsa, io);
        g = new Game(null, io, target);
    }

    @Test
    public void save_should_preserve_location_name() throws IOException {
        // Arrange
        Class<Map<String, String>> dictClass =
                (Class<Map<String, String>>)(Class)Map.class;
        ArgumentCaptor<Map<String, String>> contentsCaptor =
                ArgumentCaptor.forClass(dictClass);
        when(fsa.saveToFile(any())).thenReturn("this is dumb");


        // Act
        String path = target.save(g);

        // Assert
        verify(fsa).saveToFile(contentsCaptor.capture());
        var loc = contentsCaptor.getValue().get("location");
        assertEquals("The Deathly Hallows", loc);
        assertNotNull(path);
        assertNotEquals("", path);
    }

    @Test
    public void load_should_load_save_file() throws IOException {
        // Arrange
        String path = "thisisafakepath";

        when(fsa.loadFile(path)).thenReturn(Map.of("location", "The Amazon"));

        // Act
        target.load(path, g);

        // Assert
        assertEquals("The Amazon", g.getPlayer().getLocation().getName());
    }

}
