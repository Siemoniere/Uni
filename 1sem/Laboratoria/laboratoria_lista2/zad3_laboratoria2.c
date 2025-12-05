//Szymon Hładyszewski zadanie 3 lista 2
#include <stdio.h>
#include <math.h>

int main()
{
	double przyblizenie;
	double suma;
	for (int i=1; i<=1000; i++)
	{
		suma+=log(i);
	}
	suma=suma/1000;
	przyblizenie=exp(suma);
	printf ("Przyblizenie wynosi %lf\n", przyblizenie);

return 0;
}	
