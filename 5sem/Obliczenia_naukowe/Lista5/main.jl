# Szymon Hładyszewski 279772
# Program testujący algorytmy zgodnie z wymaganiami:
# 1. Obliczanie błędu dla generowanego b (x=[1...1])
# 2. Zapisywanie składowych x dla wczytanego b

include("blocksys.jl")
using .blocksys
using LinearAlgebra
using Printf

function run_tests()
    data_dir = "data"
    results_dir = "results"
    !isdir(results_dir) && mkpath(results_dir)

    # Lista plików A1-A7
    test_cases = [i for i in 1:7]
    
    # DODANO: memory w nagłówku dla skryptu plots.jl
    open("all.txt", "w") do f println(f, "n;method;scenario;time;memory;error") end

    # --- ROZGRZEWKA (Warm-up) ---
    # Kompilujemy funkcje na małej macierzy A1, aby JIT nie zafałszował wyników w all.txt
    if isfile(joinpath(data_dir, "A1.txt"))
        nw, lw, Aw = blocksys.load_matrix(joinpath(data_dir, "A1.txt"))
        bw = blocksys.generate_vector_b(nw, lw, Aw)
        # Wywołujemy każdą funkcję raz (wynik nas nie interesuje, tylko kompilacja)
        try blocksys.solve_gauss_no_pivot!(nw, lw, deepcopy(Aw), copy(bw)) catch end
        try blocksys.solve_gauss_pivot!(nw, lw, deepcopy(Aw), copy(bw)) catch end
        try 
            Alu = deepcopy(Aw)
            blocksys.factorize_lu_no_pivot!(nw, lw, Alu)
            blocksys.solve_lu(nw, lw, Alu, copy(bw), collect(1:nw))
        catch end
        try 
            AluP = deepcopy(Aw)
            p = blocksys.factorize_lu_pivot!(nw, lw, AluP)
            blocksys.solve_lu(nw, lw, AluP, copy(bw), p)
        catch end
    end
    # --- KONIEC ROZGRZEWKI ---

    println("\n" * "="^115)
    @printf("%-10s | %-18s | %-15s | %-12s | %-15s\n", 
            "Plik", "Metoda", "Scenariusz", "Czas [s]", "Błąd/Status")
    println("-"^115)

    for i in test_cases
        file_A = "A$i.txt"
        file_b = "b$i.txt"
        path_A = joinpath(data_dir, file_A)
        path_b = joinpath(data_dir, file_b)

        if !isfile(path_A)
            continue
        end

        # Wczytanie macierzy
        n, l, A_load = blocksys.load_matrix(path_A)

        methods = [
            ("Gauss_NoPivot", (n_v, l_v, A, b) -> blocksys.solve_gauss_no_pivot!(n_v, l_v, A, b)),
            ("Gauss_Pivot",   (n_v, l_v, A, b) -> blocksys.solve_gauss_pivot!(n_v, l_v, A, b)),
            ("LU_NoPivot",    (n_v, l_v, A, b) -> (blocksys.factorize_lu_no_pivot!(n_v, l_v, A); blocksys.solve_lu(n_v, l_v, A, b, collect(1:n_v)))),
            ("LU_Pivot",      (n_v, l_v, A, b) -> (p = blocksys.factorize_lu_pivot!(n_v, l_v, A); blocksys.solve_lu(n_v, l_v, A, b, p)))
        ]

        for (name, fn) in methods
            # --- SCENARIUSZ 1: b jest OBLICZANE (x = [1...1]) ---
            A_gen = deepcopy(A_load)
            b_gen = blocksys.generate_vector_b(n, l, A_gen)
            
            stats_gen = @timed fn(n, l, A_gen, b_gen)
            x_gen = stats_gen.value
            err = norm(x_gen - ones(n)) / norm(ones(n))

            @printf("%-10s | %-18s | %-15s | %-12.6f | %-15.2e\n", 
                    file_A, name, "Gen_B (Error)", stats_gen.time, err)
            
            # Zapisujemy błąd do results
            out_gen = joinpath(results_dir, "err_$(name)_$(file_A)")
            blocksys.save_solution(out_gen, x_gen, n, err)

            # Logowanie do all.txt (Scenariusz 1)
            open("all.txt", "a") do f
                # DODANO: stats_gen.bytes dla wykresu pamięci
                println(f, "$n;$name;Gen_B;$(stats_gen.time);$(stats_gen.bytes);$err")
            end


            # --- SCENARIUSZ 2: b jest WCZYTANE (jeśli plik istnieje) ---
            if isfile(path_b)
                A_file = deepcopy(A_load)
                b_file = blocksys.load_vector(path_b)
                
                stats_file = @timed fn(n, l, A_file, b_file)
                x_file = stats_file.value

                @printf("%-10s | %-18s | %-15s | %-12.6f | %-15s\n", 
                        file_A, name, "File_B (Sol)", stats_file.time, "Saved to file")
                
                # Zapisujemy składowe x do results (bez błędu, bo go nie znamy)
                out_file = joinpath(results_dir, "sol_$(name)_$(file_A)")
                blocksys.save_solution(out_file, x_file, n)
                
                # DODANO: Logowanie Scenariusza 2 do all.txt
                open("all.txt", "a") do f
                    println(f, "$n;$name;File_B;$(stats_file.time);$(stats_file.bytes);0.0")
                end
            end
        end
        println("-"^115)
    end
end

run_tests()