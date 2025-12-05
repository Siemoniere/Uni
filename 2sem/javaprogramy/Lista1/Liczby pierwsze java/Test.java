public class Test {

    public static void main(String[] args){

        int n =0;
        try {
            n=Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            System.out.println("Przedział "+args[0]+ " nie jest liczbą");
            return;
        }
        if (n<0){
            System.out.println("Liczba "+n+" nie jest poprawnym przedziałem");
            return;
        }
        LiczbyPierwsze lPierwsze = new LiczbyPierwsze(n);

        for (int i=1; i<args.length;i++){
            int ktora=0;
            try {
                ktora=Integer.parseInt(args[i]); 
                   int rozmiar = lPierwsze.getRozmiar();
            if (ktora<1 || ktora>=rozmiar) {
                System.out.println(ktora+" Liczba poza zakresem");
            } else System.out.println(args[i]+" "+lPierwsze.liczba(ktora));
            }
            catch (NumberFormatException ex) {
                System.out.println(args[i] + " Nie jest liczbą");
               
            }
         
        }
    }
}
