//Szymon Hładyszewski
#include "funs.h"
void sortuj(int* tablica){
	for(int i=0; i<1296; i++)
		for(int j=0; j<i; j++)
			if(tablica[i]>tablica[j]){
				int temp=tablica[i];
				tablica[i]=tablica[j];
				tablica[j]=temp;
			}
}
