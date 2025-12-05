import javafx.scene.shape.Rectangle;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.application.Platform;

public class MyThreads implements Runnable{
    private Rectangle rectangle;
    private Random rand;
    private int i, j;
    private double k;
    private double p;
    private Rectangle[][] allrecs;

    public MyThreads(Rectangle rectangle, Random rand, Rectangle [][] allrecs, int i, int j, double p, long k){
        this.rectangle = rectangle;
        this.rand = rand;
        this.i = i;
        this.j = j;
        this.p = p;
        this.k = k;
        this.allrecs = allrecs;
    }

    public void run(){
        while(true){
            double choose = rand.nextDouble();
            if (p>=choose){
                Color newColor = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1.0);
                Platform.runLater(() -> rectangle.setFill(newColor));
            } else {
                Platform.runLater(() -> changeColor(i, j, allrecs));        
            }
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
    public int torusI(int i){
        int rows = allrecs.length;
        return (i+rows)%rows;
    }
    public int torusJ(int i, int j){
        int columns = allrecs[torusI(i)].length;
        return (j+columns)%columns;
    }
    public void changeColor(int i, int j, Rectangle[][] allrecs){
        synchronized(allrecs){
            Paint fill1 = allrecs[torusI(i-1)][torusJ(i-1, j)].getFill();
            Paint fill2 = allrecs[torusI(i)][torusJ(i, j-1)].getFill();
            Paint fill3 = allrecs[torusI(i+1)][torusJ(i+1,j)].getFill();
            Paint fill4 = allrecs[torusI(i)][torusJ(i,j+1)].getFill();
            Color color1 = (Color) fill1;
            Color color2 = (Color) fill2;
            Color color3 = (Color) fill3;
            Color color4 = (Color) fill4;
            System.out.println("Zaczynam zmieniać kolor pola: " + i + ", " + j);
            double averageRed = (color1.getRed() + color2.getRed() + color3.getRed() + color4.getRed())/4;
            double averageGreen = (color1.getGreen() + color2.getGreen() + color3.getGreen() + color4.getGreen())/4;
            double averageBlue = (color1.getBlue() + color2.getBlue() + color3.getBlue() + color4.getBlue())/4;
            rectangle.setFill(new Color(averageRed, averageGreen, averageBlue, 1.0));
            System.out.println("Kończę zmieniać kolor pola: " + i + ", " + j);
        }
    }
}