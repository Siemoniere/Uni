#include <iostream>
#include "funkcje.hpp"
#include <vector>

using namespace std;

LiczbyPierwsze::LiczbyPierwsze(int przedzial){
    for (int i=1;i<przedzial;i++){
        if (czyPierwsza(i)) tab.push_back(i);
    }
}
LiczbyPierwsze::~LiczbyPierwsze(){

}

bool LiczbyPierwsze::czyPierwsza (int n){
    for (int i=2;i<n;i++){
        if (n%i==0) return false;
    }
    return true;
}
int LiczbyPierwsze::liczba (int m){
    if (m>=0 && m<tab.size()) return tab[m];
    else return -1;
}