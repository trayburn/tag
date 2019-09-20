import org.improving.tag.commands.MoveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MoveCommandTests {

    MoveCommand target;
    TestInputOutput io;

    @BeforeEach
    public void arrange() {
        // Arrange
        io = new TestInputOutput();
        target = new MoveCommand(io);
    }

    @Test
    public void execute_should_display_all_words_but_move() {
        // Act
        target.execute("move to the moon");

        // Assert
        assertEquals("You proceed to the moon.", io.lastText);
    }

    @Test
    public void execute_should_display_all_words_but_move_with_spaces() {
        // Act
        target.execute("     move to the moon     ");

        // Assert
        assertEquals("You proceed to the moon.", io.lastText);
    }


    @Test
    public void isValid_should_be_true_when_input_is_move() {
        // Act
        var result = target.isValid("move to the moon");

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_true_when_input_is_move_with_spaces() {
        // Act
        var result = target.isValid("      move to the moon     ");

        // Assert
        assertTrue(result);
    }

    @Test
    public void isValid_should_be_true_when_input_is_move_with_caps() {
        // Act
        var result = target.isValid("mOvE tO the MOON");

        // Assert
        assertTrue(result);
    }


    @Test
    public void isValid_should_be_false_when_input_is_foobar() {
        // Act
        var result = target.isValid("foobar");

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_null() {
        // Act
        var result = target.isValid(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void isValid_should_be_false_when_input_is_only_one_word() {
        // Act
        var result = target.isValid("move");

        // Assert
        assertFalse(result);
    }


}
