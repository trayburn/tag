import org.improving.tag.Game;
import org.improving.tag.commands.DanceCommand;
import org.improving.tag.commands.SetCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetCommandTests {
    SetCommand target;
    TestInputOutput io;
    String goodInput = "@set name=Fluefedor";
    Game game;

    @BeforeEach
    public void arrange() {
        // Arrange
        io = new TestInputOutput();
        target = new SetCommand(io);
        game = new Game(null, io);
    }

    @Test
    public void isValid_should_pass() {
        // Act
        var result = target.isValid(goodInput, game);

        // Assert
        assertTrue(result);
    }

    @Test
    public void execute_should_set_name() {
        // Act
        target.execute(goodInput, game);

        // Assert
        assertEquals("Your name is now Fluefedor.", io.lastText);
    }
}
