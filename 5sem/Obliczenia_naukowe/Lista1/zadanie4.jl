function find_smallest()::Float64
    x::Float64 = nextfloat(Float64(1.0))
    while x*(Float64(1)/x) == Float64(1.0)
        x = nextfloat(x)
    end
    return x
end
println("Smallest Float64 such that x * (1/x) != 1: ", find_smallest())