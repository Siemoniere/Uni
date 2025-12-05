//Szymon Hładyszewski
#include "funs.h"
void rozbicie(int liczba, int rozbite[4]){
	int temp=0;
	for(int i=1000; i>=1; i/=10){
		rozbite[temp]=(liczba%(i*10)-liczba%i)/i;
		temp++;
	}
		
}

