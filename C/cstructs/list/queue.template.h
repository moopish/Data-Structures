// ${STRUCT}.h
// by Michael van Dyk
// Generated ${DATE}
// 
// Do not modify
//

#include <stdbool.h>
#include <stdint.h>

typedef struct def_${STRUCT} ${STRUCT};

bool     ${STRUCT}_add    (${STRUCT} * queue, void * item);
void*    ${STRUCT}_get    (${STRUCT} * queue);
void*    ${STRUCT}_remove (${STRUCT} * queue);

uint32_t ${STRUCT}_size   (${STRUCT} * queue);
