#!/bin/bash

if [ "$#" -ne 2 ]; then
  echo "Usage: $0 struct_name type" >&2
  exit 1
fi

NAME=$1
DATE=`date '+%A %W %Y %X'`
case "$2" in
  "queue" | "stack" | "deque" | "list" | "uset" | "sset")
    sed -e 's/${STRUCT}/'$NAME'/g' -e 's/${DATE}/'"$DATE"'/g' $2.h.template
  ;;

  *)
    echo "$2 is not a valid type: choose from 'queue', 'stack', 'deque', 'list', 'uset' or 'sset'" >&2
    exit 1
  ;;
  
esac

