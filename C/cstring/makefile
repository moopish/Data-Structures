main: main.o cstring.o
	gcc -o test main.o cstring.o

main.o: main.c cstring.c cstring.h
	gcc -o main.o -c main.c

cstring.o: cstring.c cstring.h
	gcc -o cstring.o -c cstring.c
