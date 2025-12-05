#include <iostream>
#include <vector>

using namespace std;

class LiczbyPierwsze{

    int n;

    public:
    vector<int> tab;
    LiczbyPierwsze(int przedzial);
    ~LiczbyPierwsze();
    int liczba(int m);
    bool czyPierwsza(int n);
};