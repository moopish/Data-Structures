#!/bin/bash

if [ -e "col.make" ]; then
  rm "col.make"
fi

DATE=`date '+%A %W %Y %X'`
echo "# collection generation makefile" >> "col.make"
echo "# created by Michael van Dyk" >> "col.make"
echo "# generated: $DATE" >> "col.make"
echo >> "col.make"

echo "generate: all" >> "col.make"
echo >> "col.make"

LIST=

while read line 
do
  arr=($line)
  case "${arr[1]}" in
    "queue" | "stack" | "deque" | "list" | "uset" | "sset")
      echo "${arr[0]}.h: ${arr[1]}.h.template" >> "col.make"
      echo "	./colgen.sh $line > ${arr[0]}.h" >> "col.make"
      echo >> "col.make"

      LIST="${LIST[@]} ${arr[0]}.h"
    ;;

    *)
      echo "invalid line in colgen.lst: $line" >&2

  esac
done < ./colgen.lst

echo "all: ${LIST[@]}" >> "col.make"
