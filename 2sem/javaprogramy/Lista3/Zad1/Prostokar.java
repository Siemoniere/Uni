public class Prostokar extends Czworokat{

    Prostokar(String nazwa, double bok1, double bok2, double bok3, double bok4, double kat){
        super(nazwa, bok1, bok2, bok3, bok4, kat);
    }
    @Override
    public String Nazwa(){
        return "Prostokąt";
    }
    @Override
    public double LiczObwód(){
        return bok1+bok2+bok3+bok4;
    }
    @Override
    public double LiczPole(){
        if (bok1==bok2) return bok1*bok3;
        else return bok1*bok2;
    }
}