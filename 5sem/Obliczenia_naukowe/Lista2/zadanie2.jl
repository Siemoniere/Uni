#Szymon Hładyszewski 279772
using SymPy

@syms x
f(x) = ℯ^(x) * log(1 + ℯ^(-x))
println(limit(f(x), x => oo))