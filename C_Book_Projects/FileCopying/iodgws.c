#include <stdio.h>
#include <stdlib.h>

int main(){
	FILE * fptr;
	int c, i, nwhite, nother;
	int ndigit[10];

	nwhite = nother = 0;
	for(i = 0; i < 10; ++i){
		ndigit[i] = 0;
	}

	fptr = fopen("digits.txt", "r");
	if(fptr == NULL){
		exit(EXIT_FAILURE);
	}

	while((c=fgetc(fptr)) != EOF){
		if(c >= '0' && c <= '9')
			++ndigit[c-'0'];
		else if (c == ' ' || c == '\n' || c == '\t')
			++nwhite;
		else
			++nother;
	}

	printf("digits =");
	for(i = 0; i < 10; ++i){
		printf(" %d", ndigit[i]);
	}
	printf(", white space = %d, other = %d\n", nwhite, nother);
}
