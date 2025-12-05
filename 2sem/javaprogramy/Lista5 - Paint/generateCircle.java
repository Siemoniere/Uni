import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
/**
 * Klasa odpowiedzialna za generowanie i manipulację obiektami koła na scenie.
 *
 * @class generateCircle
 * @brief Tworzy i umożliwia manipulację kołem na scenie.
 * @param panel Główny panel, na którym umieszczane jest koło.
 * @param scena Główna scena, na której odbywają się interakcje z użytkownikiem.
 * @param c Obiekt koła, który ma być manipulowany.
 */
public class generateCircle {
    
    private double CstartX, CstartY;
    private double r;
    private boolean isFinished = false;
     /**
     * Konstruktor inicjujący działania związane z kołem na scenie.
     */
    public generateCircle(BorderPane panel, Scene scena, Circle c){
        
        scena.setOnMousePressed(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
                if(!isFinished){
                    panel.requestFocus();
                    if (!panel.getChildren().contains(c)) {
                        panel.getChildren().add(c);
                    }
                    c.setFill(Color.TRANSPARENT);
                    c.setStroke(Color.BLACK);
                    CstartX = e.getX();
                    CstartY = e.getY();
                    c.setCenterX(CstartX);
                    c.setCenterY(CstartY);
                }
            }
        });
        scena.setOnMouseDragged(e -> {
            if (!isFinished){
                double x = e.getX();
                double y = e.getY();
                r = Math.sqrt(Math.pow(x - CstartX, 2) + Math.pow(y - CstartY, 2));
                c.setRadius(r);
            }
        });
        scena.setOnMouseReleased(e -> {
            isFinished= true;
        });
        scena.setOnMouseClicked(e->{
            if ( c.contains(e.getX(), e.getY())) {
                new choose(panel, scena);
            }
        });
    }
}