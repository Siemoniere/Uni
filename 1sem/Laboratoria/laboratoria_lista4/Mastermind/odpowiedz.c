//Szymon Hładyszewski
#include <stdio.h>
#include <stdlib.h>
#include "funs.h"
void odpowiedz(int liczba){
	int rozbite[4]; 
	rozbicie(liczba, rozbite);
	for(int i=0; i<4; i++)
		printf("[%d]", rozbite[i]);
	printf("\n");
}
