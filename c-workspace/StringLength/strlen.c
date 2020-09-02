#include <stdio.h>

/* strlen: returns length of s */
int strlength(char s[]){
	int i;
	i = 0;
	while(s[i] != '\0'){
		++i;
	}
	return i;
}

int main(){
	char s[] = {"supercalafragilisticexpealidocious"};
	int len = strlength(s);
	printf("%d\n",len);
	return len;
}
