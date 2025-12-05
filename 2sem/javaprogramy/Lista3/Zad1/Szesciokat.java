public class Szesciokat extends Figura{

    double bok;
    Szesciokat(String nazwa, double bok){
        super(nazwa);
        this.bok=bok;
    }
    @Override
    public String Nazwa(){
        return "Sześciokąt";
    }
    @Override
    public double LiczObwód(){
        return 6*bok;
    }
    @Override
    public double LiczPole(){
        return 3*bok*bok*Math.sqrt(3)/2;
    }
}