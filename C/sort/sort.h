#include <stdint.h>

//count sorting stuff
#define counting_sort counting_sort_32

void counting_sort_8(uint8_t* in, uint8_t** out, uint32_t len, uint8_t k);
void counting_sort_16(uint16_t* in, uint16_t** out, uint32_t len, uint16_t k);
void counting_sort_32(uint32_t* in, uint32_t** out, uint32_t len, uint32_t k); 

//not done
void counting_sort_reverse(uint32_t* in, uint32_t** out, uint32_t len, uint32_t k); 

//insertion sort general
void insertion_sort(void** in, int len, int (*cmp)(void*, void*));
