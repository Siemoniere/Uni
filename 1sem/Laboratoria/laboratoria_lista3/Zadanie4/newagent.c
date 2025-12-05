#include <stdio.h>
#include "agents.h"
struct agent newagent(int x, int y) {
	struct agent new_agent ={x, y};
	return new_agent;
};
