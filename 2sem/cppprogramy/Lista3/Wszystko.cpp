#include<iostream>
#include <string>
#include<cmath>
#include "Wszystko.hpp"
using namespace std;

string Szesciokat::Nazwa(){
    return "Sześciokąt";
}
double Szesciokat::LiczObwód(){
    return 6*bok;
}
double Szesciokat::LiczPole(){
    return 3*bok*bok*sqrt(3)/2;
}
string Pieciokat::Nazwa(){
    return "Pięciokąt";
}
double Pieciokat::LiczObwód(){
    return 5*bok;
}
double Pieciokat::LiczPole(){
    return bok*bok*sqrt(25+10*sqrt(5))/4;
}
string Kolo::Nazwa(){
    return "Koło";
}
double Kolo::LiczObwód(){
    return 2*M_PI*r;
}
double Kolo::LiczPole(){
    return M_PI*r*r;
}
string Kwadrat::Nazwa(){
    return "Kwadrat";
}
double Kwadrat::LiczObwód(){
    return 4*bok1;
}
double Kwadrat::LiczPole(){
    return bok1*bok1;
}
string Prostokat::Nazwa(){
    return "Prostokąt";
}
double Prostokat::LiczObwód(){
    if(bok1==bok2) return 2*bok1+2*bok3;
    else return 2*bok1+2*bok2;
}
double Prostokat::LiczPole(){
    if (bok1==bok2) return bok1*bok3;
        else return bok1*bok2;
}
string Romb::Nazwa(){
    return "Romb";
}
double Romb::LiczObwód(){
    return 4*bok1;
}
double Romb::LiczPole(){
    return bok1*bok1*sin(kat*M_PI/180);
}