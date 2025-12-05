interface Jedno {
    double LiczObwód (double a);
    double LiczPole(double a);
    String Nazwa();
}
interface Dwu {
    double LiczObwód(double a, double b);
    double LiczPole(double a, double b);
    String Nazwa();
}

public class Figury {

    public enum JednoE implements Jedno{
        SZESCIOKAT{
            @Override
            public String Nazwa(){
                return "Sześciokąt";
            }
            @Override
            public double LiczObwód(double bok){
                return 6*bok;
            }
            @Override
            public double LiczPole(double bok){
                return 3*bok*bok*Math.sqrt(3)/2;
            }
        },
        PIECIOKAT{
            @Override
            public String Nazwa(){
                return "Pięciokąt";
            }
            @Override
            public double LiczObwód(double bok){
                return 5*bok;
            }
            @Override
            public double LiczPole(double bok){
                return bok*bok*Math.sqrt(25+10*Math.sqrt(5))/4;
            }
        },
        KOLO{
            @Override
            public String Nazwa(){
                return "Koło";
            }
            @Override
            public double LiczObwód(double r){
                return 2*Math.PI*r;
            }
            @Override
            public double LiczPole(double r){
                return Math.PI*r*r;
            }
        },
        KWADRAT{
            @Override
            public String Nazwa(){
                return "Kwadrat";
            }
            @Override
            public double LiczObwód(double bok1){
                return bok1*4;
            }
            @Override
            public double LiczPole (double bok1){
                return bok1*bok1;
            }
        },
    }

    public enum DwuE implements Dwu{
        PROSTOKAT{
            @Override
            public String Nazwa(){
                return "Prostokąt";
            }
            @Override
            public double LiczObwód(double bok1, double bok2){
                return 2*bok1+2*bok2;
            }
            @Override
            public double LiczPole(double bok1, double bok2){
                return bok1*bok2;
            }
        },
        ROMB{
            @Override
            public String Nazwa(){
                return "Romb";
            }
            @Override
            public double LiczObwód(double bok1, double kat){
                return 4*bok1;
            }
            @Override
            public double LiczPole(double bok1, double kat){
                return bok1*bok1*Math.sin(Math.toRadians(kat));
            }
        },
    }
}