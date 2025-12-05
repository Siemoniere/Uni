public class NajwiekszyDzielnik {
    public static void main(String[] args) {
        for(int i = 0; i < args.length; i++) {
            try {
                int n = Integer.parseInt(args[i]);
                int dzielnik;
                if (n>0) {
                dzielnik = div(n);
                System.out.println(n + " " + dzielnik);
                } else { 
                    dzielnik = div(-n);
                    System.out.println(n + " " + dzielnik);


                }
            } catch (NumberFormatException ex) {
                System.out.println(args[i] + " nie jest liczba calkowita");
            }
        }
    }

    public static int div(int n) {
        if (n <= 1) {
            return n;
        }
        for (int i = n / 2; i >= 1; i--) {
            if (n % i == 0) {
                return i;
            }
        }
        return 1;
    }
}
