#------------------------------------------------------------------------------
#
# Makefile --- One Makefile to build either the release or debug versions!
#
# Usage
#	make all		Build the released product
#	make debug		Build the product suitable for debugging
#
# Notes
#	Make has a built-in "pattern rule" that provides a recipe for building
#	a *.o file from a *.c file.  It looks something like this:
#	%.o : %.c
#		$(CC) -c $(CFLAGS) $< -o $@
#	...where $< refers to the *.c file and $@ refers to the target file.
#
#	This makefile defines CFLAGS to issue all warnings when anything is
#	compiled.  It has a familiar all target that asks make to bring the
#	executable program, main, up-to-date.  But it has two (!) debug 
#	rules for the same target, debug.  The first one will cause make
#	to append the debugging options -g and -Og to CFLAGS.  The second
#	ask make to build the executable main.
#
#	Limitations
#	You'll need to do a make clean to get rid of old object files if 
#	you've changed your mind about release vs. debug executables.
#
#	Authors
#	Eddie Quebrado
#	01/17/2018 Epoch....................................................jrc
#
#------------------------------------------------------------------------------
CFLAGS = -Wall -std=c99
all:	linecount
debug:	CFLAGS += -g -O0
debug:	linecount
main:	linecount.o Makefile
	gcc $(CFLAGS) $< -o $@
mail.o: linecount.c Makefile
clean:
	rm *.o linecount 
