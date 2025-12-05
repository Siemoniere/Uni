import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
/**
 * Główna klasa aplikacji do rysowania w stylu Paint przy użyciu JavaFX.
 *
 * @class Paint
 * @brief Klasa główna aplikacji, konfigurująca główną scenę i panele.
 */
public class Paint extends Application{

    private BorderPane mainpane, toolpane, drawingpane;
    private Scene scena;
    /**
     * Główna metoda aplikacji.
     * @param args Argumenty przekazywane do aplikacji.
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Inicjalizuje i wyświetla główne okno aplikacji.
     * @param okno Główna scena/stage, na której wyświetlane są komponenty.
     */
    @Override
    public void start (Stage okno){
        okno.setHeight(1200);
        okno.setWidth(1800);
        okno.setTitle("Paint");
        mainpane = new BorderPane();
        toolpane = new BorderPane();
        drawingpane = new BorderPane();
        scena = new Scene(mainpane);
        new MyMenu(mainpane, drawingpane, okno);
        new MyToolBar(mainpane, toolpane,drawingpane, scena);
        okno.setScene(scena);
        okno.show();
    }
}