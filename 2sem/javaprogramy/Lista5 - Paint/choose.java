import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
/**
 * Klasa odpowiedzialna za wybór i aktywację figur na scenie.
 *
 * @class choose
 * @brief Zarządza wyborem i aktywacją figur.
 * @param panel Panel, na którym umieszczane są figury.
 * @param scena Scena, na której odbywają się interakcje z użytkownikiem.
 */
public class choose {

    private boolean isActive=false;
    private Circle c;
    private Rectangle rec;
    private Polygon t;
    /**
     * Konstruktor aktywujący i dezaktywujący figury na scenie.
     */
    public choose(BorderPane panel, Scene scena) {

        scena.setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.PRIMARY && !isActive){
                for (Node node : panel.getChildren()) {
                    if (node instanceof Rectangle) {
                        rec = (Rectangle) node;
                        if (rec.contains(e.getX(), e.getY())) {
                            activate(rec, scena, panel);
                            isActive = true;
                            break;
                        }
                    } else if (node instanceof Circle) {
                        c = (Circle) node;
                        if (c.contains(e.getX(), e.getY())) {
                            activate(c, scena, panel);
                            isActive = true;
                            break;
                        }
                    } else if (node instanceof Polygon) {
                        t = (Polygon) node;
                        if (t.contains(e.getX(), e.getY())) {
                            activate(t, scena, panel);
                            isActive = true;
                            break;
                        }
                    }
                }
            }else{
                for (Node node : panel.getChildrenUnmodifiable()) {
                    if (node instanceof Rectangle) {
                        rec = (Rectangle) node;
                        if (rec.contains(e.getX(), e.getY()) && rec.getStrokeWidth()==5) {
                            deactivate(rec);
                            isActive = false;
                            break;
                        }
                    } else if (node instanceof Circle) {
                        c = (Circle) node;
                        if (c.contains(e.getX(), e.getY()) && c.getStrokeWidth()==5) {
                            deactivate(c);
                            isActive = false;
                            break;
                        }
                    } else if (node instanceof Polygon) {
                        t = (Polygon) node;
                        if (t.contains(e.getX(), e.getY()) && t.getStrokeWidth()==5) {
                            deactivate(t);
                            isActive = false;
                            break;
                        }
                    }
                }
                }
        });
    }
    /**
     * Dezaktywuje figurę, wyłączając dla niej specjalne działania.
     */
    public void deactivate(Node node){
        if( node instanceof Rectangle){
            Rectangle rectangle = (Rectangle) node;
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(1);
        } else if (node instanceof Circle){
            Circle circle = (Circle) node;
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(1);
        } else if (node instanceof Polygon){
            Polygon polygon = (Polygon) node;
            polygon.setStroke(Color.BLACK);
            polygon.setStrokeWidth(1);
        }
    }
     /**
     * Aktywuje wybraną figurę, włączając dla niej specjalne działania (np. przesuwanie, skalowanie).
     */
    public void activate(Node node, Scene scena, BorderPane panel){
        if (node instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) node;
            new RectangleActions(scena, rectangle, panel);
            rectangle.setStrokeWidth(5);
        } else if (node instanceof Circle) {
            Circle circle = (Circle) node;
            new CircleActions(scena, circle, panel);
            circle.setStrokeWidth(5);
        } else if (node instanceof Polygon) {
            Polygon polygon = (Polygon) node;
            new TriangleActions(scena, polygon, panel);
            polygon.setStrokeWidth(5);
        }
    }
}