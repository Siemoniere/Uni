import static org.junit.Assert.*;
import org.junit.Test;
import wypozyczanie.Customer;
import wypozyczanie.Book;
import wypozyczanie.Library;
public class LibraryTest {

    @Test
    public void testAddAndDeleteBook() {
        Library library = new Library();
        Book book = new Book("Sapiens");
        library.addBook(book, 1);
        assertEquals(1, library.countCopies(book));
        library.deleteBook(book);
        assertEquals(0, library.countCopies(book));
    }

    @Test
    public void testRentAndReturnBook() {
        Library library = new Library();
        Customer customer = new Customer("John", "Smith");
        library.addCustomer(customer);
        Book book = new Book("1984");
        library.addBook(book, 1);
        library.rentBook("1984", "John", "Smith");
        library.giveBack("1984", "John", "Smith");
        assertEquals(1, library.countCopies(book));
    }
}
