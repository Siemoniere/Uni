import static org.junit.Assert.*;
import org.junit.Test;
import wypozyczanie.Book;

public class BookTest {

    @Test
    public void testBookCreation() {
        Book book = new Book("The Great Gatsby");
        assertNotNull("Book should be created", book);
    }

    @Test
    public void testGetTitle() {
        Book book = new Book("1984");
        assertEquals("1984", book.getTitle());
    }
}
