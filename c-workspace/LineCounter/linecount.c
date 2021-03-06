#include <stdio.h>
#define MAXLINE 1000 /* maximum input line size */

int max;	
char line[MAXLINE];	
char longest[MAXLINE];	

int getline(void);
void copy(void);

/* print longest input line; specialized version */
int main(){
	int len;
	extern int max;
	extern char longest[];
		
	max = 0;
	while((len = getline()) > 0){
		if(len > max){
			max = len;
			copy();
		}
	}
	if(max > 0){
		printf("%s", longest);
	}
	return 0;
}

/* getline: specialized version */
int getline(void){
	int c, i;
	extern char line[];

	for(i = 0; i < MAXLINE-1 && (c = getchar()) != EOF && c != '\n'; ++i){
		line[i] = c;
	}
	if(c == '\n'){
		line[i] = c;
		++i;
	}
	line[i] = '\0';
	return i;
}

/* copy: specialized version */
void copy(void){
	int i;
	extern char line[], longest[];

	i = 0;
	while((longest[i] = line[i]) != '\0'){
		++i;
	}
}

/* count lines in input 
int main(){
	int c, n1;
	int n2, n3;
	n1 = 0;
	while((c = getchar()) != EOF){
		if(c == '\n'){
			++n1;
		}
		if (c == '\t') {
			++n2;
		}
		if (c == ' '){
			++n3;
		} 
	}
	printf("Number of lines: %d\n", n1);
	printf("Number of tabs: %d\n", n2);
	printf("Number of spaces: %d\n", n3);
}
*/

