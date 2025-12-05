import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
/**
 * Klasa odpowiedzialna za generowanie i manipulację trójkątem na scenie.
 *
 * @class generateTriangle
 * @brief Tworzy i umożliwia manipulację trójkątem na scenie.
 * @param panel Panel, na którym umieszczany jest trójkąt.
 * @param scena Scena, na której odbywają się interakcje z użytkownikiem.
 * @param t Obiekt trójkąta, który ma być manipulowany.
 */
public class generateTriangle {

    private double[] TPoints= {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // Początkowe koordynaty punktów trójkąta
    private boolean isFinished = false; // Flaga zakończenia rysowania

    /**
     * Konstruktor inicjujący operacje na obiekcie trójkąta.
     */
    public generateTriangle(BorderPane panel, Scene scena, Polygon t){
        scena.setOnMousePressed(e -> {
            if(e.getButton() == MouseButton.PRIMARY && !isFinished){
                panel.requestFocus();
                if (!panel.getChildren().contains(t)) {
                    panel.getChildren().add(t);
                }
                t.setFill(Color.TRANSPARENT);
                t.setStroke(Color.BLACK);
                t.getPoints().clear();
                for (int i = 0; i < 6; i++){
                    t.getPoints().add(TPoints[i]);
                }
                TPoints[0] = e.getX();
                TPoints[1] = e.getY();
                TPoints[2] = e.getX();
                TPoints[3] = e.getY();
                TPoints[4] = e.getX();
                TPoints[5] = e.getY();
            }
        });
        scena.setOnMouseDragged(e -> {
            if (!isFinished) {
                TPoints[4] = e.getX();
                TPoints[5] = e.getY();
                TPoints[3] = e.getY();
                TPoints[0] = (TPoints[2] + TPoints[4]) / 2;
                for (int i = 0; i < TPoints.length; i++){
                    t.getPoints().set(i, TPoints[i]);
                }
            }
        });
        scena.setOnMouseReleased(e -> {
            isFinished = true;
        });
        scena.setOnMouseClicked(e -> {
            if (t.contains(e.getX(), e.getY())) {
                new choose(panel, scena);
            }
        });
    }
}
