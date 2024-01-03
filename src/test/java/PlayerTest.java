import org.junit.jupiter.api.Test;
import wumpusproject.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testGetName() {
        Player player = new Player("James");
        assertEquals("James", player.getName());
    }

    @Test
    public void testEquals() {
        Player player1 = new Player("James");
        Player player2 = new Player("William");
        Player player3 = new Player("Elena");

        assertFalse(player1.equals(player2));
        assertFalse(player1.equals(player3));
    }

    @Test
    public void testHashCode() {
        Player player1 = new Player("James");
        Player player2 = new Player("William");
        Player player3 = new Player("Elena");

        assertNotEquals(player1.hashCode(), player2.hashCode());
        assertNotEquals(player1.hashCode(), player3.hashCode());
    }
}
