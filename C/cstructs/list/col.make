# collection generation makefile
# created by Michael van Dyk
# generated: Tuesday 19 2017 01:38:15 AM

generate: all

arraystack.h: stack.h.template
	./colgen.sh arraystack stack > arraystack.h

arrayqueue.h: queue.h.template
	./colgen.sh arrayqueue queue > arrayqueue.h

arraydeque.h: deque.h.template
	./colgen.sh arraydeque deque > arraydeque.h

arraylist.h: list.h.template
	./colgen.sh arraylist list > arraylist.h

all:  arraystack.h arrayqueue.h arraydeque.h arraylist.h
