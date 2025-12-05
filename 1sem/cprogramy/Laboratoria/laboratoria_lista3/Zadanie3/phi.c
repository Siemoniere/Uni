#include <stdio.h>
#include "funs.h"

int phi (long int n)
{	int temp, m, a=0;
	for (int i=1;i<=n;i++){
		temp=i;
		m=n;
		int j=i;
		while (j!=0){
			temp=m;
			m=j;
			j=temp%j;
		}
		if(m==1) a+=1;
	}
 return a;
}
