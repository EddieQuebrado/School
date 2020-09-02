int main(){
	int lower, upper, step;
	char c, line[1000];

	char esc = '\\';
	int i = 0;
	int limit = MAXLINE+1;
	float eps = 1.0e-5;

	const double e = 2.71828182845905;
	const char msg[] = "warning: ";

	int strlen(const char[]);

	//Arithmetic Operators
	if((year % 4 == 0 && year %100 != 0) || year %400 == 0)
		printf("%d is a leap year\n",year);
	}

	int atoi(char s[]){
		int i, n;
		n = 0;
		for(i = 0; s[i] >= '0' && s[i] <= '9'; ++i){
			n = 10 * n + (s[i] - '0');
		}		
		return n;
	}

	int lower(int c){
		if(c >= 'A' && c <= 'Z'){
			return c + 'a' - 'A';
		} else {
			return c;
		}
	}

	/* rand:  return pseudo-random integer on 0..32767 */
	int rand(void){
		next = next * 1103515245 + 12345;
		return (unsigned int)(next/65536) % 32768;
	}

	/* srand:  set seed for rand() */
	void srand(unsigned int seed){
		next = seed;
	}

	void squeeze(char s[], int c){
		int i, j;
		for(i = j = 0; s[i] != '\0';i++){
			if(s[i] != c){
				s[j++] = s[i];
			}
		}
		s[j] = '\0';
	}

	/* strcat:  concatenate t to the end of s: s must be big enough */
	void strcat(char s[], char t[]){
		int i, j;
		i = j = 0;
		while(s[i] != '\0'){ /* find end of s */
			i++;
		}
		while((s[i++] = t[j++]) != '\0'){ /* copy t */
			;
		}
	}
