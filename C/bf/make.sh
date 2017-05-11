#!/bin/bash

CNVRT=bf2c.sh
BCODE=bf
FILES=$(find *.$BCODE | cut -f1 -d'.')
T_EXE=tester
TESTS=

for FILE in $FILES
do
  TESTS="$TESTS $FILE.$T_EXE"
done

echo "build: $TESTS" > makefile
echo "" >> makefile
echo "clean:" >> makefile
echo "	rm -f $TESTS *.c" >> makefile
echo "" >> makefile
echo "remakefile:" >> makefile
echo "	./make.sh" >> makefile

for FILE in $FILES
do
  echo "" >> makefile
  echo "$FILE.$T_EXE: $FILE.$BCODE $CNVRT" >> makefile
  echo "	./$CNVRT $FILE.$BCODE" >> makefile
  echo "	gcc -o $FILE.$T_EXE $FILE.c" >> makefile
done
