#include "sort.h"

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define MAX_TEST_U8  0x000FF
#define MAX_TEST_U16 0x0FFFF
#define MAX_TEST_U32 0xFFFFF

#define _order_checker_(N) \
bool _order_good_ ## N (uint ## N ## _t* l, uint32_t n) \
{ \
   uint32_t i; \
   for (i=1; i<n; ++i) \
      if (l[i] < l[i-1]) \
         return (false); \
   return (true); \
}

_order_checker_(8)
_order_checker_(16)
_order_checker_(32)

#undef _order_checker_

#define _equal_(M,N) \
bool _equal_ ## M ## _ ## N (uint ## M ## _t* m, uint32_t lm, uint ## N ## _t* n, uint32_t ln) \
{ \
   if (lm != ln) return (false); \
   uint32_t i; \
   for (i=0; i<lm; ++ i) \
      if (m[i] != n[i]) \
         return (false); \
   return (true); \
}

_equal_(8,16)
_equal_(8,32)
_equal_(16,32)

#undef _equal_

bool _under_ten_test_unsigned()
{
   uint8_t     l8 [30];
   uint8_t  *  o8     ;
   uint16_t   l16 [30];
   uint16_t * o16     ;
   uint32_t   l32 [30];
   uint32_t * o32     ;

   int i;
   for (i=0; i<30; ++i)
      printf("%d ", l32[i] = l16[i] = l8[i] = rand() % 10);
   printf("\n\n");

   counting_sort_8(l8, &o8, 30, 10);
   counting_sort_16(l16, &o16, 30, 10);
   counting_sort_32(l32, &o32, 30, 10); 

   for (i=0; i<30; ++i)
      printf("%d ", o8[i]);
   printf("\n");

   for (i=0; i<30; ++i)
      printf("%d ", o16[i]);
   printf("\n");

   for (i=0; i<30; ++i)
      printf("%d ", o32[i]);
   printf("\n");

   bool good = _equal_8_16(o8,30,o16,30) && _equal_8_32(o8,30,o32,30) && _equal_16_32(o16,30,o32,30);

   free(o8);
   free(o16);
   free(o32);

   return (good);
}

bool _all_comp(uint32_t n, uint32_t times)
{
   uint8_t     l8 [n];
   uint8_t  *  o8    ;
   uint16_t   l16 [n];
   uint16_t * o16    ;
   uint32_t   l32 [n];
   uint32_t * o32    ;
 
   int i, j;
   bool good = true;

   for (j=0; j<times; ++j) {
      for (i=0; i<n; ++i)
         l32[i] = l16[i] = l8[i] = rand() % 10;

      counting_sort_8(l8, &o8, n, MAX_TEST_U8);
      counting_sort_16(l16, &o16, n, MAX_TEST_U8);
      counting_sort_32(l32, &o32, n, MAX_TEST_U8);
      
      good = good &&  _equal_8_16(o8,n,o16,n) && _equal_8_32(o8,n,o32,n) && _equal_16_32(o16,n,o32,n);

      if (!good) {
         for (i=0; i<n; ++i)
            printf("%d ", l8[i]);
         printf("\n");

         for (i=0; i<n; ++i)
            printf("%d ", o8[i]);
         printf("\n\n");

         for (i=0; i<n; ++i)
            printf("%d ", l16[i]);
         printf("\n");

         for (i=0; i<n; ++i)
            printf("%d ", o16[i]);
         printf("\n\n");

         for (i=0; i<n; ++i)
            printf("%d ", l32[i]);
         printf("\n");

         for (i=0; i<n; ++i)
            printf("%d ", o32[i]);
         printf("\n\n");

         free(o8);
         free(o16);
         free(o32);
         return (false);
      }

      free(o8);
      free(o16);
      free(o32);
   }

   return (true);
}

int main(void)
{
   bool passed = true;
   srand(time(NULL));

   printf("\nunder ten unsigned test... \n");
   if (!_under_ten_test_unsigned()) {
      passed = false;
      printf("\ntest failed\n");
   } else {
      printf("\ntest passed\n");
   } 

   printf("\nall test... \n");
   if (!_all_comp(100, 10000)) {
      passed = false;
      printf("\ntest failed\n");
   } else {
      printf("\ntest passed\n");
   } 

   return (0);
}
