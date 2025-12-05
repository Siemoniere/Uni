import java.util.ArrayList;
public class WierszTrójkątPascala {
    
    long n;
    ArrayList<Long> tab = new ArrayList<>();

    WierszTrójkątPascala (long n){
        utworzTablice(n);
        }

    void utworzTablice(long n){
        for (long k=0;k<=n;k++){
            long wynik= silnia(n)/(silnia(k)*silnia(n-k));
            tab.add(wynik);
        }
    }

    long element (int m){
        if (m>=0&&m<tab.size()){
            return tab.get(m);
        } else return -1;
    }

    long silnia (long x){
        long wynik=1;
        for (long i=1;i<=x;i++){
            wynik*=i;
        }
        return wynik;
    }
}