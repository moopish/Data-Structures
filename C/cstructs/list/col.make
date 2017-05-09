# collection generation makefile
# created by Michael van Dyk
# generated: Tuesday 19 2017 03:19:17 AM
# DO NOT MODIFY

generate: all

arraystack.h: stack.template.h
	./colgen.sh arraystack stack > arraystack.h

arrayqueue.h: queue.template.h
	./colgen.sh arrayqueue queue > arrayqueue.h

arraydeque.h: deque.template.h
	./colgen.sh arraydeque deque default > arraydeque.h

arraylist.h: list.template.h
	./colgen.sh arraylist list default > arraylist.h

all:  arraystack.h arrayqueue.h arraydeque.h arraylist.h
