//Szymon Hładyszewski
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "funs.h"
int main(){
	int mozliwosci[1296]={0};
	for(int i=0; i<1296; i++)
		mozliwosci[i]=(i/216+1)*1000+((i/36)%6+1)*100+((i/6)%6+1)*10+i%6+1;
	int czerwone=0, biale=0, liczba=1111;
	while(czerwone!=4){
		odpowiedz(liczba);
		pytanie(&biale, &czerwone);
		odrzuc(mozliwosci,liczba, czerwone, biale);
		int licz=0;
		while(mozliwosci[licz]!=0&&licz<1296){
			licz++;
		}
		if(licz==0){ 
			printf("Oszukujesz!\n"); 
			return 0;
		}
		srand(time(NULL));
		liczba=mozliwosci[rand()%licz];
	}	
	printf("Zgadłem!\n");
    return 0;
}
