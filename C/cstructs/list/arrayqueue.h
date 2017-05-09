// arrayqueue.h
// by Michael van Dyk
// Generated Tuesday 19 2017 01:38:17 AM
// 
// Do not modify
//

#include <stdbool.h>

typedef struct def_arrayqueue arrayqueue;

bool  arrayqueue_add    (arrayqueue * queue, void * item);
void* arrayqueue_get    (arrayqueue * queue);
void* arrayqueue_remove (arrayqueue * queue);
