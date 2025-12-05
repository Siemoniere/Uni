//Szymon Hładyszewski zadanie 2 lista 1
#include <stdio.h>
#include <math.h>
int main () {
	printf ("Podaj trzy wspolczynniki, pamietaj aby nie wprowadzac a=0\n");
	float a, b, c;
	scanf ("%f" , &a);
	scanf ("%f" , &b);
	scanf ("%f" , &c);
	float d=b*b-4*a*c;
	float x, y, z;
       if(d<0){
	       printf ("Funkcja nie ma rozwiazan");
	       return 0;}
       x=-b/(2*a);
       y=(-b+sqrt(d))/(2*a);
       z=(-b-sqrt(d))/(2*a);
 if (d==0){
	 printf ("Funkcja ma jedno rozwiazanie rowne %f.\n", x);
	 return 0;}
       if (d>0){
	       printf ("Funkcja ma dwa rozwiazania rowne %f oraz %f.\n", y, z);
	      return 0;}
       return 0;}
