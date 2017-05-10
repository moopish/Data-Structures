test: main
	valgrind ./cstring.tester

testlib: buildlib main.o
	gcc -o cstring.tester main.o libcstring.a
	valgrind ./cstring.tester

buildlib: cstring.o
	ar ruv libcstring.a cstring.o
	ranlib libcstring.a 

main: main.o cstring.o
	gcc -o cstring.tester main.o cstring.o

main.o: main.c cstring.c cstring.h
	gcc -o main.o -c main.c

cstring.o: cstring.c cstring.h
	gcc -o cstring.o -c cstring.c

clean:
	rm -f cstring.tester *.o *.a
