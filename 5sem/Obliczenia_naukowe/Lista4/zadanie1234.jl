#Szymon Hładyszewski
module Inter
export ilorazyRoznicowe, warNewton, naturalna, rysujNnfx
using Plots
function ilorazyRoznicowe(x::Vector{Float64}, f::Vector{Float64})
    n = length(x)
    fx = zeros(n)
    for i in 1:n
        fx[i] = f[i]
    end
    for j in 2:n
        for i in n:-1:j
            fx[i] = (fx[i] - fx[i-1]) / (x[i] - x[i-j+1])
        end
    end
    return fx
end
function warNewton(x::Vector{Float64}, fx::Vector{Float64}, t::Float64)
    n = length(x)
    nt = fx[n]
    for i in n-1:-1:1
        nt = fx[i] + (t - x[i]) * nt
    end
    return nt
end
function naturalna(x::Vector{Float64}, fx::Vector{Float64})
    n = length(x)
    a = zeros(n)
    a[n] = fx[n]
    for i in n-1:-1:1
        a[i] = fx[i] - x[i] * a[i+1]
        for j in i+1:n-1
            a[j] = a[j] - x[i] * a[j+1]
        end
    end
    return a
end
function rysujNnfx(f, a::Float64, b::Float64, n::Int; wezly::Symbol = :rownoodlegle)
    rozdzielczosc = 100 
    x::Vector{Float64} = zeros(n+1)
    fWartosci::Vector{Float64} = zeros(n+1)

    if wezly == :rownoodlegle
        h::Float64 = (b - a) / n
        for i in 1:(n+1)
            x[i] = a + (i-1)*h
            fWartosci[i] = f(x[i])
        end
    elseif wezly == :czebyszew
        for i in 1:(n+1)
            x[i] = 0.5 * ((b - a) * cos((2i - 1) * π / (2 * (n+1))) + (b + a))
            fWartosci[i] = f(x[i])
        end
    else
        throw(ArgumentError("Nieobsługiwany typ węzłów: $wezly"))
    end

    c::Vector{Float64} = ilorazyRoznicowe(x, fWartosci)

    liczbaPunktow = rozdzielczosc * n + 1
    odstep::Float64 = (b - a) / (liczbaPunktow - 1)

    xWykres::Vector{Float64} = zeros(liczbaPunktow)
    fWykres::Vector{Float64} = zeros(liczbaPunktow)
    wielomianWykres::Vector{Float64} = zeros(liczbaPunktow)

    xWykres[1] = a
    fWykres[1] = fWartosci[1]
    wielomianWykres[1] = fWartosci[1]
    for i in 2:liczbaPunktow
        xWykres[i] = a + (i-1)*odstep
        fWykres[i] = f(xWykres[i])
        wielomianWykres[i] = warNewton(x, c, xWykres[i])
    end

    p = plot(xWykres, [fWykres, wielomianWykres], label = ["funkcja" "wielomian"], title = "Interpolacja funkcji f wielomianem stopnia <=$n")
    return p
end

end