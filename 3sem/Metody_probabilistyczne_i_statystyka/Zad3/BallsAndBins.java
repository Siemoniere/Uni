import org.apache.commons.math3.random.MersenneTwister;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BallsAndBins {

    public static void main(String[] args) {
        MersenneTwister random = new MersenneTwister();
        StringBuilder result = new StringBuilder();
        for (int i = 10000; i <=1000000; i+=10000){
            System.out.println(i);
            for(int k = 1; k<=50; k++){
                int bins[] = new int[i];
                int greatest = 0;
                for (int count = 0; count < i; count++){
                    int number = random.nextInt(i);
                    bins[number]++;
                    if(greatest < bins[number]){
                        greatest = bins[number];
                    }
                }
                result.append(i).append(";").append(greatest).append("\n");
            }
        }
        String path ="C:/Users/Admin/Documents/Metody-probabilistyczne-i-statystyka/Zad3/wyniki.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(result.toString());
            System.out.println("Dane zostały zapisane do pliku CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
