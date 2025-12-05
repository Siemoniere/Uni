import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.KeyCode;
import javafx.collections.ObservableList;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
/**
 * Klasa odpowiedzialna za interakcje z obiektem trójkąta.
 *
 * @class TriangleActions
 * @brief Zarządza działaniami na obiekcie trójkąta.
 * @param scena Scena, na której odbywają się interakcje z użytkownikiem.
 * @param t Obiekt trójkąta, który ma być manipulowany.
 * @param panel Panel, na którym umieszczany jest trójkąt.
 */
public class TriangleActions {

    private double[] TPoints={0.0,0.0,0.0,0.0,0.0,0.0}; 
    private String[] colors ={"Red", "Green", "Blue"};
    private ContextMenu contextMenu;
    private MenuItem item;
    private Rotate rotate;
    private Translate translate;
    private double X, Y, TstartX, TendX, RstartX, RstartY, TendY, TstartY;
    ObservableList<Double> points;

    /**
     * Konstruktor inicjujący działania na obiekcie trójkąta.
     */
    public TriangleActions (Scene scena, Polygon t, BorderPane panel){

        contextMenu = new ContextMenu();
        for (String color : colors) {
            item = new MenuItem(color);
            item.setOnAction(e -> t.setFill(Color.web(color.toLowerCase())));
            contextMenu.getItems().add(item);
        }
        points = t.getPoints();
        for (int i=0; i<6;i++){
            TPoints[i] = points.get(i);
        }
        scena.setOnScroll(e->{
            if(t.getStrokeWidth()==5){
                double deltaY = e.getDeltaY();
                if(deltaY > 0){
                    TPoints[1]-= Math.abs(deltaY*0.05);
                    TPoints[2]-= Math.abs(deltaY*0.05);
                    TPoints[3]+= deltaY*0.05;
                    TPoints[4]+= deltaY*0.05;
                    TPoints[5]+= deltaY*0.05;
                    for (int i=0;i<TPoints.length;i++){
                        t.getPoints().set(i, TPoints[i]);
                    }
                } else{
                    TPoints[1]+= Math.abs(deltaY*0.05);
                    TPoints[2]+= Math.abs(deltaY*0.05);
                    TPoints[3]-= Math.abs(deltaY*0.05);
                    TPoints[4]-= Math.abs(deltaY*0.05);
                    TPoints[5]-= Math.abs(deltaY*0.05);
                    for (int i=0;i<TPoints.length;i++){
                        t.getPoints().set(i, TPoints[i]);
                    }
                }
            }
        });
        scena.setOnMousePressed(e->{
            if (t.getStrokeWidth()==5){
                if (e.getButton() == MouseButton.SECONDARY && t.contains(e.getX(), e.getY())) {
                    contextMenu.show(t, e.getScreenX(), e.getScreenY());
                } else {
                    contextMenu.hide();
                }
            }
        });
        scena.setOnKeyPressed(e ->{
            if (t.getStrokeWidth()==5){
                if (e.getCode() == KeyCode.R){
                    t.setStroke(Color.RED);
                    scena.setOnMousePressed(event->{
                        if (!t.getStroke().equals(Color.RED)) t.setStroke(Color.RED);
                        if(event.getButton() == MouseButton.SECONDARY){
                            RstartX = event.getX() - t.getTranslateX();
                            RstartY = event.getY() - t.getTranslateY();
                        }
                    });
                    scena.setOnMouseDragged(event->{
                        if(event.getButton() == MouseButton.SECONDARY && t.getStrokeWidth()==5){
                            rotate = new Rotate();
                            double height = TPoints[3]-TPoints[1];
                            double width = TPoints[4]-TPoints[2];
                            double X = TPoints[0];
                            double Y = TPoints[1]+height/2;
                            double centerX = TPoints[0];
                            double centerY = TPoints[3] - TPoints[1];
                            double deltaX = event.getX() - centerX;
                            double deltaY = event.getY() - centerY;
                            double startAngle = Math.atan2(RstartY - centerY, RstartX - centerX);
                            double newAngle = Math.atan2(deltaY, deltaX);
                            double angle = Math.toDegrees(newAngle - startAngle);
                            rotate.setPivotX(X+(width/2));
                            rotate.setPivotY(Y+(height/2));
                            t.setRotate(t.getRotate() + angle);

                            RstartX = event.getX() - t.getTranslateX();
                            RstartY = event.getY() - t.getTranslateY();
                        }
                    });
                    scena.setOnMouseReleased(event->{
                        t.setStroke(Color.BLACK);
                    });
                }
                if (e.getCode() == KeyCode.T){
                    t.setStroke(Color.PURPLE);
                    scena.setOnMousePressed(event->{
                        if (!t.getStroke().equals(Color.PURPLE)) t.setStroke(Color.PURPLE);
                        if(event.getButton() == MouseButton.SECONDARY){
                            translate = new Translate();
                            TstartX = event.getX();
                            TstartY = event.getY();
                        }
                    });
                    scena.setOnMouseDragged(event ->{
                        if(event.getButton() == MouseButton.SECONDARY){
                            TendX = event.getX();
                            TendY = event.getY();
                            t.setTranslateX((TendX-TstartX));
                            t.setTranslateY((TendY - TstartY));
                        }
                    });
                    scena.setOnMouseReleased(event ->{
                        t.setStroke(Color.BLACK);
                        for (int i=0; i<6; i+=2) {
                            points.set(i,points.get(i) + t.getTranslateX());
                            points.set( i+1,points.get(i + 1) + t.getTranslateY());
                        }
                        t.setTranslateX(0);
                        t.setTranslateY(0);
                    });
                }
            }
        });
    }
}