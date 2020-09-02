#include <stdio.h>

int main(){
	float fahr, celsius;
	int upper, lower, step;

	lower = 0;
	upper = 100;
	step = 1;

	celsius = lower;
	while(celsius <= upper){
		fahr = (celsius*1.8) + 32.0;
		printf("%6.1f\t%6.1f\n",celsius,fahr);
		celsius = celsius + step;
	}
}
