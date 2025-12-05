#include <iostream>
#include "funkcje.hpp"

using namespace std;

int main(int argc, char* argv[]){
    if (argc<2) {
        cout<<"Za mało danych"<<endl;
        return 0;
    }
    int argument;
    try {
        argument = stoi(argv[1]);
    } catch (invalid_argument const &e) {
        cout << "Przedział " << argv[1] << " nie jest liczbą" << endl;
        return 0;
    } if (argument<=0){
        cout << "Przedział " << argv[1] << " jest niepoprawny" << endl;
        return 0;
    }

    TrojkatPascala trojkat(argument);
    for (int i=2;i<argc;i++){
        int ktory, wynik;
        try {
            ktory= stoi(argv[i]);
            wynik=trojkat.element(ktory);
            if (wynik==-1) cout<<argv[i]<<" nie jest w przedziale"<<endl;
            else cout<<argv[i]<<" "<<wynik<<endl;
        } catch (invalid_argument const &e) {
            cout << argv[i] << " nie jest liczbą" << endl;
        }
    }
    return 0;
}