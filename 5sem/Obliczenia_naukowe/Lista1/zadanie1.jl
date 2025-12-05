function machepsFL16()::Float16
    e::Float16 = Float16(1.0)
    while Float16(1.0) + e / Float16(2.0) > Float16(1.0)
        e /= Float16(2.0)
    end    
    return e
end
function machepsFL32()::Float32
    e::Float32 = Float32(1.0)
    while Float32(1.0) + e / Float32(2.0) > Float32(1.0)
        e /= Float32(2.0)
    end    
    return e
end
function machepsFL64()::Float64
    e::Float64 = Float64(1.0)
    while Float64(1.0) + e / Float64(2.0) > Float64(1.0)
        e /= Float64(2.0)
    end    
    return e
end
function etaFL16()::Float16
    e::Float16 = Float16(1.0)
    while e / Float16(2.0) > Float16(0.0)
        e /= Float16(2.0)
    end    
    return e
end
function etaFL32()::Float32
    e::Float32 = Float32(1.0)
    while e / Float32(2.0) > Float32(0.0)
        e /= Float32(2.0)
    end    
    return e
end
function etaFL64()::Float64
    e::Float64 = Float64(1.0)
    while e / Float64(2.0) > Float64(0.0)
        e /= Float64(2.0)
    end    
    return e
end
function maxFL16()::Float16
  fl16::Float16 = 1.0
  fl16new::Float16 = fl16*2
  while !isinf(fl16new)
    fl16 = fl16new
    fl16new = fl16 * 2
  end
  add::Float16 = fl16 / 2
  while (add > 0.0 && fl16 + add > fl16)
    fl16new = fl16 + add
    if !isinf(fl16new)
      fl16 = fl16new
    else
      add /= 2
    end
  end
  return fl16
end

function maxFL32()::Float32
  fl32::Float32 = 1.0
  fl32new::Float32 = fl32*2
  while !isinf(fl32new)
    fl32 = fl32new
    fl32new = fl32 * 2
  end
  add::Float32 = fl32 / 2
  while (add > 0.0 && fl32 + add > fl32)
    fl32new = fl32 + add
    if !isinf(fl32new)
      fl32 = fl32new
    else
      add /= 2
    end
  end
  return fl32
end

function maxFL64()::Float64
  fl64::Float64 = 1.0
  fl64new::Float64 = fl64*2
  while !isinf(fl64new)
    fl64 = fl64new
    fl64new = fl64 * 2
  end
  add::Float64 = fl64 / 2
  while (add > 0.0 && fl64 + add > fl64)
    fl64new = fl64 + add
    if !isinf(fl64new)
      fl64 = fl64new
    else
      add /= 2
    end
  end
  return fl64
end

println("Macheps for Float16: ", machepsFL16())
println("Default macheps for Float16: ", eps(Float16))
println("Macheps for Float32: ", machepsFL32())
println("Default macheps for Float32: ", eps(Float32))
println("Macheps for Float64: ", machepsFL64())
println("Default macheps for Float64: ", eps(Float64))
println("Eta for Float16: ", etaFL16())
println("Default eta for Float16: ", nextfloat(Float16(0.0)))
println("Eta for Float32: ", etaFL32())
println("Default eta for Float32: ", nextfloat(Float32(0.0)))
println("Eta for Float64: ", etaFL64())
println("Default eta for Float64: ", nextfloat(Float64(0.0)))
println(floatmin(Float32))
println(floatmin(Float64))
println("Max for Float16: ", maxFL16())
println("Default max for Float16: ", floatmax(Float16))
println("Max for Float32: ", maxFL32())
println("Default max for Float32: ", floatmax(Float32))
println("Max for Float64: ", maxFL64())
println("Default max for Float64: ", floatmax(Float64))