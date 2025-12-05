import java.io.*;
import java.net.*;
/**
 * Klasa MultiThread obsługuje połączenia klientów i wykonuje operacje na strukturze drzewa.
 */
public class MultiThread extends Thread {
    private Socket socket;
    private int type;
    private String t;
    Tree<Integer> treeInt = null;
    Tree<Double> treeDouble = null;
    Tree<String> treeString = null;
    /**
     * Konstruktor klasy MultiThread z podanym gniazdem.
     *
     * @param socket gniazdo klienta
     */
    public MultiThread(Socket socket) {
        this.socket = socket;
    }
    /**
     * Metoda run obsługuje żądania klientów i wykonuje operacje na drzewie.
     */
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);
            
            String line = in.readLine();
            if (line != null && !line.trim().isEmpty()) {
                type = Integer.parseInt(line);
                switch (type) {
                    case 1:
                        treeInt = new Tree<Integer>();
                        System.out.println("stworzono integer");
                        break;
                    case 2:
                        treeDouble = new Tree<Double>();
                        System.out.println("stworzono double");
                        break;
                    case 3:
                        treeString = new Tree<String>();
                        System.out.println("stworzono stringa");
                        break;
                }
            } else {
                System.out.println("Klient się rozłączył");
                return;
            }

            String value;
            int intValue = 0;
            double doubleValue = 0;
            boolean isCorrect = true;
            while (true) {
                line = in.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    int activity = Integer.parseInt(line);

                    switch (activity) {
                        case 1:
                            isCorrect = true;
                            value = in.readLine();
                                System.out.println("dodaję " + value);
                                try {
                                    if (type == 1) {
                                        intValue = Integer.parseInt(value);
                                    } else if (type == 2) {
                                        doubleValue = Double.parseDouble(value);
                                    } else {
                                        treeString.add(value);
                                        out.println(treeString);
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid value: " + value);
                                    isCorrect = false;
                                    if (type == 1) out.println(treeInt);
                                    else if (type == 2) out.println(treeDouble);
                                }
                                if (isCorrect) {
                                    if (type == 1) {
                                        treeInt.add(intValue);
                                        out.println(treeInt);
                                    } else if (type == 2) {
                                        treeDouble.add(doubleValue);
                                        out.println(treeDouble);
                                    }
                                }
                            break;
                        case 2:
                            isCorrect = true;
                            value = in.readLine();
                                System.out.println("szukam " + value);
                                try {
                                    if (type == 1) {
                                        intValue = Integer.parseInt(value);
                                    } else if (type == 2) {
                                        doubleValue = Double.parseDouble(value);
                                    } else {
                                        if (treeString.search(value)) out.println("Istnieje");
                                        else out.println("Nie istnieje");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid value: " + value);
                                    out.println("niepoprawny typ");
                                    isCorrect = false;
                                }
                                if (isCorrect) {
                                    if (type == 1) {
                                        if (treeInt.search(intValue)) out.println("Istnieje");
                                        else out.println("Nie istnieje");
                                    } else if (type == 2) {
                                        if (treeDouble.search(doubleValue)) out.println("Istnieje");
                                        else out.println("Nie istnieje");
                                    }
                                }
                            break;
                        case 3:
                            isCorrect = true;
                            value = in.readLine();
                                System.out.println("usuwam " + value);
                                try {
                                    if (type == 1) {
                                        intValue = Integer.parseInt(value);
                                    } else if (type == 2) {
                                        doubleValue = Double.parseDouble(value);
                                    } else {
                                        treeString.delete(value);
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid value: " + value);
                                    isCorrect = false;
                                    if (type == 1) out.println(treeInt);
                                    else if (type == 2) out.println(treeDouble);
                                }
                                if (isCorrect) {
                                    if (type == 1) {
                                        treeInt.delete(intValue);
                                        out.println(treeInt);
                                    } else if (type == 2) {
                                        treeDouble.delete(doubleValue);
                                        out.println(treeDouble);
                                    }
                                }
                    }
                } else {
                    System.out.println("Client disconnected");
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
