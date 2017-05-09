// ${STRUCT}.h
// by Michael van Dyk
// Generated ${DATE}
// 
// Do not modify
//

#include <stdbool.h>
#include <stdint.h>

typedef struct def_${STRUCT} ${STRUCT};

//Queue functions
bool     ${STRUCT}_add          (${STRUCT} * list, void * item);
void*    ${STRUCT}_get          (${STRUCT} * list);
void*    ${STRUCT}_remove       (${STRUCT} * list);

//Stack functions
bool     ${STRUCT}_push         (${STRUCT} * list, void * item);
void*    ${STRUCT}_peek         (${STRUCT} * list);
void*    ${STRUCT}_pop          (${STRUCT} * list);

//Deque functions
bool     ${STRUCT}_add_first    (${STRUCT} * list, void * item);
bool     ${STRUCT}_add_last     (${STRUCT} * list, void * item);
void*    ${STRUCT}_get_first    (${STRUCT} * list);
void*    ${STRUCT}_get_last     (${STRUCT} * list);
void*    ${STRUCT}_remove_first (${STRUCT} * list);
void*    ${STRUCT}_remove_last  (${STRUCT} * list);

//List functions
bool     ${STRUCT}_add_index    (${STRUCT} * list, void * item, uint32_t index);
void*    ${STRUCT}_get_index    (${STRUCT} * list,              uint32_t index);
void*    ${STRUCT}_remove_index (${STRUCT} * list,              uint32_t index);
void*    ${STRUCT}_set_index    (${STRUCT} * list, void * item, uint32_t index);

uint32_t ${STRUCT}_size         (${STRUCT} * list);
