#include <stdio.h>
#include "funs.h"
int main ()
{
	long int n;
	printf("Podaj liczbe naturalna\n");
	scanf ("%ld", &n);
	int wynik= phi(n);
	printf("Wynik funkcji Eulera wynosi %d\n", wynik);
	return 0;}
