//Szymon Hładyszewski
#include "funs.h"
void porownaj(int* odpowiedz, int* strzal, int *czerwone, int* biale){
	for(int i=0; i<4; i++){
		if(odpowiedz[i]==strzal[i]){
			(*czerwone)++;
			odpowiedz[i]=0;
			strzal[i]=0;
		}
	}
	for(int i=0; i<4; i++){
		for(int j=0; j<4; j++){
			if(odpowiedz[i]==strzal[j]&&(odpowiedz[i]!=0&&strzal[j]!=0)){
				(*biale)++;
				odpowiedz[i]=0;
				strzal[j]=0;
				break;
			}
		}
	}
}

