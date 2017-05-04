#include <stdbool.h>
#include <stdint.h>

#define NO_ELEMENT 0

typedef struct _SLL sllist;
typedef struct _DLL dllist;
typedef dllist linkedlist;

//sll
void add_back_sll(sllist* list, void* data);
void add_front_sll(sllist* list, void* data);
bool add_sll(sllist* list, void* data, uint32_t index);
void clear_sll(sllist* list);
void copy_sll(sllist** to, sllist* from);
void create_sll(sllist** list);
void delete_sll(sllist* list, void (*dlter)(void*));
void* get_back_sll(sllist* list);
void* get_front_sll(sllist* list);
void* get_sll(sllist* list, uint32_t index);
void* rem_back_sll(sllist* list);
void* rem_front_sll(sllist* list);
void* rem_sll(sllist* list, uint32_t index);

bool is_empty_sll(sllist* list);
uint32_t size_sll(sllist* list);


