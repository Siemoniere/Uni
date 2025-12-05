//Szymon Hładyszewski zadanie 2 lista 2
#include <stdio.h>

int main ()
{
	double  suma=0;
	int n=0;
	do {
	n+=1;
	suma=suma+1.0/n;
	} while (suma<=10);
	printf ("%d\n", n);	


	return 0;
}
