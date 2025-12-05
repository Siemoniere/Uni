import java.net.*;
import java.io.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.control.Menu;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import java.util.*;
/**
 * Klasa ClientGUI implementuje interfejs GUI klienta do zarządzania drzewem binarnym.
 */
public class ClientGUI extends Application {

    private Scene scene;
    private BorderPane mainpane, treepane;
    private Button addButton, deleteButton, searchButton, IntegerButton, DoubleButton, StringButton;
    private HBox typeBox, actionBox;
    private TextField textfield;
    private Label label, tree;
    private Socket socket; 
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Metoda główna uruchamia aplikację JavaFX.
     *
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Metoda start inicjalizuje interfejs GUI i nawiązuje połączenie z serwerem.
     *
     * @param primaryStage główne okno aplikacji
     */
    @Override
    public void start (Stage primaryStage){
        primaryStage.setTitle("Binary Tree Client");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);

        mainpane = new BorderPane();
        scene = new Scene(mainpane);

        IntegerButton = new Button("Integer");
        DoubleButton = new Button("Double");
        StringButton = new Button("String");
        typeBox = new HBox(20);
        typeBox.getChildren().addAll(IntegerButton, DoubleButton, StringButton);
        typeBox.setAlignment(Pos.CENTER);
        mainpane.setCenter(typeBox);

        primaryStage.setScene(scene);
        primaryStage.show();

        try  {
            socket = new Socket("localhost", 4444); 
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }

        IntegerButton.setOnAction( e->{
            createRest();
            mainpane.getChildren().remove(typeBox);
            sendInfo(1);
        });
        DoubleButton.setOnAction( e->{
            createRest();
            mainpane.getChildren().remove(typeBox);
            sendInfo(2);
        });
        StringButton.setOnAction( e->{
            createRest();
            mainpane.getChildren().remove(typeBox);
            sendInfo(3);
        });
        
    }
    /**
     * Wysyła informację o typie danych do serwera.
     *
     * @param type typ danych (1: Integer, 2: Double, 3: String)
     */
    public void sendInfo(int type){
            out.println(type);
    }
    /**
     * Wysyła wartość do serwera.
     *
     * @param value wartość do wysłania
     */
    public void sendValue(String value){
            out.println(value);
    }
    /**
     * Pobiera aktualny stan drzewa od serwera.
     *
     * @return aktualny stan drzewa jako String
     * @throws IOException jeśli wystąpi błąd wejścia/wyjścia
     */
    public String getTree() throws IOException {
        String treeString = null;
        try {
            treeString = in.readLine();
        } catch (IOException ex) {
            System.out.println("Error reading from server: " + ex.getMessage());
            throw ex;
        }
        return treeString;
    }
    /**
     * Rysuje drzewo na podstawie przekazanego ciągu znaków.
     *
     * @param s ciąg znaków reprezentujący drzewo
     * @param x współrzędna x
     * @param y współrzędna y
     * @param gap odstęp między elementami drzewa
     */
    public void draw(String s, double x , double y, double gap){
        int length = s.length();
        int pierwszyDwukropek = s.indexOf(':');
        if(pierwszyDwukropek == -1) return;
        String firstValue = s.substring(1, pierwszyDwukropek);

        Circle circle = new Circle(x, y, 20);//koło
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text rootValue = new Text(x-10, y+5, firstValue);//wartość
        mainpane.getChildren().addAll(circle, rootValue);

        int drugiDwukropek = s.indexOf(':', pierwszyDwukropek+1);
        String check = s.substring(pierwszyDwukropek+1, drugiDwukropek);
            int count = 0, i=0;
            for (i=0;i<s.length();i++){
                if(s.charAt(i)=='(') count++;
                else if (s.charAt(i)== ')') {
                    count--;
                    if (count == 1) break;
                }
            }
            String leftSide = s.substring(pierwszyDwukropek+1, i+1);
            if (!leftSide.equals("()")) {
                draw(leftSide, x-gap, y+50, gap/2);
                Line leftLine = new Line(x - gap, y+50, x, y); //lewa linia
                mainpane.getChildren().add(leftLine);
            }
            String rightSide = s.substring(i+2, length-1);
            if (!rightSide.equals("()")) {
                draw(rightSide, x+gap, y+50, gap/2);
                Line rightLine = new Line(x + gap, y+50, x, y); //prawa linia
                mainpane.getChildren().add(rightLine);
            }
    }
    /**
     * Usuwa elementy graficzne reprezentujące drzewo z głównego panelu.
     */
    public void clear(){
        List<Node> nodesToRemove = new ArrayList<>();
    
        for (Node node : mainpane.getChildren()) {
            if (node instanceof Line || node instanceof Circle || node instanceof Text) {
                nodesToRemove.add(node);
            }
        }
        
        mainpane.getChildren().removeAll(nodesToRemove);
    }
    /**
     * Tworzy GUI do obsługi dodawania, wyszukiwania i usuwania elementów drzewa.
     */
    public void createRest(){
        addButton = new Button("add");
        searchButton = new Button("search");
        deleteButton = new Button("delete");
        textfield = new TextField();
        label = new Label("true/false");
        actionBox = new HBox();
        actionBox.getChildren().addAll(textfield, addButton, deleteButton, searchButton, label);
        actionBox.setAlignment(Pos.CENTER);
        mainpane.setTop(actionBox);
        tree = new Label();
        mainpane.setCenter(tree);

        addButton.setOnAction(e->{
            sendInfo(1);
            sendValue(textfield.getText());
            try {
                clear();
                draw(getTree(), 400, 100, 200);
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                label.setText("Error");
            }
        });
        searchButton.setOnAction(e->{
            sendInfo(2);
            sendValue(textfield.getText());
            try {
                    String s = in.readLine();
                    label.setText(s);
                } catch (IOException ex) {
                    System.out.println("Error reading from server: " + ex.getMessage());
                    label.setText("Error");
                }
        });
        deleteButton.setOnAction(e ->{
            sendInfo(3);
            sendValue(textfield.getText());
            try {
                clear();
                draw(getTree(), 400, 100, 200);
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                label.setText("Error");
            }
        });
    }
}