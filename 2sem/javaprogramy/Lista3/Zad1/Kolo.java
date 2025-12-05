public class Kolo extends Figura {

    double r;
    Kolo(String nazwa, double r){
        super(nazwa);
        this.r=r;
    }
    @Override
    public String Nazwa(){
        return "Koło";
    }
    @Override
    public double LiczObwód(){
        return 2*Math.PI*r;
    }
    @Override
    public double LiczPole(){
        return Math.PI*r*r;
    }
}