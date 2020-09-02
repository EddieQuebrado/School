#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>

/* Outputs the amount of newlines, spaces, and tabs to user*/
int main(){
	FILE * fptr1;
	int space, newline, tab;
	space = 0;
	newline = 0;
	tab = 0;
	int c;

	fptr1 = fopen("text.txt","r");
	if(fptr1 == NULL)
		exit(EXIT_FAILURE);

	while((c = fgetc(fptr1)) != EOF){
		if(c== '\n'){
			newline++;
		} else if (c == ' ') {
			space++;
		} else if (c == '\t') {
			tab++;
		}	
	}
	printf("Newline: %d, Space: %d, Tab: %d, ", newline, space, tab);
	exit(EXIT_SUCCESS);
}
