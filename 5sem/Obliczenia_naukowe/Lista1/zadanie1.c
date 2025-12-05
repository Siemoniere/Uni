//zwróć wartości epsilona maszynowego i MAX dla typów float16, float32 i float64
#include <stdio.h>
#include <float.h>
#include <stdint.h>
#include <math.h>
int main() {

    printf("float32:\n");
    printf("  epsilon: %e\n", FLT_EPSILON);
    printf("  max: %e\n\n", FLT_MAX);

    printf("float64:\n");
    printf("  epsilon: %e\n", DBL_EPSILON);
    printf("  max: %e\n", DBL_MAX);

    return 0;
}