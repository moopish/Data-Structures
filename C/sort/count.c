#include "sort.h"

#include <stdlib.h>

#define count_sort_u(N) \
void counting_sort_ ## N (uint ## N ## _t* in, uint ## N ## _t** out, uint32_t len, uint ## N ## _t k) \
{ \
   int i; \
   uint32_t* counts = (uint32_t*)calloc(k, sizeof(uint32_t)); \
   for (i=0; i<len; ++i) \
      ++counts[in[i]]; \
   for (i=1; i<k; ++i) \
      counts[i] += counts[i-1]; \
   *out = (uint ## N ## _t*)malloc(sizeof(uint ## N ## _t) * len); \
   for (i=len-1; i>=0; --i) \
      (*out)[--counts[in[i]]] = in[i]; \
   free(counts); \
}

count_sort_u(8)
count_sort_u(16)
count_sort_u(32)

#undef count_sort_u

void counting_sort_reverse(uint32_t* in, uint32_t** out, uint32_t len, uint32_t k)
{
   uint32_t i;
   uint32_t* counts = (uint32_t*)calloc(k, sizeof(uint32_t));
   for (i=0; i<len; ++i)
      ++counts[in[i]];
   for (i=1; i<k; ++i)
      counts[i] += counts[i-1];
   *out = (uint32_t*)malloc(sizeof(uint32_t) * len);
   for (i=len-1; i>=0; --i)
      (*out)[len - counts[in[i]]--] = in[i];
   free(counts);
}

/*void counting_sort(uint32_t* in, uint32_t** out, uint32_t len, uint32_t k)
{
   int i;
   uint32_t* counts = (uint32_t*)calloc(k, sizeof(uint32_t));
   for (i=0; i<len; ++i)
      ++counts[in[i]];
   for (i=1; i<k; ++i)
      counts[i] += counts[i-1];
   *out = (uint32_t*)malloc(sizeof(uint32_t) * len);
   for (i=len-1; i>=0; --i)
      (*out)[--counts[in[i]]] = in[i];       
   free(counts);
}*/

