// ${STRUCT}.h
// by Michael van Dyk
// Generated ${DATE}
// 
// Do not modify
//

#include <stdbool.h>

typedef struct def_${STRUCT} ${STRUCT};

//Queue functions
bool     ${STRUCT}_add          (${STRUCT} * deque, void * item);
void*    ${STRUCT}_get          (${STRUCT} * deque);
void*    ${STRUCT}_remove       (${STRUCT} * deque);

//Stack functions
bool     ${STRUCT}_push         (${STRUCT} * deque, void * item);
void*    ${STRUCT}_peek         (${STRUCT} * deque);
void*    ${STRUCT}_pop          (${STRUCT} * deque);

//Deque functions
bool     ${STRUCT}_add_first    (${STRUCT} * deque, void * item);
bool     ${STRUCT}_add_last     (${STRUCT} * deque, void * item);
void*    ${STRUCT}_get_first    (${STRUCT} * deque);
void*    ${STRUCT}_get_last     (${STRUCT} * deque);
void*    ${STRUCT}_remove_first (${STRUCT} * deque);
void*    ${STRUCT}_remove_last  (${STRUCT} * deque);

uint32_t ${STRUCT}_size         (${STRUCT} * deque);
