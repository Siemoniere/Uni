#include <iostream>
#include <vector>

using namespace std;

class TrojkatPascala{
    int wiersz;
    vector <int> tab;


    public:
    int silnia(int n);
    int element (int ktory);
    void utworzTab(int n);
    TrojkatPascala (int wiersz);
    ~TrojkatPascala();
};