#include "sort.h"

void insertion_sort(void** in, int len, int (*cmp)(void*, void*))
{
   int i, j;
   for (i=1; i<len; ++i) {
      j=i;
      while (j>0 && (*cmp)(*in + j - 1, *in + j)) {
         void * t = *in + j;
         in[j] = in + j - 1;
         in[j - 1] = t;
         j -= 1;
      }
   }
}
