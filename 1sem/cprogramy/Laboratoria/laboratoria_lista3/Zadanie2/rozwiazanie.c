#include <stdio.h>
#include <assert.h>
#include "funs.h"
double rozwiazanie (double a, double b, double eps){
	assert (a<b);
	assert (f(a)*f(b)<0);
	double c;
	while ((b-a)/2>eps){
		c=(a+b)/2;
		if (f(a)*f(c)<0) b=c;
		else a=c;
	}
	double wynik=(a+b)/2;
	return wynik;

}
