#Szymon Hładyszewski 279772
using LinearAlgebra

function matcond(n::Int, c::Float64)
# Function generates a random square matrix A of size n with
# a given condition number c.
# Inputs:
#	n: size of matrix A, n>1
#	c: condition of matrix A, c>= 1.0
#
# Usage: matcond(10, 100.0)
#
# Pawel Zielinski
        if n < 2
         error("size n should be > 1")
        end
        if c< 1.0
         error("condition number  c of a matrix  should be >= 1.0")
        end
        (U,S,V)=svd(rand(n,n))
        return U*diagm(0 =>[LinRange(1.0,c,n);])*V'
end
function hilb(n::Int)
# Function generates the Hilbert matrix  A of size n,
#  A (i, j) = 1 / (i + j - 1)
# Inputs:
#	n: size of matrix A, n>=1
#
#
# Usage: hilb(10)
#
# Pawel Zielinski
        if n < 1
         error("size n should be >= 1")
        end
        return [1 / (i + j - 1) for i in 1:n, j in 1:n]
end

println("Hilbert")
for n in 1:20
    A = hilb(n)
    x = ones(Float64, n)
    b = A * x

    xGauss = A \ b
    xInv = inv(A) * b

    errGauss = norm(xGauss - x) / norm(x)
    errInv = norm(xInv - x) / norm(x)
    println("n = $n, errGauss = $errGauss, errInv = $errInv")
    println("cond(A) = $(cond(A))")
    println("rank(A) = $(rank(A))")
end
println()
println("Random")
n = [5, 10, 20]
c = [1.0, 10.0, 1e3, 1e7, 1e12, 1e16]
for i in n
    for j in c
        A = matcond(i, j)
        x = ones(Float64, i)
        b = A * x

        xGauss = A \ b
        xInv = inv(A) * b

        errGauss = norm(xGauss - x) / norm(x)
        errInv = norm(xInv - x) / norm(x)
        println("n = $i, cond(A) = $j, errGauss = $errGauss, errInv = $errInv")
        println("rank(A) = $(rank(A))")
    end
end
