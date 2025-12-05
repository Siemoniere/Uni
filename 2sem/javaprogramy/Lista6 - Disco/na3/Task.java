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
public class Task extends Application{

    private Scene scene;
    private BorderPane pane;
    private Rectangle rectangle;
    private Random rand;
    private Color color;
    private GridPane gridpane;
    public static void main(String[] args) {
        launch(args);
    }
    public void start (Stage primarystage){
        int n=20;
        int m=32;
        Rectangle[][] allrecs = new Rectangle[n][m];
        primarystage.setTitle("Disco");
        primarystage.setMaximized(true); 
        gridpane = new GridPane();
        pane = new BorderPane();
        rand = new Random();
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                rectangle = new Rectangle(60,60);
                colorise(rand, rectangle);
                allrecs[i][j] = rectangle;
                gridpane.add(allrecs[i][j], j, i);
                MyThreads mythread = new MyThreads(rectangle, rand);
                Thread thread = new Thread(mythread);
                thread.start();
            }
        }
        pane.setCenter(gridpane);
        scene = new Scene(pane);
        primarystage.setScene(scene);
        primarystage.show();
    }
    public void colorise(Random rand, Rectangle rectangle){
        rand = new Random();
        rectangle.setFill(new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1.0));
    }
}