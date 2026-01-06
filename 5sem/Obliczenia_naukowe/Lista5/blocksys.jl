#Szymon Hładyszewski 279772

module blocksys
using Printf
export load_matrix, load_vector, save_solution, solve_gauss_pivot!, 
       factorize_lu_pivot!, solve_lu, generate_vector_b, 
       solve_gauss_no_pivot!, factorize_lu_no_pivot!,
       BlockMatrix, set_val!, get_val
mutable struct BlockMatrix
    n::Int
    l::Int
    data::Matrix{Float64}
    offset::Int
end

function BlockMatrix(n, l)
    return BlockMatrix(n, l, zeros(Float64, n, 3 * l + 1), l)
end

function set_val!(M::BlockMatrix, i, j, val)
    col = j - i + M.offset + 1
    if col >= 1 && col <= size(M.data, 2)
        M.data[i, col] = val
    end
end

function get_val(M::BlockMatrix, i, j)
    col = j - i + M.offset + 1
    if col < 1 || col > size(M.data, 2)
        return 0.0
    end
    return M.data[i, col]
end

function load_matrix(filepath::String)
    open(filepath, "r") do f
        line = readline(f)
        parts = split(line)
        n, l = parse(Int, parts[1]), parse(Int, parts[2])
        M = BlockMatrix(n, l)
        while !eof(f)
            line = readline(f)
            if isempty(strip(line)) continue end
            parts = split(line)
            i, j, val = parse(Int, parts[1]), parse(Int, parts[2]), parse(Float64, parts[3])
            set_val!(M, i, j, val)
        end
        return n, l, M
    end
end

function load_vector(filepath::String)
    open(filepath, "r") do f
        n = parse(Int, readline(f))
        return [parse(Float64, readline(f)) for _ in 1:n]
    end
end

function save_solution(path::String, x::Vector{Float64}, n::Int, err::Union{Float64, Nothing} = nothing)
    open(path, "w") do f
        if err !== nothing
            println(f, err) # Zapis błędu zgodnie z wymogiem scnal5l.pdf
        end
        for i in 1:n
            println(f, x[i])
        end
    end
end

function generate_vector_b(n::Int, l::Int, A::BlockMatrix)
    b = zeros(Float64, n)
    for i in 1:n
        for col_idx in 1:size(A.data, 2)
            val = A.data[i, col_idx]
            if val != 0.0
                j = col_idx - 1 - A.offset + i
                if j >= 1 && j <= n
                    b[i] += val
                end
            end
        end
    end
    return b
end

function factorize_lu_no_pivot!(n::Int, l::Int, A::BlockMatrix)
    for k in 1:n-1
        for i in k+1:min(k + l, n)
            if abs(get_val(A, k, k)) < 1e-12
                error("Zero na przekątnej w kroku $k - wymagany wybór elementu głównego.")
            end
            f = get_val(A, i, k) / get_val(A, k, k)
            set_val!(A, i, k, f)
            for j in k+1:min(k + l, n)
                set_val!(A, i, j, get_val(A, i, j) - f * get_val(A, k, j))
            end
        end
    end
end

function factorize_lu_pivot!(n::Int, l::Int, A::BlockMatrix)
    p = collect(1:n)
    for k in 1:n-1
        max_idx, max_v = k, abs(get_val(A, p[k], k))
        for i in k+1:min(k + l, n)
            v = abs(get_val(A, p[i], k))
            if v > max_v max_v, max_idx = v, i end
        end
        p[k], p[max_idx] = p[max_idx], p[k]
        for i in k+1:min(k + l, n)
            f = get_val(A, p[i], k) / get_val(A, p[k], k)
            set_val!(A, p[i], k, f)
            for j in k+1:min(k + 2*l, n)
                set_val!(A, p[i], j, get_val(A, p[i], j) - f * get_val(A, p[k], j))
            end
        end
    end
    return p
end

function solve_lu(n::Int, l::Int, LU::BlockMatrix, b::Vector{Float64}, p::Vector{Int})
    y = zeros(n)
    for i in 1:n
        s = 0.0
        for j in max(1, i - 2*l):i-1 s += get_val(LU, p[i], j) * y[j] end
        y[i] = b[p[i]] - s
    end
    x = zeros(n)
    for i in n:-1:1
        s = 0.0
        for j in i+1:min(i + 2*l, n) s += get_val(LU, p[i], j) * x[j] end
        x[i] = (y[i] - s) / get_val(LU, p[i], i)
    end
    return x
end

function solve_gauss_pivot!(n::Int, l::Int, A::BlockMatrix, b::Vector{Float64})
    p = collect(1:n)
    for k in 1:n-1
        max_idx, max_v = k, abs(get_val(A, p[k], k))
        for i in k+1:min(k + l, n)
            v = abs(get_val(A, p[i], k))
            if v > max_v max_v, max_idx = v, i end
        end
        p[k], p[max_idx] = p[max_idx], p[k]
        for i in k+1:min(k + l, n)
            f = get_val(A, p[i], k) / get_val(A, p[k], k)
            for j in k+1:min(k + 2*l, n)
                set_val!(A, p[i], j, get_val(A, p[i], j) - f * get_val(A, p[k], j))
            end
            b[p[i]] -= f * b[p[k]]
        end
    end
    x = zeros(n)
    for i in n:-1:1
        s = 0.0
        for j in i+1:min(i + 2*l, n) s += get_val(A, p[i], j) * x[j] end
        x[i] = (b[p[i]] - s) / get_val(A, p[i], i)
    end
    return x
end

function solve_gauss_no_pivot!(n::Int, l::Int, A::BlockMatrix, b::Vector{Float64})
    for k in 1:n-1
        for i in k+1:min(k + l, n)
            f = get_val(A, i, k) / get_val(A, k, k)
            for j in k+1:min(k + l, n)
                set_val!(A, i, j, get_val(A, i, j) - f * get_val(A, k, j))
            end
            b[i] -= f * b[k]
        end
    end
    x = zeros(n)
    for i in n:-1:1
        s = 0.0
        for j in i+1:min(i + l, n) s += get_val(A, i, j) * x[j] end
        x[i] = (b[i] - s) / get_val(A, i, i)
    end
    return x
end

end