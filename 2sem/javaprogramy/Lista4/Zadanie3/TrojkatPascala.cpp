#include <iostream>
#include <vector>
#include "funkcje.hpp"

using namespace std;

TrojkatPascala::TrojkatPascala(int wiersz){
    utworzTab(wiersz);
}

TrojkatPascala::~TrojkatPascala(){

}

int TrojkatPascala::silnia (int n){
    int wynik=1;
    for (int i=1;i<=n;i++){
        wynik*=i;
    }
    return wynik;
}

void TrojkatPascala::utworzTab(int n){
    int wynik;
    for (int i=0;i<=n;i++){
        wynik=silnia(n)/(silnia(i)*silnia(n-i));
        tab.push_back(wynik);
    }
}

int TrojkatPascala::element(int ktory){
    if (ktory>=0&& ktory<tab.size()) return tab[ktory];
    else return -1;
}

