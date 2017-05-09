#!/bin/bash

if [ -e "col.make" ]; then
  rm "col.make"
fi

if [ -e "cstruct_gen.h" ]; then
  rm "cstruct_gen.h"
fi

DATE=`date '+%A %W %Y %X'`
echo "# collection generation makefile" >> "col.make"
echo "# created by Michael van Dyk" >> "col.make"
echo "# generated: $DATE" >> "col.make"
echo "# DO NOT MODIFY" >> "col.make"
echo >> "col.make"

echo "generate: all" >> "col.make"
echo >> "col.make"

echo "// cstruct_gen.h header" >> "cstruct_gen.h"
echo "// created by Michael van Dyk" >> "cstruct_gen.h"
echo "// generated: $DATE" >> "cstruct_gen.h"
echo "// DO NOT MODIFY" >> "cstruct_gen.h"

LIST=

while read line 
do
  arr=($line)
  case "${arr[1]}" in
    "queue" | "stack" | "deque" | "list" | "uset" | "sset")
      echo "${arr[0]}.h: ${arr[1]}.template.h" >> "col.make"
      echo "	./colgen.sh $line > ${arr[0]}.h" >> "col.make"
      echo >> "col.make"

      echo "#include \"${arr[0]}.h\"" >> "cstruct_gen.h"

      LIST="${LIST[@]} ${arr[0]}.h"
    ;;

    *)
      echo "invalid line in colgen.lst: $line" >&2

  esac
done < ./colgen.lst

echo "all: ${LIST[@]}" >> "col.make"
