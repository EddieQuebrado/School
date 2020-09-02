#include <stdio.h>
#include <stdlib.h>

int main(){
	FILE * fptr;
	int i, c;

	fptr = fopen("/tmp/length.txt", "w+");
	if(fptr == NULL)
		exit(EXIT_FAILURE);
	int a, b, d;
	a = b = d = 0;

	while((c=fgetc(fptr))!= EOF){
		if(c == '\t'){
			fputs("\t",fptr);
		} else if (c == '\b'){
			fputs("\b",fptr);
		} else if (c == '\\') { 
			fputs("\\",fptr);
		} else {
			fputs("1", fptr);
		}
	}
	fclose(fptr);
}
