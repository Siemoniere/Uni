#Szymon Hładyszewski 279772
function nextFloat32(p::Float32, r::Float32)
  nextP::Float32 = p + r*p*(1-p)
  return nextP
end

function nextFloat64(p::Float64, r::Float64)
  nextP::Float64 = p + r*p*(1-p)
  return nextP
end
rfl32 = Float32(3.0)
rfl64 = Float64(3.0)

pfl32 = Float32(0.01)
pfl64 = Float64(0.01)
pfl32_cut = Float32(0.722)
for n in 1:10
    global pfl32 = nextFloat32(pfl32, rfl32)
    global pfl64 = nextFloat64(pfl64, rfl64)
    println("n = $n, Float32 p = $pfl32, Float64 p = $pfl64")
end
println("After 10 iterations Float32 p = $pfl32_cut")
for n in 11:40
    global pfl32_cut = nextFloat32(pfl32_cut, rfl32)
    global pfl32 = nextFloat32(pfl32, rfl32)
    global pfl64 = nextFloat64(pfl64, rfl64)
    println("n = $n, Float32 p = $pfl32, Float64 p = $pfl64, Float32 cut p = $pfl32_cut")
end