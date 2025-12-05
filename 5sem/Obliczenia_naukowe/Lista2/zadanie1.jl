#Szymon Hładyszewski 279772
function scalar_product_aFL32(x, y)::Float32
    result::Float32 = Float32(0.0)
    for i in (1:length(x))
        result += x[i] * y[i]
    end
    return result
end
function scalar_product_bFL32(x, y)::Float32
    result::Float32 = Float32(0.0)
    for i in (length(x):-1:1)
        result += x[i] * y[i]
    end
    return result
end
function scalar_product_cFL32(x, y)::Float32
    pos::Float32 = Float32(0.0)
    neg::Float32 = Float32(0.0)
    result::Float32 = Float32(0.0)
    data = Array{Float32}(undef, length(x))
    for i in (1:length(x))
        data[i] = x[i] * y[i]
    end
    sort!(data)
    for i in (1:length(x))
      if (data[i] >= 0.0)
        pos += data[i]
      end
    end

    for i in (length(x):-1:1)
      if (data[i] < 0.0)
        neg += data[i]
      end
    end
    result = pos + neg
    return result
end
function scalar_product_dFL32(x, y)::Float32
    pos::Float32 = Float32(0.0)
    neg::Float32 = Float32(0.0)
    result::Float32 = Float32(0.0)
    data = Array{Float32}(undef, length(x))
    for i in (1:length(x))
        data[i] = x[i] * y[i]
    end
    sort!(data, rev=true)
    for i in (length(x):-1:1)
      if (data[i] >= 0.0)
        pos += data[i]
      end
    end

    for i in (1:length(x))
      if (data[i] < 0.0)
        neg += data[i]
      end
    end
    result = pos + neg
    return result
end
function scalar_product_aFL64(x, y)::Float64
    result::Float64 = Float64(0.0)
    for i in (1:length(x))
        result += x[i] * y[i]
    end
    return result
end
function scalar_product_bFL64(x, y)::Float64
    result::Float64 = Float64(0.0)
    for i in (length(x):-1:1)
        result += x[i] * y[i]
    end
    return result
end
function scalar_product_cFL64(x, y)::Float64
    pos::Float64 = Float64(0.0)
    neg::Float64 = Float64(0.0)
    result::Float64 = Float64(0.0)
    data = Array{Float64}(undef, length(x))
    for i in (1:length(x))
        data[i] = x[i] * y[i]
    end
    sort!(data)
    for i in (1:length(x))
      if (data[i] >= 0.0)
        pos += data[i]
      end
    end
    for i in (length(x):-1:1)
      if (data[i] < 0.0)
        neg += data[i]
      end
    end
    result = pos + neg
    return result
end
function scalar_product_dFL64(x, y)::Float64
    pos::Float64 = Float64(0.0)
    neg::Float64 = Float64(0.0)
    result::Float64 = Float64(0.0)
    data = Array{Float64}(undef, length(x))
    for i in (1:length(x))
        data[i] = x[i] * y[i]
    end
    sort!(data, by = abs, rev=true)
    for i in (length(x):-1:1)
      if (data[i] >= 0.0)
        pos += data[i]
      end
    end
    for i in (1:length(x))
      if (data[i] < 0.0)
        neg += data[i]
      end
    end
    result = pos + neg
    return result
end
println("Before change:")
xfl32 = [Float32(2.718281828), Float32(-3.141592654), Float32(1.414213562), Float32(0.5772156649), Float32(0.3010299957)]
yfl32 = [Float32(1486.2497), Float32(878366.9879), Float32(-22.37492), Float32(4773714.647), Float32(0.000185049)]
xfl64 = [Float64(2.718281828), Float64(-3.141592654), Float64(1.414213562), Float64(0.5772156649), Float64(0.3010299957)]
yfl64 = [Float64(1486.2497), Float64(878366.9879), Float64(-22.37492), Float64(4773714.647), Float64(0.000185049)]
println("Float32 scalar product a: ", scalar_product_aFL32(xfl32, yfl32))
println("Float32 scalar product b: ", scalar_product_bFL32(xfl32, yfl32))
println("Float32 scalar product c: ", scalar_product_cFL32(xfl32, yfl32))
println("Float32 scalar product d: ", scalar_product_dFL32(xfl32, yfl32))
println("Float64 scalar product a: ", scalar_product_aFL64(xfl64, yfl64))
println("Float64 scalar product b: ", scalar_product_bFL64(xfl64, yfl64))
println("Float64 scalar product c: ", scalar_product_cFL64(xfl64, yfl64))
println("Float64 scalar product d: ", scalar_product_dFL64(xfl64, yfl64))
println("After change:")
xfl32 = [Float32(2.718281828), Float32(-3.141592654), Float32(1.414213562), Float32(0.577215664), Float32(0.301029995)]
yfl32 = [Float32(1486.2497), Float32(878366.9879), Float32(-22.37492), Float32(4773714.647), Float32(0.000185049)]
xfl64 = [Float64(2.718281828), Float64(-3.141592654), Float64(1.414213562), Float64(0.577215664), Float64(0.301029995)]
yfl64 = [Float64(1486.2497), Float64(878366.9879), Float64(-22.37492), Float64(4773714.647), Float64(0.000185049)]
println("Float32 scalar product a: ", scalar_product_aFL32(xfl32, yfl32))
println("Float32 scalar product b: ", scalar_product_bFL32(xfl32, yfl32))
println("Float32 scalar product c: ", scalar_product_cFL32(xfl32, yfl32))
println("Float32 scalar product d: ", scalar_product_dFL32(xfl32, yfl32))
println("Float64 scalar product a: ", scalar_product_aFL64(xfl64, yfl64))
println("Float64 scalar product b: ", scalar_product_bFL64(xfl64, yfl64))
println("Float64 scalar product c: ", scalar_product_cFL64(xfl64, yfl64))
println("Float64 scalar product d: ", scalar_product_dFL64(xfl64, yfl64))
