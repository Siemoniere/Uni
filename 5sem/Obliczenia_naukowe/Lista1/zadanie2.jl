function guess_machepsFL16()::Float16
    return Float16(3)*((Float16(4)/Float16(3)) - Float16(1)) - Float16(1)
end
function guess_machepsFL32()::Float32
    return Float32(3)*((Float32(4)/Float32(3)) - Float32(1)) - Float32(1)
end
function guess_machepsFL64()::Float64
    return Float64(3)*((Float64(4)/Float64(3)) - Float64(1)) - Float64(1)
end

println("Guessed Float16 machine epsilon: ", guess_machepsFL16())
println("Real Float16 machine epsilon: ", eps(Float16))
println("Guessed Float32 machine epsilon: ", guess_machepsFL32())
println("Real Float32 machine epsilon: ", eps(Float32))
println("Guessed Float64 machine epsilon: ", guess_machepsFL64())
println("Real Float64 machine epsilon: ", eps(Float64))