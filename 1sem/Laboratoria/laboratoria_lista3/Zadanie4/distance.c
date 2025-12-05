#include <stdio.h>
#include <math.h>
#include "agents.h"
double distance(struct agent a1, struct agent a2){
	double distance_x=a1.x-a2.x;
	double distance_y=a1.y-a2.y;
	double distance_xy=sqrt(distance_x*distance_x+distance_y*distance_y);
	return distance_xy;}
