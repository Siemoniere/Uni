import org.apache.commons.math3.random.MersenneTwister;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InsertionSort{

    public static void generatePermutation(int[] a, int n, MersenneTwister rand) {
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        //tasowanie Fishera-Yatesa
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    public static int[] insertionSort(int[] arr) {
        int n = arr.length;
        int cmp = 0;
        int s = 0;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                cmp++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    s++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return new int[]{cmp, s};
    }
    public static void main(String[] args) {
        MersenneTwister rand = new MersenneTwister();
        StringBuilder result = new StringBuilder();
        for (int n = 100; n <= 10000; n+=100){
            System.out.println(n);
            for (int k = 1 ; k <= 50; k++){
                int a[] = new int[n];
                generatePermutation(a, n, rand);
                int results[] = new int[2];
                results = insertionSort(a);
                result.append(n).append(";").append(results[0]).append(";").append(results[1]).append("\n");
            }
        }
        String path ="C:/Users/Admin/Documents/Metody-probabilistyczne-i-statystyka/Zad3/insertion.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(result.toString());
            System.out.println("Dane zostały zapisane do pliku CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}