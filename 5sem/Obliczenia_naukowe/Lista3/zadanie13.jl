#Szymon Hładyszewski zadania 1-3
module zadanie13
export mbisekcji, msiecznych, mstycznych
function mbisekcji(f, a::Float64, b::Float64, delta::Float64, epsilon::Float64)
    err = 0
    u = f(a)
    v = f(b)
    if u * v > 0
        err = 1
        return (Nothing, Nothing, Nothing, err)
    end
    it = 0
    r = (a + b) / 2
    w = f(r)
    it += 1
    while abs(b - a) > delta && abs(w) > epsilon
        it += 1
        if u * w <= 0
            b = r
            v = w
        else
            a = r
            u = w
        end
        r = (a + b) / 2
        w = f(r)
    end
    return (r, w, it, err)
end

function msiecznych(f, x0::Float64, x1::Float64, delta::Float64, epsilon::Float64, maxit::Int)
    it = 0
    f0 = f(x0)
    f1 = f(x1)

    while abs(x1 - x0) > delta && abs(f1) > epsilon && it < maxit
        it += 1

        # sprawdzenie na dzielenie przez zero → metoda się "zawiesza"
        if f1 == f0
            return (NaN, NaN, maxit, 1)
        end

        x2 = x1 - f1 * (x1 - x0) / (f1 - f0)

        x0 = x1
        f0 = f1
        x1 = x2
        f1 = f(x1)
    end

    # jeśli nie udało się znaleźć zbieżności, ale iteracje się skończyły
    err = 0
    if it == maxit && abs(f1) > epsilon
        err = 1
    end

    return (x1, f1, it, err)
end

end
function mstycznych(f, pf, x0::Float64, delta::Float64, epsilon::Float64, maxit::Int)
    it = 0
    f0 = f(x0)
    pf0 = pf(x0)
    if pf0 == 0
        return (Nothing, Nothing, Nothing, 2)
    end
    while abs(f0) > epsilon && it < maxit
        it += 1
        if pf0 == 0
            return (Nothing, Nothing, it, 2)
        end
        x1 = x0 - f0 / pf0
        if abs(x1 - x0) < delta
            x0 = x1
            f0 = f(x0)
            break
        end
        x0 = x1
        f0 = f(x0)
        pf0 = pf(x0)
    end
    err = 0
    if it == maxit && abs(f0) > epsilon
        err = 1
    end
    return (x0, f0, it, err)
end