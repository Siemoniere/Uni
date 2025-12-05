import java.util.ArrayList;
public class TrojkatPascala {
    
    int n;
    ArrayList<Integer> tab = new ArrayList<>();

    TrojkatPascala (int n){
        utworzTablice(n);
        }

    void utworzTablice(int n){
        for (int k=0;k<=n;k++){
            int wynik= silnia(n)/(silnia(k)*silnia(n-k));
            tab.add(wynik);
        }
    }

    int element (int m){
        if (m>=0&&m<tab.size()){
            return tab.get(m);
        } else return -1;
    }

    int silnia (int x){
        int wynik=1;
        for (int i=1;i<=x;i++){
            wynik*=i;
        }
        return wynik;
    }
}