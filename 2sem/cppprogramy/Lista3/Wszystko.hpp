#include<iostream>
#include<string>
using namespace std;

class Figura{
protected:
    string nazwa;
public:
    Figura (string nazwa):nazwa(nazwa){}
    ~Figura(){}
    virtual double LiczObwód()=0;
    virtual double LiczPole()=0;
    string Nazwa();
};

class Szesciokat :public Figura{
protected:
    double bok;
public:
    Szesciokat(string nazwa, double bok):Figura(nazwa), bok(bok){}
    double LiczObwód();
    double LiczPole();
    string Nazwa();
};

class Pieciokat: public Figura{
protected:
    double bok;
public:
    Pieciokat(string nazwa, double bok): Figura(nazwa), bok(bok) {}
    double LiczObwód();
    double LiczPole();
    string Nazwa();
};

class Kolo: public Figura {
protected:
    double r;
public:
    Kolo(string nazwa, double r): Figura(nazwa), r(r) {}
    ~Kolo(){}
    double LiczObwód();
    double LiczPole();
    string Nazwa();
};

class Czworokat : public Figura{
protected:
    double bok1;
    double bok2;
    double bok3;
    double bok4;
    double kat;
public:
    Czworokat(string nazwa, double bok1, double bok2, double bok3, double bok4, double kat): Figura(nazwa), bok1(bok1), bok2(bok2),bok3(bok3), bok4(bok4), kat(kat) {}
    ~Czworokat(){}
};

class Kwadrat : public Czworokat{
public:
    Kwadrat(string nazwa, double bok1, double bok2, double bok3, double bok4, double kat): Czworokat (nazwa, bok1, bok2, bok3, bok4, kat) {}
    double LiczObwód();
    double LiczPole();
    string Nazwa();
};

class Prostokat: public Czworokat{
public:
    Prostokat(string nazwa, double bok1, double bok2, double bok3, double bok4, double kat):Czworokat(nazwa, bok1, bok2, bok3, bok4, kat){}
    double LiczObwód();
    double LiczPole();
    string Nazwa();
};

class Romb: public Czworokat{
public:
    Romb(string nazwa, double bok1, double bok2, double bok3, double bok4, double kat): Czworokat(nazwa, bok1, bok2, bok3, bok4, kat) {}
    double LiczObwód();
    double LiczPole();
    string Nazwa();
};