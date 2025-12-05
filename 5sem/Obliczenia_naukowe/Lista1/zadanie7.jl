function derivative(f, x, h)::Float64
    return (f(x + h) - f(x)) / (h)
end
function f(x::Float64)::Float64
    return sin(x)+cos(3*x)
end
function df(x::Float64)::Float64
    return cos(x)-3*sin(3*x)
end

function main()
    h::Float64 = 1.0
    i = 0

    while i <= 54
        println("i = ", i, ", f'(1.0): ", derivative(f, Float64(1.0), h), "diff: ", abs(derivative(f, Float64(1.0), h) - df(Float64(1.0))), " h+1: ", h+1)
        i += 1
        h /= 2
    end
end

main()