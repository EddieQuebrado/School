#include <stdio.h>
#include <stdlib.h>

#define IN 1
#define OUT 0

int main(){
	FILE * fptr;
	int c, nl, nw, nc, state;
	
	state = OUT;
	nl = nw = nc = 0;
	fptr = fopen("text.txt", "r");
	if(fptr == NULL)
		exit(EXIT_FAILURE);

	while((c = fgetc(fptr)) != EOF){
		++nc;
		if(c == '\n')
			++nl;
		if(c == ' ' || c == '\n' || c == '\t')
			state = OUT;
		else if (state == OUT){
			state = IN;
			++nw;
		}
	}
	printf("%d %d %d\n", nl, nw, nc);
}
