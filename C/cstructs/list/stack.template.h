// ${STRUCT}.h
// by Michael van Dyk
// Generated ${DATE}
// 
// Do not modify
//

#include <stdbool.h>
#include <stdint.h>

typedef struct def_${STRUCT} ${STRUCT};

bool     ${STRUCT}_push (${STRUCT} * stack, void * item);
void*    ${STRUCT}_peek (${STRUCT} * stack);
void*    ${STRUCT}_pop  (${STRUCT} * stack);

uint32_t ${STRUCT}_size (${STRUCT} * stack);
