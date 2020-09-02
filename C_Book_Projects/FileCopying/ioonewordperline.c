#include <stdio.h>
#include <stdlib.h>

int main(){
	FILE * fptr;
	int i;
	char s[100];
	fptr = fopen("text.txt", "r");
	if(fptr == NULL)
		exit(EXIT_FAILURE);

	fgets(s, 100, fptr);

	for(i = 0; s[i]!='\0'; i++) {
		printf("%c", s[i]);
		if(s[i]==' '){
			printf("\n");
		}
	}
}
