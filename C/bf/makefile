build:  addcopy.tester addmove.tester copy.tester hello.tester inputtest.tester mod2.tester move.tester subcopy.tester submove.tester

clean:
	rm -f  addcopy.tester addmove.tester copy.tester hello.tester inputtest.tester mod2.tester move.tester subcopy.tester submove.tester *.c

remakefile:
	./make.sh

addcopy.tester: addcopy.bf bf2c.sh
	./bf2c.sh addcopy.bf
	gcc -o addcopy.tester addcopy.c

addmove.tester: addmove.bf bf2c.sh
	./bf2c.sh addmove.bf
	gcc -o addmove.tester addmove.c

copy.tester: copy.bf bf2c.sh
	./bf2c.sh copy.bf
	gcc -o copy.tester copy.c

hello.tester: hello.bf bf2c.sh
	./bf2c.sh hello.bf
	gcc -o hello.tester hello.c

inputtest.tester: inputtest.bf bf2c.sh
	./bf2c.sh inputtest.bf
	gcc -o inputtest.tester inputtest.c

mod2.tester: mod2.bf bf2c.sh
	./bf2c.sh mod2.bf
	gcc -o mod2.tester mod2.c

move.tester: move.bf bf2c.sh
	./bf2c.sh move.bf
	gcc -o move.tester move.c

subcopy.tester: subcopy.bf bf2c.sh
	./bf2c.sh subcopy.bf
	gcc -o subcopy.tester subcopy.c

submove.tester: submove.bf bf2c.sh
	./bf2c.sh submove.bf
	gcc -o submove.tester submove.c
