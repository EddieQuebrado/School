#include <stdio.h>

/* copy input to output; 1st version */
int main(){
	int c;
	int i = 0;
	c = getchar();
	while((c = getchar()) != EOF) {
		if(c == 'i'){
			i++;
		}
		putchar(c);
	}	
	printf("Occurences of I in sentence: %d\n",i);
}
