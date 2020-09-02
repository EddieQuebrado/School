#include <stdio.h>

int main() {
	int c, i, nwhite, nother, ndigit[10];

	nwhite = nother = 0;
	for(i = 0; i < 10; i++){
		ndigit[i] = 0;
	} while ((c = getchar()) != EOF){
		switch(c){
		case '0': case '1': case '2': case '3': case '4':
		case '5': case '6': case '7': case '8': case '9':
			//incase of occurence of digit between the boundaries of 0 - 10
			ndigit[c-'0']++;
			//increase the amount of occurences of the specificied digit
			break;
		case ' ':
			//incase of occurence of no character
		case '\n':
			//incase of new line character
		case '\t':
			//incase of new tab press
			nwhite++;
			break;
		default:
			//incase of other character
			nother++;
			break;
		}
	}
	printf("digits =");
	for(i = 0; i < 10; i++){
		printf(" %d", ndigit[i]);
		printf(", white space = %d, other = %d\n", nwhite, nother);
	}
	return 0;
}

