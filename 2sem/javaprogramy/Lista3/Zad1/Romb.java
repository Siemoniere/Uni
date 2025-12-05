public class Romb extends Czworokat{

    Romb(String nazwa, double bok1, double bok2, double bok3, double bok4, double kat){
        super(nazwa, bok1, bok2, bok3, bok4, kat);
    }
    @Override
    public String Nazwa(){
        return "Romb";
    }
    @Override
    public double LiczObwód(){
        return 4*bok1;
    }
    @Override
    public double LiczPole(){
        return bok1*bok1*Math.sin(kat);
    }
}