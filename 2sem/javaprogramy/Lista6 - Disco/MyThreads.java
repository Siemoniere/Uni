import javafx.scene.shape.Rectangle;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.application.Platform;
/**
* Klasa implementująca interfejs Runnable do obsługi zmiany kolorów prostokątów w osobnych wątkach.
*/ 
public class MyThreads implements Runnable{
    private Rectangle rectangle;
    private Random rand;
    private int i, j;
    private double k;
    private double p;
    private Rectangle[][] allrecs;
    private boolean[][] isFrozen;
    private volatile boolean isFreezed = false;   
    /**
     * Konstruktor inicjalizujący wątek z parametrami potrzebnymi do operacji na prostokątach.
     * @param rectangle Prostokąt, który jest obsługiwany przez wątek.
     * @param rand Instancja Random do losowania kolorów.
     * @param allrecs Dwuwymiarowa tablica wszystkich prostokątów.
     * @param i Indeks wiersza aktualnie obsługiwanego prostokąta.
     * @param j Indeks kolumny aktualnie obsługiwanego prostokąta.
     * @param p Prawdopodobieństwo zmiany koloru.
     * @param k Czynnik wpływający na interwał czasowy pomiędzy zmianami koloru.
     * @param isFrozen Tablica wskazująca, które prostokąty zostały 'zamrożone' (nieaktywne).
     */
    public MyThreads(Rectangle rectangle, Random rand, Rectangle [][] allrecs, int i, int j, double p, long k, boolean[][] isFrozen){
        this.rectangle = rectangle;
        this.rand = rand;
        this.i = i;
        this.j = j;
        this.p = p;
        this.k = k;
        this.allrecs = allrecs;
        this.isFrozen = isFrozen;
        freeze();
    }
    /**
     * Główna metoda wątku, zarządza cykliczną zmianą kolorów prostokątów.
     */
    public void run(){
        while(true){
            if(!isFreezed){
                System.out.println("Zaczynam zmieniać kolor pola: " + i + ", " + j);
                double choose = rand.nextDouble();
                if (p>=choose){
                    Color newColor = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1.0);
                    Platform.runLater(() -> rectangle.setFill(newColor));
                } else {
                    Platform.runLater(() -> changeColor(i, j, allrecs));        
                }
                System.out.println("Kończę zmieniać kolor pola: " + i + ", " + j);
                try{
                    double parameter = rand.nextDouble(1)+0.5;
                    double almostreadyparameter = k*parameter;
                    long readyparameter = (long) almostreadyparameter;
                    Thread.sleep(readyparameter);
                } catch (Exception e){
                    return;
                }
            }
        }
    }
    /**
     * Funkcja obliczająca indeks wiersza z uwzględnieniem warunków brzegowych torusa.
     * @param i Indeks wiersza.
     * @return Znormalizowany indeks wiersza.
     */
    public int torusI(int i){
        int rows = allrecs.length;
        return (i+rows)%rows;
    }
    /**
     * Funkcja obliczająca indeks kolumny z uwzględnieniem warunków brzegowych torusa.
     * @param i Indeks wiersza.
     * @param j Indeks kolumny.
     * @return Znormalizowany indeks kolumny.
     */
    public int torusJ(int i, int j){
        int columns = allrecs[torusI(i)].length;
        return (j+columns)%columns;
    }
    /**
     * Metoda do zmiany koloru prostokąta na podstawie kolorów sąsiednich prostokątów.
     * @param i Indeks wiersza prostokąta do zmiany.
     * @param j Indeks kolumny prostokąta do zmiany.
     * @param allrecs Tablica wszystkich prostokątów.
     */
    public void changeColor(int i, int j, Rectangle[][] allrecs) {
        synchronized(allrecs) {
            Paint[] fill = new Paint[4];
            fill[0] = allrecs[torusI(i-1)][torusJ(i-1, j)].getFill();
            fill[1] = allrecs[torusI(i)][torusJ(i, j-1)].getFill();
            fill[2] = allrecs[torusI(i+1)][torusJ(i+1, j)].getFill();
            fill[3] = allrecs[torusI(i)][torusJ(i, j+1)].getFill();
            Color [] color = new Color[4];
            color[0] = (Color) fill[0];
            color[1] = (Color) fill[1];
            color[2] = (Color) fill[2];
            color[3] = (Color) fill[3];
            double count = 0;
            double averageBlue = 0.0;
            double averageGreen = 0.0;
            double averageRed = 0.0;

            for (int k = 0; k < 4; k++) {
                if (!(isFrozen[torusI(i-1)][torusJ(i-1, j)])) {
                    count++;
                    averageRed += color[0].getRed();
                    averageGreen += color[0].getGreen();
                    averageBlue += color[0].getBlue();
                }
                if (!(isFrozen[torusI(i)][torusJ(i, j-1)])) {
                    count++;
                    averageRed += color[1].getRed();
                    averageGreen += color[1].getGreen();
                    averageBlue += color[1].getBlue();
                }if (!(isFrozen[torusI(i+1)][torusJ(i+1, j)])) {
                    count++;
                    averageRed += color[2].getRed();
                    averageGreen += color[2].getGreen();
                    averageBlue += color[2].getBlue();
                }if (!(isFrozen[torusI(i)][torusJ(i, j+1)])) {
                    count++;
                    averageRed += color[3].getRed();
                    averageGreen += color[3].getGreen();
                    averageBlue += color[3].getBlue();
                }
            }
            if (count > 0) { 
                averageRed /= count;
                averageGreen /= count;
                averageBlue /= count;
                rectangle.setFill(new Color(averageRed, averageGreen, averageBlue, 1.0));
            }
        }
    }
    /**
     * Metoda umożliwiająca 'zamrożenie' interakcji z prostokątem przez użytkownika.
     */
    public void freeze(){
        rectangle.setOnMouseClicked(e->{
            //rectangle.setFill(Color.BLACK);
            isFreezed = !isFreezed;
            isFrozen[i][j] =!isFrozen[i][j];
        });
    }
}