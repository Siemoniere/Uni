using Plots, CSV, DataFrames

function generate_plots()
    if !isfile("all.txt")
        error("Brak pliku all.txt. Uruchom najpierw main.jl")
    end

    df = CSV.read("all.txt", DataFrame, delim=';')
    df_gen = df[df.scenario .== "Gen_B", :]

    p1 = plot(title="Złożoność czasowa O(n)", xlabel="Rozmiar n", ylabel="Czas [s]", legend=:topleft)
    for method in unique(df_gen.method)
        sub = df_gen[df_gen.method .== method, :]
        sort!(sub, :n) 
        plot!(p1, sub.n, sub.time, label=method, marker=:circle)
    end
    
    p2 = plot(title="Złożoność pamięciowa O(n)", xlabel="Rozmiar n", ylabel="Pamięć [MB]", legend=:topleft)
    for method in unique(df_gen.method)
        sub = df_gen[df_gen.method .== method, :]
        sort!(sub, :n)
        plot!(p2, sub.n, sub.memory ./ (1024^2), label=method, marker=:square)
    end
    
    savefig(p1, "results/wykres_czasu.png")
    savefig(p2, "results/wykres_pamieci.png")
    println("Sukces: Wykresy zapisano w folderze results/")
end

generate_plots()