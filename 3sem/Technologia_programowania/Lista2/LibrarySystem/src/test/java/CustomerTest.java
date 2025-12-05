import static org.junit.Assert.*;
import org.junit.Test;
import wypozyczanie.Customer;
import wypozyczanie.Book;
public class CustomerTest {

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer("John", "Doe");
        assertEquals("John Doe", customer.getName());
    }

    @Test
    public void testBorrowAndReturnBook() {
        Customer customer = new Customer("Jane", "Doe");
        Book book = new Book("Brave New World");
        customer.borrow(book);
        assertEquals(1, customer.getNumber());
        customer.giveBookBack(book);
        assertEquals(0, customer.getNumber());
    }
}
