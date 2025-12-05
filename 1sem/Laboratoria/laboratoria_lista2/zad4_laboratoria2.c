//Szymon Hładyszewski zadanie 4 lista 2
#include <stdio.h>

int licz_dni (int rok, int miesiac, int dzien)
{
    int ile_w_latach=0, ile_w_miesiacach=0;
    for (int i=1; i<rok;i++)
    {
        if (i%4==0 &&(i%100!=0||i%400==0)) ile_w_latach+=366;
        else ile_w_latach+=365;
    }
    for (int j=1; j<miesiac;j++)
    {
        if(j==1||j==3||j==5||j==7||j==8||j==10||j==12) ile_w_miesiacach+=31;
        else if ((rok%4==0&&(rok%100!=0||rok%400==0))&&j==2) ile_w_miesiacach+=29;
        else if ((rok%4!=0||(rok%100==0&&rok%400!=0))&&j==2) ile_w_miesiacach+=28;
        else if (j==4||j==6||j==9||j==11) ile_w_miesiacach+=30;
    }
       int ile_dni_lacznie=ile_w_latach+ile_w_miesiacach+dzien;
    return ile_dni_lacznie;
}
int main ()
{
    int rok, miesiac, dzien;
    int rok_urodzenia, miesiac_urodzenia, dzien_urodzenia;
    printf ("Podaj aktualny rok\n");
    scanf ( "%d", &rok);
    printf ("Podaj aktualny miesiac\n");
    scanf ("%d", &miesiac);
    printf ("Podaj aktualny dzien\n");
    scanf ("%d", &dzien);
    printf ("Podaj swoj rok urodzenia\n");
    scanf ("%d", &rok_urodzenia);
    printf ("Podaj swoj miesiac urodzenia\n");
    scanf ("%d", &miesiac_urodzenia);
    printf ("Podaj swoj dzien urodzenia\n");
    scanf ("%d", &dzien_urodzenia);
    int ile_dni_w_tej_e
    rze= licz_dni (rok, miesiac, dzien);
    printf ("Od poczatku naszej ery minelo %d dni\n", ile_dni_w_tej_erze);
    int dourodzin=licz_dni (rok_urodzenia, miesiac_urodzenia, dzien_urodzenia);
    int ile_dni_zyjesz=ile_dni_w_tej_erze-dourodzin;
    int sekundy=ile_dni_zyjesz*24*3600;
    printf ("Od dnia twoich urodzin minelo %d dni, czyli dokladnie %d sekund\n", ile_dni_zyjesz, sekundy);
    return 0;
}

