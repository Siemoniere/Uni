#include <stdio.h>

int main()
{
    int a;
    printf ("Podaj sposrod ilu z poczatkowych liczb naturalnych wypisac liczby pierwsze:\n");
    scanf ("%d",&a);
    int suma=0;
    for (int n=2;n<=a;n++)
    {
        for (int i=n-1; i>1; i--)
        {
            if (n%i==0) suma+=i;
        }
        if (suma==0) printf ("%d\n", n);
        suma=0;
    }
return 0;
}
