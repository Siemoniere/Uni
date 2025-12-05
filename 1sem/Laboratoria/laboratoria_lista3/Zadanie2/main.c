#include <stdio.h>
#include "funs.h"

int main ()
{
	double a, b, eps;
	printf("Podaj poczatek przedzialu\n");
	scanf ("%lf", &a);
	printf("Podaj koniec przedzialu\n");
	scanf("%lf", &b);
	printf("Podaj epsilon\n");
	scanf("%lf", &eps);
	double rozw=rozwiazanie(a, b, eps);
	printf("Rozwiazanie wynosi %lf\n", rozw);
	return 0;
}
