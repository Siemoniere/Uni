package wypozyczanie;

import java.util.Scanner;
/**
 * Klasa główna `Main` zarządza interakcją z użytkownikiem.
 *
 * Zgodnie z zasadami GRASP:
 * - Odpowiedzialność: Klasa pełni rolę interfejsu użytkownika, pozwala na dodawanie książek, klientów oraz zarządzanie procesami wypożyczania.
 * - Wysoka spójność: Klasa jest odpowiedzialna tylko za interakcję z użytkownikiem i nie ingeruje w szczegóły implementacji `Library`.
 * - Niskie sprzężenie: `Main` komunikuje się jedynie z `Library`, przekazując jej dane od użytkownika i nie manipulując bezpośrednio jej stanem.
 */
public class Main {
    private static final int ADD_BOOK = 1;
    private static final int ADD_CUSTOMER = 2;
    private static final int SHOW_BOOKS = 3;
    private static final int SHOW_CUSTOMERS = 4;
    private static final int RENT = 5;
    private static final int GIVE_BACK = 6;
    private static final int EXIT = 7;
    public static void main(String[] args) {
        System.out.println("Hello!");
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();       
        while (true) {
            System.out.println("------------------------");
            System.out.println("What do you want to do?");
            System.out.println("1. Add a book");
            System.out.println("2. Add a customer");
            System.out.println("3. Show all books");
            System.out.println("4. Show all customers");
            System.out.println("5. Rent a book");
            System.out.println("6. Give book back");
            System.out.println("7. Exit");
            System.out.println("------------------------");
            int value = scanner.nextInt();
            scanner.nextLine();
            switch (value) {
                case ADD_BOOK:
                    System.out.println("Book title:");
                    String title = scanner.nextLine();
                    System.out.println("Number of copies:");
                    int copies = scanner.nextInt();
                    scanner.nextLine();
                    Book book = new Book(title);
                    library.addBook(book, copies);
                    break;
                case ADD_CUSTOMER:
                    System.out.println("Customer name:");
                    String name = scanner.nextLine();
                    System.out.println("Customer surname:");
                    String surname = scanner.nextLine();
                    Customer customer = new Customer(name, surname);
                    library.addCustomer(customer);
                    break;
                case SHOW_BOOKS:
                    library.showBooks();
                    break;
                case SHOW_CUSTOMERS:
                    library.showCustomers();
                    break;
                case RENT:
                    System.out.println("Customer name: ");
                    String cname = scanner.nextLine();
                    System.out.println("Customer surname: ");
                    String csurname = scanner.nextLine();
                    if (library.searchCustomer(cname, csurname)) {
                        System.out.println("Book title:");
                        String btitle = scanner.nextLine();
                        if (library.searchBook(btitle)) {
                            library.rentBook(btitle, cname, csurname);
                        } else {
                            System.err.println("No such a book!");
                        }
                    } else {
                        System.err.println("No such a customer!");
                        break;
                    }
                    break;
                case GIVE_BACK:
                    System.out.println("Customer name: ");
                    String cuname = scanner.nextLine();
                    System.out.println("Customer surname: ");
                    String cusurname = scanner.nextLine();
                    if (library.searchCustomer(cuname, cusurname)) {
                        System.out.println("Book title: ");
                        String boTitle = scanner.nextLine();
                        library.giveBack(boTitle, cuname, cusurname);
                    } else {
                        System.err.println("No such a customer!");
                        break;
                    }

                    break;
                case EXIT:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.err.println("No such an action!");
                    break;
            }
            if (value == EXIT) {
                break;
            }
        }
        scanner.close();
    }
}