import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.improving.tag.Game;
import org.improving.tag.Player;
import org.improving.tag.commands.SetCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        game = mock(Game.class);
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
        Player player = new Player(null);
        player.setName("hi");
        player.setHitPoints(50);
        player = spy(player);

        when(game.getPlayer()).thenReturn(player);

        // Act
        target.execute("@set name=Fluefedor", game);

        // Assert
        verify(player).setName("Fluefedor");
    }
}
