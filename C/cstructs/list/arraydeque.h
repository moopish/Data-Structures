// arraydeque.h
// by Michael van Dyk
// Generated Tuesday 19 2017 03:28:27 AM
// 
// Do not modify
//

#include <stdbool.h>

typedef struct def_arraydeque arraydeque;

//Queue functions
bool     arraydeque_add          (arraydeque * deque, void * item);
void*    arraydeque_get          (arraydeque * deque);
void*    arraydeque_remove       (arraydeque * deque);

//Stack functions
bool     arraydeque_push         (arraydeque * deque, void * item);
void*    arraydeque_peek         (arraydeque * deque);
void*    arraydeque_pop          (arraydeque * deque);

//Deque functions
bool     arraydeque_add_first    (arraydeque * deque, void * item);
bool     arraydeque_add_last     (arraydeque * deque, void * item);
void*    arraydeque_get_first    (arraydeque * deque);
void*    arraydeque_get_last     (arraydeque * deque);
void*    arraydeque_remove_first (arraydeque * deque);
void*    arraydeque_remove_last  (arraydeque * deque);

uint32_t arraydeque_size         (arraydeque * deque);

//START OF DEFAULTS

inline
bool arraydeque_push (arraydeque * deque, void * item)
{
    return arraydeque_add_first(deque, item);
}

inline
void * arraydeque_peek (arraydeque * deque)
{
    return arraydeque_get_first(deque);
}

inline
void * arraydeque_pop (arraydeque * deque)
{
    return arraydeque_remove_first(deque);
}

inline
bool arraydeque_add (arraydeque * deque, void * item)
{
    return arraydeque_add_last(deque, item);
}

inline
void * arraydeque_get (arraydeque * deque)
{
    return arraydeque_get_first(deque);
}

inline
void * arraydeque_remove (arraydeque * deque)
{
    return arraydeque_remove_first(deque);
}

//END OF DEFAULTS
