import java.util.ArrayList;
public class Rysowanie {
    
    int n;
    ArrayList<Long> tab = new ArrayList<>();

    Rysowanie (int n){
        utworzTablice(n);
        }

    void utworzTablice(int n){
        for (int k=0;k<=n;k++){
            long wynik= silnia(n)/(silnia(k)*silnia(n-k));
            if(wynik<=0) return;
            tab.add(wynik);
        }
    }

    long element (int m){
        if (m>=0&&m<tab.size()){
            return tab.get(m);
        } else return -1;
    }

    long silnia (int x){
        long wynik=1;
        for (int i=1;i<=x;i++){
            wynik*=i;
        }
        return wynik;
    }
}