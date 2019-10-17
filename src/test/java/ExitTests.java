import org.improving.tag.Exit;
import org.improving.tag.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitTests {

    @Test
    public void toString_should_include_name() {
        // Arrange
        var target = new Exit("Door", null, new Location());

        // Act
        var result = target.toString();

        // Assert
        assertTrue(result.contains("Door"));
    }

    @Test
    public void equals_should_be_true_when_name_and_destination_match() {
        // Arrange
        var destination = new Location();
        var exit1 = new Exit("Door", null, destination);
        var exit2 = new Exit("Door", null, destination, "this", "intentionally", "filled", "in");

        // Act
        var result = exit1.equals(exit2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void equals_should_be_true_when_name_and_destination_match_of_course() {
        // Arrange
        var exit1 = new Exit("Door", null, new Location());
        var exit2 = new Exit("Door", null, new Location(), "this", "intentionally", "filled", "in");

        // Act
        var result = exit1.equals(exit2);

        // Assert
        assertTrue(result);
    }


    @Test
    public void equals_should_be_false_when_compared_to_non_exit() {
        // Arrange
        var destination = new Location();
        var exit1 = new Exit("Door", null, destination);

        // Act
        var result = exit1.equals("Door");

        // Assert
        assertFalse(result);
    }
}
