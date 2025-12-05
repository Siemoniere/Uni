public class Mainpierwszy {

    public static void main(String[] args) {
        if (args.length<2){
            System.out.println("Za mało danych");
            return;
        }
        for (int i=1;i<args.length;i++){
            try { 
                double n=Double.parseDouble(args[i]);
                if (n<0.0) {
                    System.out.println("Podano ujemną liczbę");
                    return;
                }
            }
            catch (NumberFormatException ex) {
                System.out.println(args[i]+" nie jest to liczba");
                return;
            }
        }
        String figura= args[0];
        double obwod, pole, bok;
        switch (figura){
            case "o":
                if (!(args.length==2)) {
                    System.out.println("Zła ilość danych");
                    return;
                }
                double r= Double.parseDouble(args[1]);
                Kolo kolo= new Kolo("kolo", r);
                obwod=kolo.LiczObwód();
                pole=kolo.LiczPole();
                System.out.println("Pole figury "+ kolo.Nazwa()+" wynosi "+pole+", a obwód wynosi "+obwod);
                break;
            case "p":
                if (!(args.length==2)) {
                    System.out.println("Zła ilość danych");
                    return;
                }
                bok= Double.parseDouble(args[1]);
                Pieciokat pieciokat = new Pieciokat("pieciokat", bok);
                obwod=pieciokat.LiczObwód();
                pole=pieciokat.LiczPole();
                System.out.println("Pole figury "+ pieciokat.Nazwa()+" wynosi "+pole+", a obwód wynosi "+obwod);
                break;
            case "s":
                if (!(args.length==2)) {
                    System.out.println("Zła ilość danych");
                    return;
                }
                bok= Double.parseDouble(args[1]);
                Szesciokat szesciokat = new Szesciokat("sześciokat", bok);
                obwod=szesciokat.LiczObwód();
                pole=szesciokat.LiczPole();
                System.out.println("Pole figury "+ szesciokat.Nazwa()+" wynosi "+pole+", a obwód wynosi "+obwod);
                break;
            case "c":
                if (!(args.length==6)) {
                    System.out.println("Zła ilość danych");
                    return;
                }
                double bok1= Double.parseDouble(args[1]);
                double bok2= Double.parseDouble(args[2]);
                double bok3= Double.parseDouble(args[3]);
                double bok4= Double.parseDouble(args[4]);
                double kat= Double.parseDouble(args[5]);
                
                if (!(0<kat&&kat<180)){
                    System.out.println("Podano niepoprawny kąt");
                    return;
                }
                if (!(kat==90)){
                    Romb romb = new Romb("romb", bok1, bok2, bok3, bok4, kat);
                    obwod=romb.LiczObwód();
                    pole=romb.LiczPole();
                    System.out.println("Pole figury "+ romb.Nazwa()+" wynosi "+pole+", a obwód wynosi "+obwod);
                }else if(bok1==bok2&&bok3==bok4&&bok1==bok3){
                    Kwadrat kwadrat = new Kwadrat("kwadrat", bok1, bok2, bok3, bok4, kat);
                    obwod=kwadrat.LiczObwód();
                    pole=kwadrat.LiczPole();
                    System.out.println("Pole figury "+ kwadrat.Nazwa()+" wynosi "+pole+", a obwód wynosi "+obwod);
                } else if ((bok1==bok2||bok1==bok3||bok1==bok4)&&(bok2==bok3||bok2==bok4||bok3==bok4)){
                    Prostokar prostokat = new Prostokar("prostokąt", bok1, bok2, bok3, bok4, kat);
                    obwod=prostokat.LiczObwód();
                    pole= prostokat.LiczPole();
                    System.out.println("Pole figury "+ prostokat.Nazwa()+" wynosi "+pole+", a obwod wynosi "+obwod);
                } else {
                    System.out.println("Nie jest to ani kwadrat, ani romb, ani prostokąt");
                    return;
                }
                break;
            default:
                System.out.println("Nie ma takiej figury");
        }
    }
}