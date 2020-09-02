CFLAGS = -Wall
OBJS = foo.o
HDRS =
CC = gcc

%.o : %.c $(HDRS)
	$(CC) $(CFLAGS) -c $< -o $@

all:	missingPrototype usingPrototype badCall usingHeader

missingPrototype: missingPrototype.o $(OBJS)
	$(CC) $(CFLAGS) $^ -o $@

usingPrototype: usingPrototype.o $(OBJS)
	$(CC) $(CFLAGS) $^ -o $@

badCall: badCall.o $(OBJS)
	$(CC) $(CFLAGS) $^ -o $@

usingHeader: usingHeader.o fooV2.o
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm -f *.o *.s $(EXES) *~