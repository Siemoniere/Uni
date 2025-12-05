#include "funs.h"
#include <stdio.h>

int main(void)
{
	char napis[100];
	printf ("Podaj napis do 100 znakow\n");
	scanf ("%s", napis);
	if (palindrom(napis)==1) printf ("Jest to palindrom\n");
	else printf ("Nie jest to palindrom\n");
	return 0;
}

