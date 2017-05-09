#include "cstring.h"

#include <string.h>
#include <stdlib.h>

typedef struct malstr malstr;

struct malstr {
    int    length;
    char * characters;
}; 

char * _init_str_(int size, string * str) {
    char * characters = (char *)malloc((size + 1) * sizeof(char));
    ((malstr *)str)->length = size;
    ((malstr *)str)->characters = characters;
    characters[size] = 0;
    return (characters);
}

void finalize_string(string * str)
{
    free((malstr *)str->characters);
}

string * initialize_string(const char * const chars, int length, string * str)
{
    char * characters = _init_str_(length, str);
    while (--length >= 0)
        characters[length] = chars[length];

    return (str);
}

int length_of_string(const char * const chars)
{
    int count = 0;
    while(chars[count] != 0) ++count;
    return (count);
}

string * create_string(const char * const chars, string ** str)
{
    return (new_string(chars, length_of_string(chars), str));
}

string * new_string(const char * const chars, int length, string ** str)
{
    return (initialize_string(chars, length, *str = (string *)malloc(sizeof(string))));
}

void delete_string(string * str)
{
    finalize_string(str);
    free(str);
}

string * reverse_string(string * str, string ** result)
{
    *result = (string *)malloc(sizeof(string));
    char * characters = _init_str_(str->length, *result);
    int i=0, lim = str->length/2;
    char swap;

    characters[str->length] = 0;
    while (i < lim) {
        characters[i] = str->characters[str->length - i - 1];
        characters[str->length - i - 1] = str->characters[i];
        ++i;
    }
    
    return (*result);
}

int compare_strings(const string * const a, const string * const b);
int first_pos(const string * const str, char c);
int first_pos_after(const string * const str, char c, int after);
int last_pos(const string * const str, char c);
int last_pos_before(const string * const str, char, int before);

string * concat_strings(string * a, string * b, string ** ab)
{
    char * characters = _init_str_(a->length + b->length, *ab = (string *)malloc(sizeof(string)));
    memcpy(characters, a->characters, a->length);
    memcpy(characters + a->length, b->characters, b->length);
    return (*ab);     
}

string * substring(string * str, int start, int end, string ** sub)
{
    return (new_string(str->characters + start, end - start, sub));
}
