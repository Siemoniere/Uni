import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
public class TrojkatPascalaGUIFX extends Application {

    BorderPane panel;
    TextField tekst;
    VBox box;
    public static void main(String args[]) {
        launch (args);
    }
    @Override
    public void start (Stage glowna){
        glowna.setHeight(700);
        glowna.setWidth(900);
        glowna.setTitle("Trójkąt Pascala FX");
        box = new VBox();
        panel = new BorderPane();
        Button przycisk = new Button("Generuj");
        tekst = new TextField();

        panel.setTop(tekst);
        panel.setBottom(przycisk);
        panel.setCenter(box);

        przycisk.setOnAction (e -> {
            generujTrojkat();
        });
        Scene scena = new Scene (panel);
        glowna.setScene(scena);
        glowna.show();
    }
    public void generujTrojkat(){
        try{
            box.getChildren().clear();
            long n = Long.parseLong(tekst.getText());
            for (int i=0; i<=n; i++){
                WierszTrójkątPascala wiersz = new WierszTrójkątPascala(i);
                String s="";
                for (int j=0; j<i+1;j++){
                    s+=wiersz.element(j)+ " ";
                }
                box.getChildren().add(new Label(s));
            }
            box.setAlignment(Pos.CENTER);
            panel.getChildren().add(box);
        }
        catch (NumberFormatException ex){
            System.out.println("Podano niepoprawną liczbę");
        }
    }
}