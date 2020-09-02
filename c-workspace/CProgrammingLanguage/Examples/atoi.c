/*
 * int isalnum(int c) checks whether the passed character is alphanumeric
 * int isalpha(int c) checks whether the passed character is alphabetic
 * int iscntrl(int c) checks whether the passed character is control character
 * int isdigit(int c) checks whether the passed character is decimal digit
 * int isgraph(int c) checks whether the passed character has graphical representation 
 * using locale
 * int islower(int c) checks whether the passed character is lowercase letter
 * int isprint(int c) checks whether the passed character is printable
 * int ispunct(int c) checks whether the passed character is a punctuation character
 * int isspace(int c) checks whether the passed character is white-space
 * int isupper(int c) checks whether the passed character is an uppercase letter
 * int isxdigit(int c) checks whether the passed character is a hexadecimal digit
 */
#include <stdio.h>
#include <ctype.h>
#include <termios.h>

/* atoi: convert s to integer; version 2 */
int atoi(char s[]){
	int i, n, sign;

	for(i = 0; isspace(s[i]); i++) /* skip white space */
		;
	sign = (s[i] == '-') ? -1 : 1;
	if(s[i] == '+' || s[i] == '-') /* skip sign */
		i++;
	for(n = 0; isdigit(s[i]); i++)
		n = 10 * n + (s[i] - '0');
	return sign * n;
}

int main(){
	tty_mode(0);		/* save current terminal mode */
	set_terminal_raw();	/* set -icanon, -echo */
	interact();		/* interact with user */
	tty_mode(1);		/* restore terminal to the way it was */
	return 0;		/* 0 means the program exited normally */
}

void interact(){
	while(1){
		printf("\nPlease enter a choice: \n1)quit\n2)Something");
		switch(getchar()){
			case 'q': return;
			case '2': {
				printf("Hi\n");
				break;
			}
			case EOF: return;
		}
	}
}

void set_terminal_raw(){
	struct termios ttystate;		
	tcgetattr(0, &ttystate);		/* read current setting */
	ttystate.c_lflag	&= ~ICANON;	/* no buffering */
	ttystate.c_lflag	&= ~ECHO;	/* no echo either */
	ttystate.c_cc[VMIN]	 = 1;		/* get 1 char at a time */
	tcsetattr(0,TCSANOW, &ttystate);	/* install settings */
}

struct tty_mode( int operation ){
	static struct termios original_mode;
	if( operation == 0 ){
		tcgetattr(0, &original_mode );
	} else {
		return tcsetattr(0, TCSANOW, &original_mode );
	}
}
