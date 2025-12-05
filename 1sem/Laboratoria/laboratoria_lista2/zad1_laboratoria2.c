//Szymon Hładyszewski zadanie 1 lista 2
#include <stdio.h>

int main ()
{
	int rok;
	printf ("Podaj wybrany rok\n");	
	scanf (" %d", &rok);
	if(rok%4==0 &&(rok%100!=0||rok%400==0))
		printf ("Podany rok jest przestepny\n");
	else 
		printf ("Podany rok jest zwykly\n");
return 0;

}
