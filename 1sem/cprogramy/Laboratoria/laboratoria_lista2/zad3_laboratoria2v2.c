//Szymon Hładyszewski zadanie 3 lista 2
#include <stdio.h>
#include <math.h>

int main()
{
	double przyblizenie=1.0;
	double n=1.0;
	for (int i=1; i<=1000; i++)
	{
	n =pow(i, 1.0/1000.0);
        przyblizenie*=n;
	}
	printf ("Przyblizenie wynosi %lf\n", przyblizenie);

return 0;
}	
