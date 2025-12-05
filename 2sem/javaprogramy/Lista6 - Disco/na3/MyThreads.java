import javafx.scene.shape.Rectangle;
import java.util.Random;
import javafx.scene.paint.Color;

public class MyThreads implements Runnable{
    private Rectangle rectangle;
    private Random rand;

    public MyThreads(Rectangle rectangle, Random rand){
        this.rectangle = rectangle;
        this.rand = rand;
    }

    public void run(){
        while(true){
            int p = rand.nextInt(5);
            long k=500;
            double parameter = rand.nextDouble(1)+0.5;
            long readyparameter = k*Math.round(parameter*100)/100;
            if (p==0){
                rectangle.setFill(new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1.0));
            }
            try{
                Thread.sleep(readyparameter);
            } catch (Exception e){}
        }
    }
}