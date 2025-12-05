#include<iostream>
#include<string>
#include"Wszystko.hpp"
using namespace std;

int main(int argc, char* argv[]) {
        if (argc<3){
            cout<<"Za mało danych"<<endl;
            return 0;
        }
        for (int i=2;i<argc;i++){
            try { 
                double n=stod(argv[i]);
                if (n<0.0) {
                    cout<<"Podano ujemną liczbę"<<endl;
                    return 0;
                }
            }
            catch (invalid_argument const &e) {
                cout<<argv[i]<<" to nie jest liczba"<<endl;
                return 0;
            }
        } 
        char* figura= argv[1];
        double obwod, pole, bok;
        string nazwa;
        switch (*figura){
            case 'o':{
                if (!(argc==3)) {
                    cout<<"Zła ilość danych"<<endl;
                    return 0;
                }
                double r= stod(argv[2]);
                Kolo kolo("koło", r);
                nazwa=kolo.Nazwa();
                obwod=kolo.LiczObwód();
                pole=kolo.LiczPole();
                cout<<"Pole figury "<<nazwa<<" wynosi "<<pole<<", a obwód wynosi "<<obwod<<endl;
                break;
            }
            case 'p':{
                if (!(argc==3)) {
                    cout<<"Zła ilość danych"<<endl;
                    return 0;
                }
                bok= stod(argv[2]);
                Pieciokat pieciokat("pięciokąt", bok);
                nazwa=pieciokat.Nazwa();
                obwod=pieciokat.LiczObwód();
                pole=pieciokat.LiczPole();
                cout<<"Pole figury "<<nazwa<<" wynosi "<<pole<<", a obwód wynosi "<<obwod<<endl;
                break;
            }
            case 's':{
                if (!(argc==3)) {
                    cout<<"Zła ilość danych"<<endl;
                    return 0;
                }
                bok= stod(argv[2]);
                Szesciokat szesciokat("sześciokąt", bok);
                nazwa=szesciokat.Nazwa();
                obwod=szesciokat.LiczObwód();
                pole=szesciokat.LiczPole();
                cout<<"Pole figury "<<nazwa<<" wynosi "<<pole<<", a obwód wynosi "<<obwod<<endl;
                break;
            }
            case 'c':{
                if (!(argc==7)) {
                    cout<<"Zła ilość danych"<<endl;
                    return 0;
                }
                double bok1= stod(argv[2]);
                double bok2= stod(argv[3]);
                double bok3= stod(argv[4]);
                double bok4= stod(argv[5]);
                double kat= stod(argv[6]);
                
                if (!(0<kat&&kat<180)){
                    cout<<"Podano niepoprawny kąt"<<endl;
                    return 0;
                }
                if (!(kat==90)){
                    Romb romb("romb", bok1, bok2, bok3, bok4, kat);
                    nazwa=romb.Nazwa();
                    obwod=romb.LiczObwód();
                    pole=romb.LiczPole();
                    cout<<"Pole figury "<<nazwa<<" wynosi "<<pole<<", a obwód wynosi "<<obwod<<endl;
                }else if(bok1==bok2&&bok3==bok4&&bok1==bok3){
                    Kwadrat kwadrat("kwadrat", bok1, bok2, bok3, bok4, kat);
                    nazwa=kwadrat.Nazwa();
                    obwod=kwadrat.LiczObwód();
                    pole=kwadrat.LiczPole();
                    cout<<"Pole figury "<<nazwa<<" wynosi "<<pole<<", a obwód wynosi "<<obwod<<endl;
                } else{
                    Prostokat prostokat("prostokąt", bok1, bok2, bok3, bok4, kat);
                    nazwa=prostokat.Nazwa();
                    obwod=prostokat.LiczObwód();
                    pole= prostokat.LiczPole();
                    cout<<"Pole figury "<<nazwa<<" wynosi "<<pole<<", a obwod wynosi "<<obwod<<endl;
                }
                break;
            }
            default:{
                cout<<"Nie ma takiej figury"<<endl;
            }
        }
    return 0;
}