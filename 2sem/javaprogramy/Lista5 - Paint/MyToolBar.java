import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.geometry.Pos;
import java.util.*;

/**
 * Klasa tworząca i zarządzająca paskiem narzędzi w aplikacji graficznej.
 *
 * @class MyToolBar
 * @brief Zarządza paskiem narzędzi z przyciskami do generowania figur.
 * @param mainpane Główny panel aplikacji.
 * @param toolpane Panel narzędziowy, na którym umieszczony jest pasek narzędzi.
 * @param drawingpane Panel rysunkowy, na którym tworzone są grafiki.
 * @param scena Główna scena aplikacji.
 */
public class MyToolBar {

    private HBox hbox;
    private Button triangleButton, rectangleButton, circleButton;
    private Circle c;
    private Rectangle rec;
    private Polygon t;
    /**
     * Konstruktor tworzący pasek narzędzi i przyciski.
     */
    public MyToolBar (BorderPane mainpane, BorderPane toolpane, BorderPane drawingpane, Scene scena){
        mainpane.setCenter(toolpane);
        toolpane.setCenter(drawingpane);
        hbox = new HBox(15);
        triangleButton = new Button("Trójkąt");
        circleButton = new Button("Koło");
        rectangleButton = new Button("Prostokąt");

        hbox.getChildren().addAll(triangleButton, circleButton, rectangleButton);
        hbox.setAlignment(Pos.CENTER);
        toolpane.setTop(hbox);

        triangleButton.setOnAction(e-> {
            t = new Polygon();
            new generateTriangle(mainpane, scena, t);
        });
        circleButton.setOnAction(e-> {
            c = new Circle (0,0,0);
            new generateCircle(mainpane, scena, c);
        });
        rectangleButton.setOnAction(e-> {
            rec = new Rectangle ();
            new generateRectangle(mainpane, scena, rec);
        });
    }
}