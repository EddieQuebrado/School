all: pointer
	./pointer

pointer: pointer.o
	gcc -o pointer pointer.o -g -Wall -std=c99

pointer.o: pointer.c makefile
	gcc -c pointer.c -g -Wall -DX=25 -DY=15

clean:
	rm -f pointer pointer.o
