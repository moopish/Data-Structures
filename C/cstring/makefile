test: main
	valgrind ./cstring.tester

testlib: buildlib cstring.tester.o
	gcc -o cstring.tester cstring.tester.o libcstring.a
	valgrind ./cstring.tester

buildlib: cstring.o
	ar ruv libcstring.a cstring.o
	ranlib libcstring.a 

main: cstring.tester.o cstring.o
	gcc -o cstring.tester cstring.tester.o cstring.o

main.o: main.c cstring.c cstring.h
	gcc -o cstring.tester.o -c cstring.tester.c

cstring.o: cstring.c cstring.h
	gcc -o cstring.o -c cstring.c

clean:
	rm -f cstring.tester *.o *.a
