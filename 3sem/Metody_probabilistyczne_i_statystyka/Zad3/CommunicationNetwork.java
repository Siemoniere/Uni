import org.apache.commons.math3.random.MersenneTwister;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CommunicationNetwork {

    public static boolean allTrue(boolean arr[]){
        for (boolean value : arr){
            if (!value){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        MersenneTwister rand = new MersenneTwister();
        double p[] = {0.1, 0.5};
        for (double pi : p){  
            for (int n = 100; n <= 10000 ; n += 100){
                for (int k = 1; k <= 50; k++){
                    System.out.println(n);
                    boolean arr[] = new boolean[n];
                    int count = 0;
                    while (!(allTrue(arr))){
                        for (int i = 0; i < n; i++){
                            if(arr[i] == false){
                                arr[i] = rand.nextDouble() < pi;
                            }
                        }
                        count++;
                    }
                    result.append(n).append(";").append(pi).append(";").append(count).append("\n");
                }
            }
        }
        String path ="C:/Users/Admin/Documents/Metody-probabilistyczne-i-statystyka/Zad3/communication.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(result.toString());
            System.out.println("Dane zostały zapisane do pliku CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}