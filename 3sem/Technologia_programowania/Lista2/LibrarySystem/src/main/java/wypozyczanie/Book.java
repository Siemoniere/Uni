package wypozyczanie;
/**
 * Klasa reprezentująca książkę w systemie wypożyczeń.
 * 
 * Zgodnie z zasadami GRASP:
 * - Odpowiedzialność: Reprezentuje pojedynczą książkę w systemie, przechowując jej tytuł.
 * - Wysoka spójność: Klasa zawiera wyłącznie dane związane z książką (tytuł), co czyni ją prostą i jednorodną.
 * - Niskie sprzężenie: Klasa `Book` jest używana przez inne klasy bez zależności od ich implementacji, zapewniając niski stopień powiązań.
 */
public final class Book {

    private final String title;

    /**
     * Konstruuje nową książkę z podanym tytułem.
     *
     * @param title Tytuł książki
     */
    public Book(final String title) {
        this.title = title;
    }

    /**
     * Zwraca tytuł książki.
     *
     * @return Tytuł książki.
     */
    public String getTitle() {
        return title;
    }
}
