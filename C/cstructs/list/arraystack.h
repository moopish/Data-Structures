// arraystack.h
// by Michael van Dyk
// Generated Tuesday 19 2017 01:38:17 AM
// 
// Do not modify
//

#include <stdbool.h>

typedef struct def_arraystack arraystack;

bool  arraystack_push (arraystack * queue, void * item);
void* arraystack_peek (arraystack * queue);
void* arraystack_pop  (arraystack * queue);
