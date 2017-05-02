#include "list.h"

#include <stdlib.h>
#include <stdio.h>

typedef struct _SLL_NODE sllnode;

struct _SLL_NODE 
{
   sllnode * next;
   void    * data;  
};

sllnode* _create_node(void* data)
{
   sllnode* ret = (sllnode*)malloc(sizeof(sllnode));
   ret->data = data;
   ret->next = NULL;
   return (ret);
}

struct _SLL 
{
   sllnode  * head;
   sllnode  * tail;
   uint32_t   count;
};

//sll
void add_back_sll(sllist* list, void* data)
{
   if (list->count == 0) {
      list->head = list->tail = _create_node(data);
   } else {
      list->tail = list->tail->next = _create_node(data);
   }
   ++(list->count);
}

void add_front_sll(sllist* list, void* data)
{
   sllnode* front = _create_node(data);
   front->next = list->head;
   list->head = front;
   if (list->count == 0) {
      list->tail = front;
   }
   ++(list->count);
}

bool add_sll(sllist* list, void* data, uint32_t index);
void clear_sll(sllist* list);

void copy_sll(sllist** to, sllist* from)
{
   create_sll(to);
   //TODO       
}

void create_sll(sllist** list)
{
   *list = (sllist*)calloc(sizeof(sllist), 1);
}

void delete_sll(sllist* list, void (*dlter)(void*))
{
   
}

void* get_back_sll(sllist* list)
{
   if (list->count == 0) {
      return (NO_ELEMENT);
   } else {
      sllnode* curr = list->head;
      while (curr->next != NULL)
         curr = curr->next;
      return (curr->data);
   }
}

void* get_front_sll(sllist* list)
{
   if (list->count == 0) {
      return (NO_ELEMENT);
   } else {
      return (list->head->data);
   }
}

void* get_sll(sllist* list, uint32_t index)
{
   if (list->count == 0 || index >= list->count) {
      return (NO_ELEMENT);
   } else {
      sllnode* curr = list->head;
      while (index > 0) {
         curr = curr->next;
         --index;
      }
      return (curr->data);
   }
}

void* rem_back_sll(sllist* list);
void* rem_front_sll(sllist* list);
void* rem_sll(sllist* list, uint32_t index);

bool is_empty_sll(sllist* list)
{
   return (list->count == 0);
}

uint32_t size_sll(sllist* list)
{
   return (list->count);
}

int main(void)
{
   sllist* list;
   create_sll(&list);
   printf("%d\n", size_sll(list));
   free(list);

   return (0);
}
