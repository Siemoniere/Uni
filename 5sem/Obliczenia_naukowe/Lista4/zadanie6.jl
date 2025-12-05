#Szymon Hładyszewski
include("zadanie1234.jl")
using .Inter
using Plots

for n in [5, 10, 15]
    # Węzły równoodległe
    rysujNnfx(x->abs(x), -1.0, 1.0, n)
    title!("Wykres interpolacji funkcji abs(x) dla n=$n (węzły równoodległe)")
    xlabel!("x")
    ylabel!("Nn,f(x) oraz f(x)")
    savefig("wykres_abs_rownoodlegle_n$(n).png")
    println("Zapisano wykres wykres_abs_rownoodlegle_n$(n).png")

    rysujNnfx(x->1/(1+x^2), -5.0, 5.0, n)
    title!("Wykres interpolacji funkcji 1/(1+x^2) dla n=$n (węzły równoodległe)")
    xlabel!("x")
    ylabel!("Nn,f(x) oraz f(x)")
    savefig("wykres_1_1plusx2_rownoodlegle_n$(n).png")
    println("Zapisano wykres wykres_1_1plusx2_rownoodlegle_n$(n).png")

    # Węzły Czebyszewa
    rysujNnfx(x->abs(x), -1.0, 1.0, n, wezly=:czebyszew)
    title!("Wykres interpolacji funkcji abs(x) dla n=$n (węzły Czebyszewa)")
    xlabel!("x")
    ylabel!("Nn,f(x) oraz f(x)")
    savefig("wykres_abs_czebyszew_n$(n).png")
    println("Zapisano wykres wykres_abs_czebyszew_n$(n).png")

    rysujNnfx(x->1/(1+x^2), -5.0, 5.0, n, wezly=:czebyszew)
    title!("Wykres interpolacji funkcji 1/(1+x^2) dla n=$n (węzły Czebyszewa)")
    xlabel!("x")
    ylabel!("Nn,f(x) oraz f(x)")
    savefig("wykres_1_1plusx2_czebyszew_n$(n).png")
    println("Zapisano wykres wykres_1_1plusx2_czebyszew_n$(n).png")
end