#!/bin/bash

if [ "$2" == "" ]; then
  BUFF_SIZE=1024
else
  BUFF_SIZE=$2
fi

if [ -e "$1" ]; then
  filename=$(basename "$1")
  filename="${filename%.*}"

  echo "// $filename.c" > $filename.c
  echo "" >> $filename.c
  echo "#include <stdio.h>" >> $filename.c
  echo "" >> $filename.c
  echo "int main(void) {" >> $filename.c
  echo "    unsigned char   array[$BUFF_SIZE] = {0};" >> $filename.c
  echo "    unsigned char * ptr = array;" >> $filename.c 

  while read -n1 c; do
    case "$c" in
      ">")
        echo "    ++ptr;" >> $filename.c
        ;;

      "<")
        echo "    --ptr;" >> $filename.c
        ;;

      "+")
        echo "    ++*ptr;" >> $filename.c
        ;;

      "-")
        echo "    --*ptr;" >> $filename.c
        ;;

      ".")
        echo "    printf(\"%d \",*ptr);" >> $filename.c
        ;;
      
      ",")
        echo "    *ptr = getchar();" >> $filename.c
        ;;

      "[")
        echo "    while (*ptr) {" >> $filename.c
        ;;

      "]")
        echo "    }" >> $filename.c
        ;;

    esac
  done < $1

  echo "    printf(\"\n\");" >> $filename.c
  echo "}" >> $filename.c

  echo "$filename"
fi
