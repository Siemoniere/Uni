import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.paint.Color;
/**
 * Klasa reprezentująca aplikację GUI, która tworzy siatkę kolorowych prostokątów.
 * Prostokąty można modyfikować za pomocą oddzielnych wątków zarządzanych przez klasę MyThreads.
 */
public class MyGUI extends Application{

    private Scene scene;
    private BorderPane pane;
    private Rectangle rectangle;
    private Random rand;
    private Color color;
    private GridPane gridpane;
    private int n, m;
    private long k;
    private double p;
    /**
     * Główna metoda uruchamiająca aplikację GUI.
     * @param args Argumenty przekazywane do aplikacji z linii komend.
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    /**
     * Rozpoczyna główne okno aplikacji GUI, konfigurując środowisko.
     * @param primaryStage Główna scena dla tej aplikacji.
     */
    public void start (Stage primarystage){
        Parameters parameters = getParameters();
        java.util.List<String> args = parameters.getRaw();

        if (args.size() < 4) {
            System.err.println("Please provide four arguments: n, m, p, k");
            System.exit(1);
        }

        try {
            n = Integer.parseInt(args.get(0));
            m = Integer.parseInt(args.get(1));
            p = Double.parseDouble(args.get(2));
            k = Long.parseLong(args.get(3));
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
            System.exit(1);
        }
        Rectangle[][] allrecs = new Rectangle[n][m];
        boolean[][] isFrozen = new boolean[n][m];
        primarystage.setTitle("Disco");
        primarystage.setResizable(false);
        gridpane = new GridPane();
        pane = new BorderPane();
        rand = new Random();
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                rectangle = new Rectangle(60,60);
                colorise(rand, rectangle);
                allrecs[i][j] = rectangle;
                isFrozen[i][j] = false;
                gridpane.add(allrecs[i][j], j, i);
            }
        }
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                MyThreads mythread = new MyThreads(allrecs[i][j], rand, allrecs , i, j, p, k, isFrozen);
                Thread thread = new Thread(mythread);
                thread.setDaemon(true);
                thread.start();
            }
        }
        pane.setCenter(gridpane);
        scene = new Scene(pane);
        primarystage.setScene(scene);
        primarystage.show();
    }
    /**
     * Metoda do losowego kolorowania prostokątów.
     * @param rand Instancja Random używana do generowania losowych kolorów.
     * @param rectangle Prostokąt, który ma być pokolorowany.
     */
    public void colorise(Random rand, Rectangle rectangle){
        rand = new Random();
        rectangle.setFill(new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1.0));
    }
}