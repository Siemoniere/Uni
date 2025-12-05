import sys
import numpy as np
from PIL import Image
import math

def calculate_entropy(data):
    if data.size == 0:
        return 0.0
    _, counts = np.unique(data, return_counts=True)
    probabilities = counts / counts.sum()
    entropy = -np.sum(probabilities * np.log2(probabilities))
    return entropy

def jpeg_ls_predictors(img_arr):
    img = img_arr.astype(np.int16)
    h, w, channels = img.shape
    padded = np.zeros((h + 1, w + 1, channels), dtype=np.int16)
    padded[1:, 1:] = img
    X = padded[1:, 1:]
    A = padded[1:, :-1]
    B = padded[:-1, 1:]
    C = padded[:-1, :-1]
    
    predictors = {}
    predictors["JPEG 1 (x=a)"] = A
    predictors["JPEG 2 (x=b)"] = B
    predictors["JPEG 3 (x=c)"] = C
    predictors["JPEG 4 (x=a+b-c)"] = A + B - C
    predictors["JPEG 5 (x=a+(b-c)/2)"] = A + (B - C) // 2
    predictors["JPEG 6 (x=b+(a-c)/2)"] = B + (A - C) // 2
    predictors["JPEG 7 (x=(a+b)/2)"] = (A + B) // 2

    max_ab = np.maximum(A, B)
    min_ab = np.minimum(A, B)
    med_pred = np.zeros_like(X)
    mask1 = C >= max_ab
    med_pred[mask1] = min_ab[mask1]
    mask2 = (C <= min_ab) & (~mask1)
    med_pred[mask2] = max_ab[mask2]
    mask3 = (~mask1) & (~mask2)
    med_pred[mask3] = A[mask3] + B[mask3] - C[mask3]
    predictors["JPEG-LS New (MED)"] = med_pred

    results = {}
    for name, pred in predictors.items():
        diff = (X - pred) % 256
        results[name] = diff.astype(np.uint8)
    return results

def analyze_image(filepath):
    try:
        image = Image.open(filepath)
        if image.format != 'TGA' and not filepath.lower().endswith('.tga'):
            print("Ostrzeżenie: Plik może nie być w formacie TGA.")
        image = image.convert('RGB')
        img_arr = np.asarray(image).copy()        
        print(f"Analiza pliku: {filepath}")
        print(f"Wymiary: {img_arr.shape[1]}x{img_arr.shape[0]}")
        ent_orig_total = calculate_entropy(img_arr)
        ent_orig_r = calculate_entropy(img_arr[:,:,0])
        ent_orig_g = calculate_entropy(img_arr[:,:,1])
        ent_orig_b = calculate_entropy(img_arr[:,:,2])
        
        print(f"{'METODA':<25}  {'TOTAL':<8}  {'RED':<8}  {'GREEN':<8}  {'BLUE':<8}")
        print(f"{'Obraz wejściowy':<25}  {ent_orig_total:.4f}    {ent_orig_r:.4f}    {ent_orig_g:.4f}    {ent_orig_b:.4f}")
        print("-"*60)
        diff_results = jpeg_ls_predictors(img_arr)
        results_summary = []
        for name, diff_map in diff_results.items():
            e_total = calculate_entropy(diff_map)
            e_r = calculate_entropy(diff_map[:,:,0])
            e_g = calculate_entropy(diff_map[:,:,1])
            e_b = calculate_entropy(diff_map[:,:,2])
            print(f"{name:<25}  {e_total:.4f}    {e_r:.4f}    {e_g:.4f}    {e_b:.4f}")
            
            results_summary.append({
                "name": name,
                "total": e_total,
                "r": e_r,
                "g": e_g,
                "b": e_b
            })        
        best_total = min(results_summary, key=lambda x: x['total'])
        best_r = min(results_summary, key=lambda x: x['r'])
        best_g = min(results_summary, key=lambda x: x['g'])
        best_b = min(results_summary, key=lambda x: x['b'])
        
        print("WYNIKI OPTYMALNE (Najmniejsza entropia):")
        print(f"Cały obraz: {best_total['name']} ({best_total['total']:.4f})")
        print(f"Kanał R:    {best_r['name']} ({best_r['r']:.4f})")
        print(f"Kanał G:    {best_g['name']} ({best_g['g']:.4f})")
        print(f"Kanał B:    {best_b['name']} ({best_b['b']:.4f})")

    except Exception as e:
        print(f"Wystąpił błąd: {e}")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Użycie: python program.py nazwa_pliku.tga")
    else:
        analyze_image(sys.argv[1])