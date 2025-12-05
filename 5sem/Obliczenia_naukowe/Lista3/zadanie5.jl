#szymon hladyszewski

include("zadanie13.jl")
using .zadanie13

function f(x::Float64)::Float64
  return 3*x - exp(x)
end
delta::Float64 = 0.5*10^(-4)
epsilon::Float64 = 0.5*10^(-4)
res1 = mbisekcji(f, 0.5, 1.0, delta, epsilon)
res2 = mbisekcji(f, 1.0, 2.0, delta, epsilon)
println("Metoda bisekcji na [0.5, 1.0]: ", res1)
println("Metoda bisekcji na [1.0, 2.0]: ", res2)