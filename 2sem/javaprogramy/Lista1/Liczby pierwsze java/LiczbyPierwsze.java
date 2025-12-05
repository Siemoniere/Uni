import java.util.ArrayList;
public class LiczbyPierwsze {

    int n;
    ArrayList<Integer> tab = new ArrayList<>();

    LiczbyPierwsze (int n){
        this.n=n;
        for (int i=1;i<=n;i++){
            if (jestPierwsza(i)) {
                tab.add(i);
            }
        }
    }

    private boolean jestPierwsza(int n){
        for (int i=2;i<n;i++){
            if (n%i==0) return false;
        }
        return true;
    }

        public int liczba(int m){
            if (m>0 && m<tab.size()) return tab.get(m);
            else return 0;
        }

        int getRozmiar (){
            return tab.size();
        }
    }

