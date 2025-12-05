import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
/**
 * Klasa odpowiedzialna za interakcje z obiektem prostokąta.
 *
 * @class RectangleActions
 * @brief Zarządza działaniami na obiekcie prostokąta.
 * @param scena Scena, na której odbywają się interakcje z użytkownikiem.
 * @param rectangle Obiekt prostokąta, który ma być manipulowany.
 * @param panel Panel, na którym umieszczany jest prostokąt.
 */
public class RectangleActions {

    private  double X, Y, height, width, angle, TstartX, TstartY, TendX, TendY, RstartX, RstartY;
    private ContextMenu contextMenu;
    private MenuItem item;
    private String[] colors ={"Red", "Green", "Blue"};
    private Rotate rotate;
    private Translate translate;
    /**
     * Konstruktor inicjujący działania na obiekcie prostokąta.
     */
    public RectangleActions(Scene scena, Rectangle rectangle, BorderPane panel){
        
        contextMenu = new ContextMenu();
            for (String color : colors) {
                item = new MenuItem(color);
                item.setOnAction(ev -> rectangle.setFill(Color.web(color.toLowerCase())));
                contextMenu.getItems().add(item);
            }
        X = rectangle.getX();
        Y= rectangle.getY();
        height = rectangle.getHeight();
        width = rectangle.getWidth();

        scena.setOnScroll(e->{
            if (rectangle.getStrokeWidth()==5){
                double deltaY = e.getDeltaY();
                if(deltaY > 0){
                    
                    height+= deltaY*0.1;
                    width+= deltaY*0.1;
                    X-= deltaY*0.05;
                    Y-= deltaY*0.05;
                    rectangle.setHeight(height);
                    rectangle.setWidth(width);
                    rectangle.setX(X);
                    rectangle.setY(Y);
                } else {
                height-=Math.abs(deltaY*0.1);
                width-=Math.abs(deltaY*0.1);
                X+= Math.abs(deltaY*0.05);
                Y+= Math.abs(deltaY*0.05);
                rectangle.setHeight(height);
                rectangle.setWidth(width);
                rectangle.setX(X);
                rectangle.setY(Y);
                }
            }
        });
        scena.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.R && rectangle.getStrokeWidth()==5){
                rectangle.setStroke(Color.RED);
                scena.setOnMousePressed(event->{
                    if(event.getButton() == MouseButton.SECONDARY){
                        if (!rectangle.getStroke().equals(Color.RED)) rectangle.setStroke(Color.RED);
                        RstartX = event.getX() - rectangle.getTranslateX();
                        RstartY = event.getY() - rectangle.getTranslateY();
                    }
                });
                scena.setOnMouseDragged(event->{
                    if(event.getButton() == MouseButton.SECONDARY && rectangle.getStrokeWidth()==5){
                        double centerX = X + rectangle.getWidth() / 2;
                        double centerY = Y + rectangle.getHeight() / 2;
                        double deltaX = event.getX() - centerX;
                        double deltaY = event.getY() - centerY;
                        double startAngle = Math.atan2(RstartY - centerY, RstartX - centerX);
                        double newAngle = Math.atan2(deltaY, deltaX);
                        double angle = Math.toDegrees(newAngle - startAngle);
                        
                        rotate = new Rotate();
                        rotate.setPivotX(X+(width/2));
                        rotate.setPivotY(Y+(height/2));
                        rectangle.setRotate(rectangle.getRotate() + angle);

                        RstartX = event.getX() - rectangle.getTranslateX();
                        RstartY = event.getY() - rectangle.getTranslateY();
                    }
                });
                scena.setOnMouseReleased(event->{
                    rectangle.setStroke(Color.BLACK);
                });
            } else if (e.getCode() == KeyCode.T && rectangle.getStrokeWidth()==5){
                rectangle.setStroke(Color.PURPLE);

                scena.setOnMousePressed(event->{
                    if (!rectangle.getStroke().equals(Color.PURPLE)) rectangle.setStroke(Color.PURPLE);
                    if(event.getButton() == MouseButton.SECONDARY){
                        translate = new Translate();
                        TstartX = event.getX();
                        TstartY = event.getY();
                    }
                });
                scena.setOnMouseDragged(event ->{
                    if(event.getButton() == MouseButton.SECONDARY && rectangle.getStrokeWidth()==5){
                        TendX = event.getX();
                        TendY = event.getY();
                        rectangle.setTranslateX((TendX-TstartX));
                        rectangle.setTranslateY((TendY - TstartY));
                    }
                });
                scena.setOnMouseReleased(event ->{
                    rectangle.setStroke(Color.BLACK);
                    
                    double newX = rectangle.getX() + rectangle.getTranslateX();
                    double newY = rectangle.getY() + rectangle.getTranslateY();
                    rectangle.setX(newX);
                    rectangle.setY(newY);
                    rectangle.setTranslateX(0);
                    rectangle.setTranslateY(0);
                    X = newX;
                    Y = newY;
                });
            }
        });
        scena.setOnMousePressed(event ->{
            if (rectangle.getStroke().equals(Color.BLACK) && event.getButton() == MouseButton.SECONDARY) {
                if ( rectangle.contains(event.getX(), event.getY())){
                    contextMenu.show(rectangle, event.getScreenX(), event.getScreenY());
                } else {
                    contextMenu.hide();
                    rectangle.setStroke(Color.BLACK);
                }
            }
        });
    }
}