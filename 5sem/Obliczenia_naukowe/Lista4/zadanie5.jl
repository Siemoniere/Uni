#Szymon Hładyszewski
include("zadanie1234.jl")
using .Inter
using Plots

for n in [5, 10, 15]
    rysujNnfx(x->exp(x), 0.0, 1.0, n)
    title!("Wykres interpolacji funkcji exp(x) dla n=$n węzłów równoodległych")
    xlabel!("x")
    ylabel!("Nn,f(x) oraz f(x)")
    savefig("wykres_exp_rownoodlegle_n$(n).png")
    println("Zapisano wykres wykres_exp_rownoodlegle_n$(n).png")


    rysujNnfx(x->sin(x)*x^2, -1.0, 1.0, n)
    title!("Wykres interpolacji funkcji sin(x)*x^2 dla n=$n węzłów równoodległych")
    xlabel!("x")
    ylabel!("Nn,f(x) oraz f(x)")
    savefig("wykres_sinx2_rownoodlegle_n$(n).png")
    println("Zapisano wykres wykres_sinx2_rownoodlegle_n$(n).png")
end