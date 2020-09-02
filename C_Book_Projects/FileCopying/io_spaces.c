#include <stdio.h>
#include <stdlib.h>
#define _GNU_SOURCE

#define NONBLANK '-'

int main(){
	int c, lastc;
	lastc = NONBLANK;
	FILE * fptr;
	
	fptr = fopen("ntext.txt", "r");
	if(fptr == NULL)
		exit(EXIT_FAILURE);

	
	while((c=fgetc(fptr)) != EOF){
		if(c == ' ') {
			if(lastc != ' '){
				putchar(c);
			}
		} else {
			putchar(c);
		}
		lastc = c;
	}

	exit(EXIT_SUCCESS);
}
