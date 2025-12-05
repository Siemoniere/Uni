//Szymon Hładyszewski zadanie 5 lista 2
#include <stdio.h>
int suma_dzielnikow (int x)
{   int suma=0;
    for (int i=x-1; i>0;i--)
    {
        if (x%i==0) suma+=i;
    }
    return suma;}
int main ()
{
    printf ("Nastepujace liczby to wszystkie liczby doskonałe mniejsze niz 1000:\n");
    int suma=0;
    for (int i=1; i<1000; i++)
    {
        suma_dzielnikow(i);
        if (suma_dzielnikow(i)==i) printf ("%d\n", i);
        suma=0;
    }
    printf ("Nastepujace pary liczb to wszystkie pary liczb zaprzyjaznionych mniejszych niz 1000:\n");
    for (int n=1; n<1000;n++)
    {
        int x=suma_dzielnikow(n);
        int y=suma_dzielnikow(x);
        if (n==y&&n!=x) printf ("%d, %d\n", n, x); 
        
     }

return 0;
}
