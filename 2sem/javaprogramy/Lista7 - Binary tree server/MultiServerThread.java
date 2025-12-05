import java.io.*;
import java.net.*;
/**
 * Klasa MultiServerThread uruchamia serwer, który nasłuchuje na porcie 4444 i obsługuje połączenia klientów.
 */
public class MultiServerThread {
    /**
     * Metoda główna uruchamia serwer i akceptuje połączenia klientów.
     *
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            System.out.println("Server is listening on port 4444");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new MultiThread(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}