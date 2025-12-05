package wypozyczanie;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashSet;

/**
 * Klasa reprezentująca bibliotekę, która zarządza książkami i klientami.
 * * Zgodnie z zasadami GRASP:
 * - Odpowiedzialność: Klasa jest odpowiedzialna za zarządzanie listą książek i klientów, a także operacje takie jak dodawanie książek, wypożyczanie oraz zwracanie.
 * - Wysoka spójność: Klasa skupia się wyłącznie na operacjach związanych z zarządzaniem biblioteką.
 * - Niskie sprzężenie: Klasa korzysta z `Book` i `Customer`, ale nie zarządza bezpośrednio ich stanem, wywołując ich metody zamiast manipulacji ich danymi.
 */
public final class Library {

    private List<Book> books;
    private List<Customer> customers;

    /**
     * Konstruktor klasy Library, inicjalizuje listy książek i klientów.
     */
    public Library() {
        books = new ArrayList<>();
        customers = new ArrayList<>();
    }

    /**
     * Dodaje książkę do biblioteki.
     *
     * @param book Książka do dodania
     */
    public void addBook(final Book book, int numberOfCopies) {
        for (int i = 0; i < numberOfCopies; i++) {
            books.add(book);
        }
    }

    /**
     * Zlicza ilość kopii danej książki w bibliotece.
     *
     * @param book Książka, której kopie są zliczane
     * @return Ilość kopii książki
     */
    public int countCopies(final Book book) {
        int count = 0;
        for (Book b : books) {
            if (b.getTitle().equals(book.getTitle())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Usuwa książkę z biblioteki.
     *
     * @param book Książka do usunięcia
     */
    public void deleteBook(final Book book) {
        books.remove(book);
    }

    /**
     * Dodaje klienta do biblioteki.
     *
     * @param customer Klient do dodania
     */
    public void addCustomer(final Customer customer) {
        customers.add(customer);
    }

    /**
     * Wypożycza książkę klientowi.
     *
     * @param title Tytuł książki do wypożyczenia
     * @param name Imię klienta
     * @param surname Nazwisko klienta
     */
    public void rentBook(final String title, final String name, final String surname) {
        Book oneBook = null;
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                oneBook = book;
                books.remove(book);
                break;
            }
        }
        for (Customer customer : customers) {
            String fullName = name + " " + surname;
            if (customer.getName().equals(fullName)) {
                customer.borrow(oneBook);
                break;
            }
        }
    }

    /**
     * Oddaje książkę do biblioteki.
     *
     * @param title Tytuł książki do oddania
     * @param name Imię klienta
     * @param surname Nazwisko klienta
     */
    public void giveBack(final String title, final String name, final String surname) {
        String fullName = name + " " + surname;
        for (Customer customer : customers) {
            if (customer.getName().equals(fullName)) {
                for (Book borrowedBook : customer.getBorrowedBooks()) {
                    if (borrowedBook.getTitle().equals(title)) {
                        customer.giveBookBack(borrowedBook);
                        books.add(borrowedBook);
                        break;
                    }
                }
            }
        }
    }

        /**
     * Wyświetla listę wszystkich klientów w bibliotece.
     */
    public void showCustomers() {
        int i = 1;
        for (Customer one : customers) {
            System.out.println(i + ". Name: " + one.getName() + "\nHow many borrowed: " + one.getNumber());
            i++;
        }
    }

        /**
     * Wyświetla listę wszystkich książek dostępnych w bibliotece wraz z liczbą dostępnych kopii.
     */
    public void showBooks() {
        LinkedHashSet<String> bookset = new LinkedHashSet<>();
        for (Book one : books) {
            bookset.add(one.getTitle());
        }
        int i = 1;
        for (String uniqueTitle : bookset){
            Book book = new Book(uniqueTitle);
            System.out.println(i + ". Title: " + uniqueTitle + "\nNumber of copies: " + countCopies(book));
            i++;
        }
    }

        /**
     * Wyszukuje klienta w bibliotece na podstawie imienia i nazwiska.
     *
     * @param name Imię klienta
     * @param surname Nazwisko klienta
     * @return true jeśli klient istnieje, false w przeciwnym razie
     */
    public boolean searchCustomer(final String name, final String surname) {
        String fullName = name + " " + surname;
        for (Customer one : customers) {
            if (one.getName().equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Wyszukuje książkę w bibliotece na podstawie tytułu.
     *
     * @param title Tytuł książki
     * @return true jeśli książka istnieje, false w przeciwnym razie
     */
    public boolean searchBook(final String title) {
        for (Book one : books) {
            if (one.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}