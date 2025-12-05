import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
/**
 * Klasa odpowiedzialna za generowanie i manipulację prostokątem na scenie.
 *
 * @class generateRectangle
 * @brief Tworzy i umożliwia manipulację prostokątem na scenie.
 * @param panel Panel, na którym umieszczany jest prostokąt.
 * @param scena Scena, na której odbywają się interakcje z użytkownikiem.
 * @param rec Obiekt prostokąta, który ma być manipulowany.
 */
public class generateRectangle {

    public double RecstartX, RecstartY, height, width;
    private boolean isFinished = false;
    /**
     * Konstruktor inicjujący operacje na obiekcie prostokąta.
     */
    public generateRectangle(BorderPane panel, Scene scena, Rectangle rec){

        scena.setOnMousePressed(e ->{
            if(e.getButton() == MouseButton.PRIMARY){
                if(!isFinished ){
                    panel.requestFocus();
                    if (!panel.getChildren().contains(rec)) {
                        panel.getChildren().add(rec);
                    }
                    rec.setFill(Color.TRANSPARENT);
                    rec.setStroke(Color.BLACK);
                    
                    RecstartX = e.getX();
                    RecstartY = e.getY();
                    rec.setX(RecstartX);
                    rec.setY(RecstartY);
                }
            }
        });
        scena.setOnMouseDragged(e-> {
            if (e.getButton() == MouseButton.PRIMARY){
                if(!isFinished){
                    double x = e.getX();
                    double y = e.getY();
                    width = Math.abs(x-RecstartX);
                    height = Math.abs(y-RecstartY);
                    rec.setHeight(height);
                    rec.setWidth(width);
                }
            }
        });
        scena.setOnMouseReleased(e -> {
            isFinished = true;
        });
        scena.setOnMouseClicked(e->{
            if (rec.contains(e.getX(), e.getY())){
                new choose(panel, scena);
            }
        });
    }
}