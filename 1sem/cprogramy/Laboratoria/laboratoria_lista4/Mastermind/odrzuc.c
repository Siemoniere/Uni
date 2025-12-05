//Szymon Hładyszewski
#include "funs.h"
void odrzuc(int *mozliwosci, int odpowiedz, int czerwone, int biale) {
	int i=0, rozbiteOdpowiedz[4], rozbite[4];
	while(mozliwosci[i]!=0 && i<1296){
		int c=0, b=0;
		rozbicie(odpowiedz, rozbiteOdpowiedz);
		rozbicie(mozliwosci[i], rozbite);
		porownaj(rozbiteOdpowiedz, rozbite, &c, &b);
		if(!(c==czerwone&&b==biale)) mozliwosci[i]=0;
		i++;
	}
	sortuj(mozliwosci);
}

