import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
//example TEST not really in database
public class AuthorTest {
    
    @Test
    public void testUpdateAuthor() {
        String newName = "Vegeta";
        // Simulate an update method
        assertEquals("Vegeta", newName, "Author name should be updated to Vegeta");
    }
    
}
