#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <cmath>
#include <algorithm>
#include <cstdint>
#include <iomanip>

#pragma pack(push, 1)
struct TGAHeader {
    uint8_t  idLength;
    uint8_t  colorMapType;
    uint8_t  imageType;
    uint16_t colorMapOrigin;
    uint16_t colorMapLength;
    uint8_t  colorMapDepth;
    uint16_t xOrigin;
    uint16_t yOrigin;
    uint16_t width;
    uint16_t height;
    uint8_t  pixelDepth;
    uint8_t  imageDescriptor;
};
#pragma pack(pop)

struct Pixel {
    uint8_t b, g, r;
};

struct Centroid {
    double b, g, r;
};

double taxicabDistance(const Pixel& p, const Centroid& c) {
    return std::abs(p.b - c.b) + std::abs(p.g - c.g) + std::abs(p.r - c.r);
}

std::vector<Pixel> readTGA(const std::string& filename, int& width, int& height, TGAHeader& header) {
    std::ifstream file(filename, std::ios::binary);
    if (!file) {
        std::cerr << "Blad: Nie mozna otworzyc pliku wejsciowego." << std::endl;
        exit(1);
    }

    file.read(reinterpret_cast<char*>(&header), sizeof(TGAHeader));

    if (header.imageType != 2) {
        std::cerr << "Blad: Obslugiwane sa tylko nieskompresowane obrazy RGB TGA." << std::endl;
        exit(1);
    }
    if (header.pixelDepth != 24) {
        std::cerr << "Blad: Obslugiwane sa tylko obrazy 24-bitowe." << std::endl;
        exit(1);
    }

    width = header.width;
    height = header.height;
    size_t dataSize = width * height;
    std::vector<Pixel> pixels(dataSize);

    file.seekg(header.idLength, std::ios::cur);
    
    file.read(reinterpret_cast<char*>(pixels.data()), dataSize * sizeof(Pixel));
    return pixels;
}

void writeTGA(const std::string& filename, const TGAHeader& header, const std::vector<Pixel>& pixels) {
    std::ofstream file(filename, std::ios::binary);
    if (!file) {
        std::cerr << "Blad: Nie mozna otworzyc pliku wyjsciowego." << std::endl;
        exit(1);
    }
    file.write(reinterpret_cast<const char*>(&header), sizeof(TGAHeader));
    file.write(reinterpret_cast<const char*>(pixels.data()), pixels.size() * sizeof(Pixel));
}

std::vector<Centroid> runLBG(const std::vector<Pixel>& pixels, int targetColors) {
    Centroid c0 = {0, 0, 0};
    for (const auto& p : pixels) {
        c0.b += p.b; c0.g += p.g; c0.r += p.r;
    }
    c0.b /= pixels.size(); c0.g /= pixels.size(); c0.r /= pixels.size();

    std::vector<Centroid> codebook;
    codebook.push_back(c0);

    while (codebook.size() < (size_t)targetColors) {
        std::vector<Centroid> nextCodebook;
        const double epsilon = 1.0; 

        for (const auto& c : codebook) {
            nextCodebook.push_back({c.b - epsilon, c.g - epsilon, c.r - epsilon});
            nextCodebook.push_back({c.b + epsilon, c.g + epsilon, c.r + epsilon});
        }
        codebook = nextCodebook;

        double prevDist = 1e9;
        for (int iter = 0; iter < 100; ++iter) {
            std::vector<Centroid> newCentroids(codebook.size(), {0, 0, 0});
            std::vector<int> counts(codebook.size(), 0);
            double totalDist = 0;

            for (const auto& p : pixels) {
                int bestIdx = 0;
                double minDist = 1e9;
                for (size_t i = 0; i < codebook.size(); ++i) {
                    double d = taxicabDistance(p, codebook[i]);
                    if (d < minDist) {
                        minDist = d;
                        bestIdx = i;
                    }
                }
                newCentroids[bestIdx].b += p.b;
                newCentroids[bestIdx].g += p.g;
                newCentroids[bestIdx].r += p.r;
                counts[bestIdx]++;
                totalDist += minDist;
            }

            for (size_t i = 0; i < codebook.size(); ++i) {
                if (counts[i] > 0) {
                    codebook[i].b = newCentroids[i].b / counts[i];
                    codebook[i].g = newCentroids[i].g / counts[i];
                    codebook[i].r = newCentroids[i].r / counts[i];
                }
            }

            if (std::abs(prevDist - totalDist) < 1.0) break;
            prevDist = totalDist;
        }
    }
    
    if (codebook.size() > (size_t)targetColors) {
        codebook.resize(targetColors);
    }
    
    return codebook;
}

int main(int argc, char* argv[]) {
    if (argc != 4) {
        std::cout << "Uzycie: " << argv[0] << " <wejscie.tga> <wyjscie.tga> <liczba_bitow>" << std::endl;
        return 1;
    }

    std::string inputFile = argv[1];
    std::string outputFile = argv[2];
    int bits = std::stoi(argv[3]);

    if (bits < 0 || bits > 24) {
        std::cerr << "Liczba bitow musi byc miedzy 0 a 24." << std::endl;
        return 1;
    }

    long long numColors = 1LL << bits; 
    
    int width, height;
    TGAHeader header;
    std::vector<Pixel> pixels = readTGA(inputFile, width, height, header);

    std::vector<Pixel> outputPixels = pixels;

    if (bits < 24) {
        std::cout << "Uruchamianie algorytmu LBG dla " << numColors << " kolorow..." << std::endl;
        std::vector<Centroid> codebook = runLBG(pixels, (int)numColors);

        for (auto& p : outputPixels) {
            int bestIdx = 0;
            double minDist = 1e9;
            for (size_t i = 0; i < codebook.size(); ++i) {
                double d = taxicabDistance(p, codebook[i]);
                if (d < minDist) {
                    minDist = d;
                    bestIdx = i;
                }
            }
            p.b = (uint8_t)std::min(255.0, std::max(0.0, codebook[bestIdx].b));
            p.g = (uint8_t)std::min(255.0, std::max(0.0, codebook[bestIdx].g));
            p.r = (uint8_t)std::min(255.0, std::max(0.0, codebook[bestIdx].r));
        }
    }

    writeTGA(outputFile, header, outputPixels);

    double mse = 0.0;
    double signalPower = 0.0;

    for (size_t i = 0; i < pixels.size(); ++i) {
        double errB = (double)pixels[i].b - outputPixels[i].b;
        double errG = (double)pixels[i].g - outputPixels[i].g;
        double errR = (double)pixels[i].r - outputPixels[i].r;
        
        mse += (errB*errB + errG*errG + errR*errR);

        signalPower += (pixels[i].b * pixels[i].b + 
                        pixels[i].g * pixels[i].g + 
                        pixels[i].r * pixels[i].r);
    }

    mse /= pixels.size();
    signalPower /= pixels.size();

    double snr = 0.0;
    if (mse > 0.000001) {
        snr = 10.0 * log10(signalPower / mse);
    } else {
        snr = std::numeric_limits<double>::infinity();
    }

    std::cout << "Przetwarzanie zakonczone." << std::endl;
    std::cout << "MSE (Bład sredniokwadratowy): " << mse << std::endl;
    std::cout << "SNR (Stosunek sygnalu do szumu): " << snr << " dB" << std::endl;

    return 0;
}