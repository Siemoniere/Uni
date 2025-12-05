import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Translate;
/**
 * Klasa odpowiedzialna za interakcje z obiektem koła.
 *
 * @class CircleActions
 * @brief Zarządza działaniami na obiekcie koła.
 * @param scena Scena, na której odbywają się interakcje z użytkownikiem.
 * @param c Obiekt koła, który ma być manipulowany.
 * @param panel Panel, na którym umieszczane jest koło.
 */
public class CircleActions {

    private String[] colors ={"Red", "Green", "Blue"};
    private ContextMenu contextMenu;
    private MenuItem item;
    private double r, TstartX, TstartY, TendX, TendY;
    private Translate translate;
    /**
     * Konstruktor inicjujący działania na obiekcie koła.
     */
    public CircleActions(Scene scena, Circle c, BorderPane panel){

        contextMenu = new ContextMenu();
        for (String color : colors) {
            item = new MenuItem(color);
            item.setOnAction(e -> c.setFill(Color.web(color.toLowerCase())));
            contextMenu.getItems().add(item);
        }

        scena.setOnScroll(e->{
            if (c.getStrokeWidth()==5){
                r = c.getRadius();
                double deltaY = e.getDeltaY();
                if(deltaY > 0){
                    r += deltaY*0.1;
                    c.setRadius(r);
                } else r-=Math.abs(deltaY*0.1);
                c.setRadius(r);
            }
        });
        scena.setOnMousePressed(e->{
            if(c.getStrokeWidth()==5){
                if (e.getButton() == MouseButton.SECONDARY) {
                    if ( c.contains(e.getX(), e.getY())){
                        contextMenu.show(c, e.getScreenX(), e.getScreenY());
                    } else {
                        contextMenu.hide();
                    }               
                }
            }
        });
        scena.setOnKeyPressed(e ->{
            if (c.getStrokeWidth()==5){
                if (e.getCode() == KeyCode.T ){
                    c.setStroke(Color.PURPLE);

                    scena.setOnMousePressed(event->{
                        if (!c.getStroke().equals(Color.PURPLE)) c.setStroke(Color.PURPLE);
                        if(event.getButton() == MouseButton.SECONDARY){
                            translate = new Translate();
                            TstartX = event.getX();
                            TstartY = event.getY();
                        }
                    });
                    scena.setOnMouseDragged(event ->{
                        if(event.getButton() == MouseButton.SECONDARY && c.getStrokeWidth()==5){
                            TendX = event.getX();
                            TendY = event.getY();
                            c.setTranslateX((TendX-TstartX));
                            c.setTranslateY((TendY - TstartY));
                        }
                    });
                    scena.setOnMouseReleased(event ->{
                        c.setStroke(Color.BLACK);
                        double newX = c.getCenterX() + c.getTranslateX();
                        double newY = c.getCenterY() + c.getTranslateY();
                        c.setCenterX(newX);
                        c.setCenterY(newY);
                        c.setTranslateX(0);
                        c.setTranslateY(0);
                    });
                }
            }
        });
    }
}