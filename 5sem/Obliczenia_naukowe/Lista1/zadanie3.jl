function delta(a::Float64, b::Float64)
    delta::Float64 = 0.0
    x = a
    y = nextfloat(x)
    println("First delta: ", y - x)
    while (y <= b)
        delta = Float64(y - x)
        x = nextfloat(x)
        y = nextfloat(y)
        new_delta = Float64(y - x)
        if (new_delta != delta)
            println("Delta between ", x, " and ", y, " is ", delta)
            return delta
        end
    end
    return delta
end
function bits(a::Float64, step::Float64, n::Int)
    num::Float64 = a
    while (n > 0)
        println("Value: ", num, " Bits: ", bitstring(num))
        num += step
        n -= 1
    end
end

#delta(1.0, 2.0)
bits(Float64(1.0), 0.0000000000000002220446049250313, 10)
delta(0.5, 1.0)
bits(Float64(0.5), 0.0000000000000001110223024625156, 10)
delta(2.0, 4.0)
bits(Float64(2.0), 0.0000000000000004440892098500626, 10)