#include "cstring.h"

#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    string * str;
    string * sub;
    string * rev;

    create_string("Testing the cstring library!", &str);

    printf("%d : %s\n", str->length, str->characters);
    to_upper_case(str, &rev);
    printf("%d : %s\n", rev->length, rev->characters);

    to_lower_case(str, &sub);
    printf("%d : %s\n", sub->length, sub->characters);

    string * concat;

    concat_strings(str, rev, &concat);
    printf("%d : %s\n", concat->length, concat->characters);

    printf("%d %d %d\n", first_pos(str, 't'), last_pos(str, 't'), first_pos(sub, ':'));

    printf("%d %d %d %d\n", 3 % 13, 3 % -13, -3 % 13, -3 % -13);

    string * rot;
    int i;
    const short lim = 0x8000; 
    for (i = lim; i<-lim; ++i) {
        rotate_string_left(str, i, &rot);
//        printf("%s\n", rot->characters);
        delete_string(rot);
    }

    ((char *)(str->characters))[3] = 'b';

    printf("%s\n", str->characters);

    delete_string(concat);

    string ** test = (string **)malloc(sizeof(string *) * 3);
    test[0] = str;
    test[1] = sub;
    test[2] = rev;
    concat_strings_multiple(test, 3, &concat);

    printf("%s\n", concat->characters);

    free(test);
    delete_string(concat);
    delete_string(str);
    delete_string(sub);
    delete_string(rev);   
}
