public class Test {

    public static void main (String [] args){
        int wiersz =0;
        try {
            wiersz=Integer.parseInt(args[0]);
        } catch (NumberFormatException ex){
            System.out.println("Podany przedział "+args[0]+" nie jest liczbą");
            return;
        }
        if (wiersz<0){
            System.out.println("Podany przedział "+args[0]+" jest niepoprawny");
            return;
        }
        TrojkatPascala trojkat = new TrojkatPascala(wiersz);
        for (int i=1;i<args.length;i++){
            int ktory, wynik;
            try {
                ktory = Integer.parseInt(args[i]);
                wynik=trojkat.element(ktory);
            if (wynik==-1){
                System.out.println(args [i]+" Liczba poza zakresem");
            } else System.out.println(args[i]+" "+trojkat.element(ktory));
            } catch (NumberFormatException ex){
                System.out.println(args[i] + " nie jest liczbą");
            }
        }
    }
}