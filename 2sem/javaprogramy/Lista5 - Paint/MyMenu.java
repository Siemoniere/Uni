import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Klasa zarządzająca paskiem menu w aplikacji graficznej.
 *
 * @class MyMenu
 * @brief Zarządza elementami menu w aplikacji.
 * @param mainpane Główny panel, na którym umieszczony jest pasek menu.
 * @param drawingpane Panel rysunkowy, na którym tworzone są grafiki.
 * @param okno Główne okno aplikacji, w którym wyświetlane jest menu.
 */
public class MyMenu extends MenuBar {
    private Stage infoStage;
    private Scene infoScene;
    private TextArea infotext;

    /**
     * Konstruktor inicjujący menu i jego elementy.
     */
    public MyMenu(BorderPane mainpane, BorderPane drawingpane, Stage okno){
        MenuBar menubar = new MenuBar();
        Menu file = new Menu("Plik");
        Menu help = new Menu("Help");
        menubar.getMenus().addAll(file, help);
        MenuItem save = new MenuItem("Zapisz");
        MenuItem newfile = new MenuItem("Nowy");
        MenuItem info = new MenuItem("Info");
        help.getItems().add(info);
        file.getItems().addAll(newfile, save);
        mainpane.setTop(menubar);

        info.setOnAction(e->{
            infoStage = new Stage();
            infoStage.setTitle("Information");
            infoStage.setHeight(600);
            infoStage.setWidth(800);
            infotext = new TextArea();
            infoScene = new Scene(infotext);
            String text;
            text = "Nazwa: Edytor graficzny\nAutor: Szymon Hładyszewski\nPrzeznaczenie: Rysowanie różnych figur geometrycznych\nAktywacja i dezaktywacja: Aby aktywować wybraną figurę, należy kliknąć na nią lewym przyciskiem myszy. Aby dezaktywować ją, również należy kliknąć na nią W JEJ OBRĘBIE\nZmiana koloru: Aby zmienić kolor figury, należy ją najpierw aktywować, a następnie kliknąc PRAWYM przyciskiem myszy w jej obrębie\nRotowanie: Aby obrócić daną figurę, należy ją aktywować, następnie wcisnąć klawisz \"R\", a następnie przytrzymując PRAWYM przyciskiem myszy dowolnie obracać tę figurę\n Przemieszczanie: Aby przemieścić wybraną figurę, należy ją aktywować, a nastepnie wcisnąć \"T\", wówczas figura zmieni kolor na fioletowy. Można wtedy przemieszczać figurę poprzez przytrzymanie PRAWEGO przycisku myszy\nZmiana rozmiaru: Aby powiększać i pomniejszać wybraną figurę, należy ją najpierw aktywować, a następnie poprzez Scrolla dowolnie modyfikować";
            infotext.setText(text);
            infoStage.setScene(infoScene);
            infoStage.show();
        });
        newfile.setOnAction(e->{
            drawingpane.getChildren().clear();
        });
    }
}