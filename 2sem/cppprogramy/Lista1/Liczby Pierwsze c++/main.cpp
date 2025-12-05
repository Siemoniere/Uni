#include <iostream>
#include "funkcje.hpp"

using namespace std;

int main (int argc, char* argv[]){
    if (argc<2) {
        cout<<"Za mało danych"<<endl;
        return 0;
    }
    int n =0;
    try {
        n = stoi(argv[1]);
    } catch (invalid_argument const &e) {
        cout << "Przedział " << argv[1] << " nie jest liczbą" << endl;
        return 0;
    } if (n<=0){
        cout << "Przedział " << argv[1] << " jest niepoprawny" << endl;
        return 0;
    }
    int ktory, wynik;
    LiczbyPierwsze liczbyPierwsze(n);
    for (int i=2;i<argc;i++){
        try {
            ktory = stoi(argv[i]);
             if (ktory<=0||ktory>=liczbyPierwsze.tab.size()) {
            cout  << argv[i] << " jest poza zakresem" << endl;
            }
            else {wynik = liczbyPierwsze.liczba (ktory);
        	cout<<argv[i]<<" "<<wynik<<endl;
        }
        } catch (invalid_argument const &e) {
            cout << argv[i] << " nie jest liczbą" << endl;
           
        } 
       
        
        
    }
    return 0;
}
