public class Pieciokat extends Figura{

    double bok;
    Pieciokat(String nazwa, double bok){
        super(nazwa);
        this.bok=bok;
    }
    @Override
    public String Nazwa(){
        return "Pięciokąt";
    }
    @Override
    public double LiczObwód(){
        return 5*bok;
    }
    @Override
    public double LiczPole(){
        return bok*bok*Math.sqrt(25+10*Math.sqrt(5))/4;
    }
}