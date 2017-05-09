// arraystack.h
// by Michael van Dyk
// Generated Tuesday 19 2017 03:28:27 AM
// 
// Do not modify
//

#include <stdbool.h>
#include <stdint.h>

typedef struct def_arraystack arraystack;

bool     arraystack_push (arraystack * stack, void * item);
void*    arraystack_peek (arraystack * stack);
void*    arraystack_pop  (arraystack * stack);

uint32_t arraystack_size (arraystack * stack);
