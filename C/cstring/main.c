#include "cstring.h"

#include <stdio.h>

int main(void)
{
    string * str;
    string * sub;
    string * rev;

    create_string("Testing the cstring library!", &str);

    printf("%d : %s\n", str->length, str->characters);
    reverse_string(str, &rev);
    printf("%d : %s\n", rev->length, rev->characters);

    substring(str, 4, 8, &sub);
    printf("%d : %s\n", sub->length, sub->characters);

    string * concat;

    concat_strings(str, rev, &concat);
    printf("%d : %s\n", concat->length, concat->characters);

    delete_string(concat);
    delete_string(str);
    delete_string(sub);
    delete_string(rev);   
}
