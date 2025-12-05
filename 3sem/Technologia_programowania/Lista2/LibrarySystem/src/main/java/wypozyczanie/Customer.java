package wypozyczanie;

import java.util.List;
import java.util.ArrayList;

/**
 * Klasa reprezentująca klienta w systemie wypożyczeń.
 * 
 * Zgodnie z zasadami GRASP:
 * - Odpowiedzialność: Reprezentuje klientów biblioteki i zarządza ich wypożyczonymi książkami.
 * - Wysoka spójność: Klasa zarządza jedynie danymi klienta i jego listą wypożyczonych książek, spełniając zasadę jednorodności odpowiedzialności.
 * - Niskie sprzężenie: Klasa nie zarządza szczegółami klasy `Book`, operując na poziomie listy książek, co ogranicza bezpośrednie powiązania między klasami.
 */
public final class Customer {

    private final String name;
    private final String surname;
    private int howManyBorrowed;
    private List<Book> borrowedBooks;

    /**
     * Konstruuje nowego klienta z podanym imieniem i nazwiskiem.
     *
     * @param name Imię klienta
     * @param surname Nazwisko klienta
     */
    public Customer(final String name, final String surname) {
        this.name = name;
        this.surname = surname;
        this.borrowedBooks = new ArrayList<>();
    }

    /**
     * Zwraca pełne imię i nazwisko klienta.
     *
     * @return Pełne imię i nazwisko klienta.
     */
    public String getName() {
        return name + " " + surname;
    }

    /**
     * Zwraca liczbę książek wypożyczonych przez klienta.
     *
     * @return Liczba wypożyczonych książek
     */
    public int getNumber() {
        return howManyBorrowed;
    }

    /**
     * Dodaje książkę do listy wypożyczonych przez klienta.
     *
     * @param book Książka do wypożyczenia
     */
    public void borrow(final Book book) {
        borrowedBooks.add(book);
        howManyBorrowed++;
    }

    /**
     * Usuwa książkę z listy wypożyczonych przez klienta.
     *
     * @param book Książka do zwrotu
     */
    public void giveBookBack(final Book book) {
        borrowedBooks.remove(book);
        howManyBorrowed--;
    }
    /**
     * Zwraca listę książek wypożyczonych przez klienta.
     * @return Lista wypożyczonych książek
     */
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
