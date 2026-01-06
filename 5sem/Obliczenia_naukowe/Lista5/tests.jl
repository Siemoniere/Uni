using Test, LinearAlgebra
include("blocksys.jl")
using .blocksys

@testset "Weryfikacja wszystkich wariantów A1-A7" begin
    for i in 1:7
        path = joinpath("data", "A$i.txt")
        !isfile(path) && continue
        n, l, A = blocksys.load_matrix(path)
        b = blocksys.generate_vector_b(n, l, A)
        
        @testset "Macierz A$i" begin
            # Test Gaussa z pivotem
            x = blocksys.solve_gauss_pivot!(n, l, deepcopy(A), copy(b))
            @test norm(x - ones(n))/norm(ones(n)) < 1e-10
            
            # Test LU z pivotem
            Alu = deepcopy(A)
            p = blocksys.factorize_lu_pivot!(n, l, Alu)
            x_lu = blocksys.solve_lu(n, l, Alu, copy(b), p)
            @test norm(x_lu - ones(n))/norm(ones(n)) < 1e-10
        end
    end
end