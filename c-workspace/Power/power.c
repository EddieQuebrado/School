#include <stdio.h>

/* test power function */
int main(){
	int i, num;
	printf("Please enter number: ");
	scanf("%d",&num);
	for(i = 0; i < 10; ++i){
		printf("%d raised to the power of %d computes to %d\n",num, i, power(num,i));
	}
	return 0;
}

/* power: raise base to n-th power; n >= 0  */
/*        (old-style version) */
int power(int base, int n){
	int p;
	
	for(p = 1; n > 0; --n){
		p = p * base;
	}
	return p;
}
