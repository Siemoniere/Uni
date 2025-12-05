function f(x::Float64)::Float64
    return sqrt(x^2 + 1.0) - 1.0
end
function g(x::Float64)::Float64
    return x^2 / (sqrt(x^2 + 1.0) + 1.0)
end

function main()
    local i = -1
    while f(8.0^i) != 0 || g(8.0^i) != 0
        println("i = ", i, " f(8^i) = ", f(8.0^i), " g(8^i) = ", g(8.0^i))
        i -= 1
    end
end

main()