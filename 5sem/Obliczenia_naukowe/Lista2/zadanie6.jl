#Szymon Hładyszewski 279772
function nextFloat64(x::Float64, c::Float64)
  nextx = x * x + c
  return nextx
end
c = Float64[-2, -2, -2, -2, -1, -1, -1]
x = Float64[1, 2, 1.99999999999999, 1, -1, 0.75, 0.25]
for i in 1:length(c)
    xi = Float64(x[i])
    ci = Float64(c[i])

    for n in 1:40
        println("c = $(ci), x0 = $(x[i]), n = $n, x = $xi")
        xi = nextFloat64(xi, ci)
    end
end