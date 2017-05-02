EXEC_EXT=tester

all: count insertion

count: count_tester.o
	gcc -o count.$(EXEC_EXT) count_tester.o count.o

count.o: count.c sort.h
	gcc -o count.o -c count.c

count_tester.o: count_tester.c count.o
	gcc -o count_tester.o -c count_tester.c

insertion: insertion_tester.o
	gcc -o insertion.$(EXEC_EXT) insertion_tester.o insertion.o

insertion.o: insertion.c sort.h
	gcc -o insertion.o -c insertion.c

insertion_tester.o: insertion_tester.c insertion.o
	gcc -o insertion_tester.o -c insertion_tester.c

clean:
	rm *.$(EXEC_EXT) *.o
