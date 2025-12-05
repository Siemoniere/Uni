#Szymon Hładyszewski

include("zadanie13.jl")
using .zadanie13

function f1(x::Float64)::Float64
  return exp(1-x) - 1
end
function f2(x::Float64)::Float64
  return x*exp(-x)
end
function pf1(x::Float64)::Float64
  return -exp(1-x)
end
function pf2(x::Float64)::Float64
  return exp(-x) - x*exp(-x)
end
delta::Float64 = 0.5*10^(-5)
epsilon::Float64 = 0.5*10^(-5)
maxit = 200

println("Metoda bisekcji dla [0,2] i f1: ", mbisekcji(f1, 0.0, 2.0, delta, epsilon))
println("Metoda Newtona dla 0.5 i f1: ", mstycznych(f1, pf1, 0.5, delta, epsilon, maxit))
println("Metoda siecznych dla 0.0, 2.0 i f1: ", msiecznych(f1, 0.0, 2.0, delta, epsilon, maxit))



println("Metoda bisekcji dla [-1,2] i f2: ", mbisekcji(f2, -1.0, 2.0, delta, epsilon))
println("Metoda Newtona dla 0.5 i f2: ", mstycznych(f2, pf2, 0.5, delta, epsilon, maxit))

println("Metoda siecznych dla -1.0, -1.0 i f2: ", msiecznych(f2, -1.0, -1.0, delta, epsilon, maxit))