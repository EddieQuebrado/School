#include <stdio.h>
#include <stdlib.h>

int main(){
	FILE *fptr, *fptr2;
	char filename[100], c;
	printf("Enter the filename to open for reading \n");
	scanf("%s", filename);
	//Open one file for reading
	fptr = fopen(filename, "r");
	if(fptr == NULL){
		printf("Cannot open file %s\n", filename);
		exit(0);
	}
	printf("Enter the filename to open for writing\n");
	scanf("%s", filename);
	//Open another file for writing
	fptr2 = fopen(filename, "w");
	if(fptr2 == NULL){
		printf("Cannot open file %s\n", filename);
		exit(0);
	}
	//Read contents from first file and write into second file
	c = fgetc(fptr);
	while(c != EOF){
		fputc(c, fptr2);
		c = fgetc(fptr);
	}
	printf("\nContents copied to %s", filename);
	fclose(fptr);
	fclose(fptr2);
	return 0;
}
