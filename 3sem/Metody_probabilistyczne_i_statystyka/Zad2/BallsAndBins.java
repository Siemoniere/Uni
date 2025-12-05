import org.apache.commons.math3.random.MersenneTwister;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BallsAndBins {

    public static boolean atLeastTwo(int[] tab){
        for(int i = 0; i<tab.length; i++){
            if (tab[i] < 2){
                return false;
            }
        }
        return true;
    }

    public static boolean colision(int[] tab){
        for (int i=0; i<tab.length; i++){
            if (tab[i] > 1){
                return true;
            }
        }
        return false;
    }

    public static boolean atLeastOne (int[] tab){
        for (int i = 0; i<tab.length; i++){
            if (tab[i] < 1){
                return false;
            }
        }
        return true;
    }

    public static int countEmpty (int[] tab){
        int count = 0;
        for (int i = 0; i<tab.length; i++){
            if (tab[i] == 0){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MersenneTwister random = new MersenneTwister();
        StringBuilder result = new StringBuilder();
        for (int i = 1000; i <=100000; i+=1000){
            System.out.println(i);
            for(int k = 1; k<=50; k++){
                int bins[] = new int[i];
                for (int j = 0; j < i; j++){
                    bins[j] = 0;
                }
                int count = 0;
                boolean colisionExisted = false;
                boolean isAtLeastOne = false;
                int bn=0, cn=0, un=0, dn=0;
                while(!(atLeastTwo(bins))){
                    int number = random.nextInt(i);
                    bins[number]++;
                    if (!colisionExisted && colision(bins)){
                        bn = count;
                        colisionExisted = true;
                    }
                    if (!isAtLeastOne && atLeastOne(bins)){
                        cn = count;
                        isAtLeastOne = true;
                    }
                    if(count == i){
                        for(int n=0; n<i; n++){
                            if (bins[n] == 0){
                                un++;
                            }
                        }
                    }
                    count++;
                }
                dn = count;
                result.append(i).append(";").append(bn).append(";").append(un)
                .append(";").append(cn).append(";").append(dn).append(";")
                .append(dn-cn).append("\n");
            }
        }
        String path = "/home/user/Programowanie/3sem/MPiS/plik.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(result.toString());
            System.out.println("Dane zostały zapisane do pliku CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
