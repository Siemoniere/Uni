#Szymon Hładyszewski

include("zadanie13.jl")
using .zadanie13

function f(x::Float64)::Float64
  return sin(x) - (x/2)^2
end

function pf(x::Float64)::Float64
  return cos(x) - x/2
end
delta::Float64 = 0.5*10^(-5)
epsilon::Float64 = 0.5*10^(-5)

res = mbisekcji(f, 1.5, 2.0, delta, epsilon)
println("Metoda bisekcji: ", res)
res = mstycznych(f, pf, 1.5, delta, epsilon, 1000)
println("Metoda Newtona (stycznych): ", res)
res = msiecznych(f, 1.0, 2.0, delta, epsilon, 1000)
println("Metoda siecznych: ", res)