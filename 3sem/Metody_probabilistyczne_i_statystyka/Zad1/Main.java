import org.apache.commons.math3.random.MersenneTwister;
import java.io.FileWriter;
import java.io.IOException;

class Points {
    double x;
    double y;

    public Points(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Integral {
    int a;
    double b;
    double M;
    String name;

    public Integral(int a, double b, double M, String name) {
        this.a = a;
        this.b = b;
        this.M = M;
        this.name = name;
    }

    public double calculateFunction(double x) {
        switch (name) {
            case "first":
                return Math.cbrt(x);
            case "second":
                return Math.sin(x);
            default:
                return 4 * x * Math.pow(1 - x, 3);
            }
    }
}

public class Main {
    public static void main(String[] args) {
        Integral[] integrals = {
            new Integral(0, 8, Math.cbrt(8), "first"),
            new Integral(0, Math.PI, Math.sin(Math.PI / 2), "second"),
            new Integral(0, 1, 27.0 / 64.0, "third")
        };

        int[] k = {5, 50};
        for (Integral integral : integrals) {
            for (int ki : k) {
                approx(integral, ki);
            }
        }
        for (int ki : k) {
            approxPi(ki);
        }
    }

    public static void approx(Integral integral, int ki) {
        StringBuilder resultData = new StringBuilder();
        StringBuilder averageData = new StringBuilder();

        for (int n = 50; n <= 5000; n += 50) {
            double average = 0;

            for (int k = 0; k < ki; k++) {
                Points[] points = new Points[n];
                int under = 0;
                MersenneTwister random = new MersenneTwister();

                for (int i = 0; i < n; i++) {
                    points[i] = new Points(random.nextDouble() * (integral.b - integral.a) + integral.a, random.nextDouble() * integral.M);
                    if (points[i].y <= integral.calculateFunction(points[i].x)) {
                        under++;
                    }
                }

                double area = (integral.b - integral.a) * integral.M;
                double result = under * area / n;
                average += result;

                resultData.append(n).append(";")
                          .append(Double.toString(result).replace('.', ','))
                          .append("\n");
            }

            average /= ki;
            averageData.append(n).append(";")
                       .append(Double.toString(average).replace('.', ','))
                       .append("\n");
        }

        String resultPath = "C:\\Users\\Admin\\OneDrive\\Dokumenty\\Semestr 3\\Metody probabilistyczne i statystyka\\Zad1\\" + integral.name + "_results_each_result_k" + ki + ".csv";
        String averagePath = "C:\\Users\\Admin\\OneDrive\\Dokumenty\\Semestr 3\\Metody probabilistyczne i statystyka\\Zad1\\" + integral.name + "_averages_k" + ki + ".csv";

        try (FileWriter result = new FileWriter(resultPath);
             FileWriter average = new FileWriter(averagePath)) {

            result.write(resultData.toString());
            average.write(averageData.toString());

            System.out.println("Zapisano dane do plików CSV dla " + integral.name + ", k = " + ki + ".");
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas zapisywania do pliku dla " + integral.name + ", k = " + ki + ": " + e.getMessage());
        }
    }

    public static void approxPi(int ki) {
        StringBuilder resultData = new StringBuilder();
        StringBuilder averageData = new StringBuilder();

        for (int n = 50; n <= 5000; n += 50) {
            double average = 0;

            for (int k = 0; k < ki; k++) {
                int inside = 0;
                MersenneTwister random = new MersenneTwister();

                for (int i = 0; i < n; i++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();
                    if (x * x + y * y <= 1) {
                        inside++;
                    }
                }

                double result = 4.0 * inside / n;
                average += result;

                resultData.append(n).append(";")
                          .append(Double.toString(result).replace('.', ','))
                          .append("\n");
            }

            average /= ki;
            averageData.append(n).append(";")
                       .append(Double.toString(average).replace('.', ','))
                       .append("\n");
        }

        String resultPath = "C:\\Users\\Admin\\OneDrive\\Dokumenty\\Semestr 3\\Metody probabilistyczne i statystyka\\Zad1\\pi_approximation_results_each_result_k" + ki + ".csv";
        String averagePath = "C:\\Users\\Admin\\OneDrive\\Dokumenty\\Semestr 3\\Metody probabilistyczne i statystyka\\Zad1\\pi_approximation_averages_k" + ki + ".csv";

        try (FileWriter result = new FileWriter(resultPath);
             FileWriter average = new FileWriter(averagePath)) {

            result.write(resultData.toString());
            average.write(averageData.toString());

            System.out.println("Zapisano dane do plików CSV dla aproksymacji pi, k = " + ki + ".");
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas zapisywania do pliku dla aproksymacji pi, k = " + ki + ": " + e.getMessage());
        }
    }
}
