#include <stdio.h>
#include "funs.h"
#include <string.h>
bool palindrom (char napis[]) {
	int a=0;
	for (int i=0;i<(strlen(napis)/2);i++)
	{
		if (napis[i]==napis[strlen(napis)-1-i]) a+=1;
	}
	if (a==(strlen(napis)/2)) return 1;
	else return 0;
}
