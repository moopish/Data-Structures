// arraylist.h
// by Michael van Dyk
// Generated Tuesday 19 2017 03:28:27 AM
// 
// Do not modify
//

#include <stdbool.h>
#include <stdint.h>

typedef struct def_arraylist arraylist;

//Queue functions
bool     arraylist_add          (arraylist * list, void * item);
void*    arraylist_get          (arraylist * list);
void*    arraylist_remove       (arraylist * list);

//Stack functions
bool     arraylist_push         (arraylist * list, void * item);
void*    arraylist_peek         (arraylist * list);
void*    arraylist_pop          (arraylist * list);

//Deque functions
bool     arraylist_add_first    (arraylist * list, void * item);
bool     arraylist_add_last     (arraylist * list, void * item);
void*    arraylist_get_first    (arraylist * list);
void*    arraylist_get_last     (arraylist * list);
void*    arraylist_remove_first (arraylist * list);
void*    arraylist_remove_last  (arraylist * list);

//List functions
bool     arraylist_add_index    (arraylist * list, void * item, uint32_t index);
void*    arraylist_get_index    (arraylist * list,              uint32_t index);
void*    arraylist_remove_index (arraylist * list,              uint32_t index);
void*    arraylist_set_index    (arraylist * list, void * item, uint32_t index);

uint32_t arraylist_size         (arraylist * list);

//START OF DEFAULTS

inline
bool arraylist_push (arraylist * list, void * item)
{
    return arraylist_add_first(list, item);
}

inline
void * arraylist_peek (arraylist * list)
{
    return arraylist_get_first(list);
}

inline
void * arraylist_pop (arraylist * list)
{
    return arraylist_remove_first(list);
}

inline
bool arraylist_add (arraylist * list, void * item)
{
    return arraylist_add_last(list, item);
}

inline
void * arraylist_get (arraylist * list)
{
    return arraylist_get_first(list);
}

inline
void * arraylist_remove (arraylist * list)
{
    return arraylist_remove_first(list);
}

inline
bool arraylist_add_first (arraylist * list, void * item)
{
    return arraylist_add_index(list, item, 0);
}

inline
void * arraylist_get_first (arraylist * list)
{
    return arraylist_get_index(list, 0);
}

inline
void * arraylist_remove_first (arraylist * list)
{
    return arraylist_remove_index(list, 0);
}

inline
bool arraylist_add_last (arraylist * list, void * item)
{
    return arraylist_add_index(list, item, arraylist_size(list));
}

inline
void * arraylist_get_last (arraylist * list)
{
    return arraylist_get_index(list, arraylist_size(list) - 1);
}

inline
void * arraylist_remove_last (arraylist * list)
{
    return arraylist_remove_index(list, arraylist_size(list) - 1);
}

//END OF DEFAULTS
