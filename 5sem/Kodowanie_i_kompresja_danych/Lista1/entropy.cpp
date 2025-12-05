#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;

void calculateEntropy(int counts[], int total, double &entropy, double &cond_entropy, int n, int pairs[][256])
{
    for (int i = 0; i < (1 << n); i++)
    {
        if (counts[i] > 0)
        {
            double p = static_cast<double>(counts[i]) / total;
            entropy -= p * log2(p);
        }
    }

    cond_entropy = 0.0;
    for (int i = 0; i < (1 << n); i++) {
        if (counts[i] > 0) {
            double px = static_cast<double>(counts[i]) / total;
            double hyx = 0.0;
            for (int j = 0; j < (1 << n); j++) {
                if (pairs[i][j] > 0) {
                    double pyx = static_cast<double>(pairs[i][j]) / counts[i];
                    hyx -= pyx * log2(pyx);
                }
            }
            cond_entropy += px * hyx;
        }
    }
}

int main(int argc, char *argv[])
{
    const int n = 8;

    if (argc != 2)
    {
        cerr << "Usage: " << argv[0] << " <input file>" << endl;
        return 1;
    }

    ifstream inputFile(argv[1], ios::binary);
    if (!inputFile)
    {
        cerr << "Error opening file: " << argv[1] << endl;
        return 1;
    }

    int counts[1 << n] = {0};
    int pairs[1 << n][1 << n] = {0};

    unsigned char prev = 0;
    unsigned char curr;
    int count = 0;
    double entropy = 0.0;
    double cond_entropy = 0.0;

    while (inputFile.read(reinterpret_cast<char *>(&curr), 1))
    {
        counts[curr]++;
        pairs[prev][curr]++;
        prev = curr;
        count++;
    }

    inputFile.close();

    calculateEntropy(counts, count, entropy, cond_entropy, n, pairs);

    cout << "Entropy: " << entropy << endl;
    cout << "Conditional Entropy: " << cond_entropy << endl;
    cout << "Entropy difference: " << (entropy - cond_entropy) << endl;

    return 0;
}
