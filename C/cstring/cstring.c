/**
 * cstring.c - source for a string struct and related functions
 * @since  May 9th, 2017
 * @author Michael van Dyk
 */
 
#include "cstring.h"

#include <string.h>
#include <stdlib.h>

/**
 *  Allocates a string structure and assigns the pointer to the 
 * memory to (A). Internal only since macros can be dangerous 
 * if missued.
 */
#define ALLOC_STR(A) (A) = (string *)malloc(sizeof(string))

/** 
 * Converts to lower case if upper case.
 */ 
#define LOWER(C) (((C) >= 'A' && (C) <= 'Z') ? (C) + 0x20 : (C))

/** 
 * Converts to upper case if lower case.
 */ 
#define UPPER(C) (((C) >= 'a' && (C) <= 'z') ? (C) - 0x20 : (C))

/**
 *  malstr is a malleable string. This hides the modifications of
 * strings from the user. Simply cast a string pointer to a malstr
 * pointer for easy modification within this file. An external 
 * user can cast the values within the struct to malleable types 
 * to modify but they must explicitly do so to ensure that they 
 * want to make the changes.
 */
typedef struct malstr malstr;

/**
 * length: the length of the string
 * characters: the characters of the string
 * 
 * [0, length) are the non null characters, null is at index length.
 * length + 1 characters are stored including the null terminating character
 */
struct malstr {
    int    length;
    char * characters;
}; 

/**
 *  Internal, used to initialize a string structure. Allocates the character 
 * array, assigns the size and adds the null character.
 * 
 * @param  size (in) the length of the string (without the null character)
 * @param  str  (in) the string pointer to initialize
 * @return           the character array
 */
char * _init_str_(int size, string * str) {
    char * characters = (char *)malloc((size + 1) * sizeof(char));

    ((malstr *)str)->length = size;
    ((malstr *)str)->characters = characters;
    characters[size] = 0;

    return (characters);
}

//
// Setup functions
///////////////////
/**
 *  Creates a string from a null terminated c-string. A string is dynamically 
 * allocated and assigned to the 'str' parameter.
 *
 * @param  chars (in)  the c-string to create a string from
 * @param  str   (out) the created string
 * @return             the created string
 */
string * create_string(const char * const chars, string ** str)
{
    return (new_string(chars, length_of_string(chars), str));
}

/**
 *  Creates a string from a c-string, does not need to be null terminated 
 * but the length must be withing the range of the given c-string. A string 
 * is dynamically allocated and assigned to the 'str' parameter.
 *
 * @param  chars  (in)  the c-string to create a string from
 * @param  length (in)  the length of the c-string
 * @param  str    (out) the created string
 * @return              the created string
 */
string * new_string(const char * const chars, int length, string ** str)
{
    return (initialize_string(chars, length, ALLOC_STR(*str)));
}

/**
 *  Initializes a string from a given c-string. 'str' but be allocated 
 * before passing.
 * 
 * @param  chars  (in)  the c-string to create a string from
 * @param  length (in)  the length of the c-string
 * @param  str    (out) the initialized string
 * @return              the initialized string
 */
 string * initialize_string(const char * const chars, int length, string * str)
{
    char * characters = _init_str_(length, str);
    while (--length >= 0) characters[length] = chars[length];
    return (str);
}

//
// Clean Up functions
//////////////////////
/**
 *  Cleans up a string structure. Frees the character array. Deletes the 
 * dynamically allocated space for the string. Do not pass a statically 
 * allocated string to this function.
 *
 * @param str (in) the string to delete
 */
void delete_string(string * str)
{
    finalize_string(str);
    free(str);
}

/**
 *  Cleans up a string structure. Frees the character array. Does not deal
 * with the memory for the string if the string was dynamically allocated.
 *
 * @param str (in) the string to finalize
 */
void finalize_string(string * str)
{
    free((malstr *)str->characters);
}

//
// Utility functions
/////////////////////
/**
 *  Determines how many times the character c is found in the given c-string.
 * Assumed to be null-terminated.
 * 
 * @param  chars (in) the c-string to search
 * @param  c     (in) the character to count
 * @return            the number of times c is in the given c-string
 */
int character_instances(const char * const chars, char c);

/**
 *  Determines how many times the character c is found in the given string.
 * 
 * @param  str (in) the string to search
 * @param  c   (in) the character to count
 * @return          the number of times c is in the given string
 */
int character_instances_string(string * str, char c);

/**
 * Determines the length of a null terminated string.
 *
 * @param  chars (in) the string to find the length of
 * @return            the length of the string
 */
int length_of_string(const char * const chars)
{
    int count = 0;
    while(chars[count] != 0) ++count;
    return (count);
}

/**
 *  Concatinate two strings together.
 *
 * @param  a  (in)  the first half of the new string
 * @param  b  (in)  the second half of the new string
 * @param  ab (out) the concatinated string
 * @return          the concatinated string
 */
string * concat_strings(string * a, string * b, string ** ab)
{
    char * characters = _init_str_(a->length + b->length, ALLOC_STR(*ab));

    memcpy(characters, a->characters, a->length);
    memcpy(characters + a->length, b->characters, b->length);

    return (*ab);     
}

/**
 *  Concatinate multiple string into on string, in the order that 
 * they are given in the array.
 *
 * @param  strs   (in)  an array of string to concatinate
 * @param  len    (in)  the length of the string array
 * @param  result (out) the concatinated string
 * @return              the concatinated string
 */
string * concat_strings_multiple(string ** strs, int len, string ** result)
{
    int total = 0;
    int curr  = 0;
    int i;
    
    for (i = 0; i < len; ++i)
        total += (strs[i])->length;
    
    char * characters = _init_str_(total, ALLOC_STR(*result));
    
    for (i = 0; i < len; ++i) {
        memcpy(characters + curr, (strs[i])->characters, (strs[i])->length);
        curr += (strs[i])->length;
    }
    
    return (*result);
}

/**
 *  Reverse the given string.
 *
 * @param  str      (in)  the string to reverse
 * @param  reversed (out) the reversed string
 * @return                the reversed string
 */
string * reverse_string(string * str, string ** result)
{
    char * characters = _init_str_(str->length, ALLOC_STR(*result));
    char   swap;
    int    i          = 0;
    int    lim        = str->length/2;

    characters[str->length] = 0;
    while (i < lim) {
        characters[i] = str->characters[str->length - i - 1];
        characters[str->length - i - 1] = str->characters[i];
        ++i;
    }
    
    return (*result);
}

/**
 *  Get a substring of the given string.
 *
 * @param  str   (in)  the string to get a substring from
 * @param  start (in)  the start index of the substring (inclusive)
 * @param  end   (in)  the end index of the substring (exclusive)
 * @param  sub   (out) the substring
 * @return
 */
string * substring(string * str, int start, int end, string ** sub)
{
    return (new_string(str->characters + start, end - start, sub));
}

//
// Rotate functions
////////////////////
/**
 *  Rotates the string left by the given amount. The rotated string is a 
 * new string stored in the param result.
 *
 * @param  str    (in)     the string to rotate
 * @param  amount (in)     the amount to rotate left by
 * @param  result (in/out) the rotated string
 * @return                 the rotated string
 */
string * rotate_string_left(string * str, int amount, string ** result)
{
    return (rotate_string_right(str, -amount, result));
}

/**
 *  Rotates the string right by the given amount. The rotated string is a 
 * new string stored in the param result.
 *
 * @param  str    (in)     the string to rotate
 * @param  amount (in)     the amount to rotate right by
 * @param  result (in/out) the rotated string
 * @return                 the rotated string
 */
string * rotate_string_right(string * str, int amount, string ** result)
{
    char * characters = _init_str_(str->length, ALLOC_STR(*result));
    int shift = (amount < 0 ? str->length - (-amount % str->length) : amount) % str->length;

    memcpy(characters + shift, str->characters, str->length - shift);
    memcpy(characters, str->characters + str->length - shift, shift);

    return (*result);
}

//
// Character case functions
////////////////////////////
/**
 *  Creates an upper case version of the given string.
 *
 * @param  str   (in)  the string to convert to upper case
 * @param  upper (out) an upper case version of the string
 * @return             an upper case version of the string
 */
string * to_upper_case(string * str, string ** upper)
{
    char * characters = _init_str_(str->length, ALLOC_STR(*upper));   
    int    i          = 0;

    for ( ; i<(*upper)->length; ++i)
        characters[i] = UPPER(str->characters[i]);
   
    return (*upper);
}

/**
 *  Creates an lower case version of the given string.
 *
 * @param  str   (in)  the string to convert to lower case
 * @param  lower (out) an lower case version of the string
 * @return             an lower case version of the string
 */
string * to_lower_case(string * str, string ** lower)
{   
    char * characters = _init_str_(str->length, ALLOC_STR(*lower));   
    int    i          = 0;

    for ( ; i<(*lower)->length; ++i)
        characters[i] = LOWER(str->characters[i]);
   
    return (*lower);
}

//
// Compare functions
/////////////////////
/**
 *  Compares two given strings.
 * 
 * @param  a (in) one string to compare
 * @param  b (in) the other string to compare
 * @return        less than, zero or greater than zero 
 *                 if a < b, a = b, a > b respectively
 */
int compare_strings(const string * const a, const string * const b)
{
    int min_ab = (a->length == b->length) ? 0 : a->length - b->length;
    int min    = (min_ab >= 0) ? a->length : b->length;
    int i      = 0;

    for ( ; i<min; ++i) {
        if (a->characters[i] != b->characters[i])
            return (a->characters[i] - b->characters[i]);
    }

    return (min_ab);
}

/**
 *  Compares two given strings ignoring case.
 * 
 * @param  a (in) one string to compare
 * @param  b (in) the other string to compare
 * @return        less than, zero or greater than zero 
 *                 if a < b, a = b, a > b respectively
 */
int compare_strings_no_case (const string * const a, const string * const b)
{
    char a_c;
    char b_c;
    int  min_ab = (a->length == b->length) ? 0 : a->length - b->length;
    int  min    = (min_ab >= 0) ? a->length : b->length;
    int  i      = 0;

    for ( ; i<min; ++i) {
        a_c = LOWER(a->characters[i]);
        b_c = LOWER(b->characters[i]);
        if (a_c != b_c)
            return (a_c - b_c);
    }

    return (min_ab);
}

/**
 *  Determines if two given strings are equal.
 * 
 * @param  a (in) one string to compare
 * @param  b (in) the other string to compare
 * @return        true if equal, false if not
 */
bool equal_strings(const string * const a, const string * const b)
{
    return (compare_strings(a, b) == 0);
}

/**
 *  Determines if two given strings are equal ignoring case.
 * 
 * @param  a (in) one string to compare
 * @param  b (in) the other string to compare
 * @return        true if equal, false if not
 */
bool equal_strings_no_case(const string * const a, const string * const b)
{
    return (compare_strings_no_case(a, b) == 0);
}

//
// Postions functions
//////////////////////
/**
 *  Finds the first instance of a character in a given string.
 *
 * @param  str (in) the string to search
 * @param  c   (in) the character to look for
 * @return          the index of the first instance (-1 if not found)
 */
int first_pos(const string * const str, char c)
{
    return (first_pos_after(str, c, -1));
}

/**
 *  Finds the first instance of a character in a given string. Starting 
 * the search after a given index.
 *
 * @param  str   (in) the string to search
 * @param  c     (in) the character to look for
 * @param  after (in) the search starts at after + 1
 * @return            the index of the first instance (-1 if not found)
 */
int first_pos_after(const string * const str, char c, int after)
{
    int i = after + 1;

    while (i < str->length) {
        if (str->characters[i] == c)
            return (i);
        ++i;
    }

    return (-1);
}

/**
 *  Finds the last instance of a character in a given string.
 *
 * @param  str (in) the string to search
 * @param  c   (in) the character to look for
 * @return          the index of the last instance (-1 if not found)
 */
int last_pos(const string * const str, char c)
{
    return (last_pos_before(str, c, str->length));
}

/**
 *  Finds the last instance of a character in a given string. Starting 
 * the search before a given index.
 * @param  str    (in) the string to search
 * @param  c      (in) the character to look for
 * @param  before (in) the search starts at before - 1
 * @return             the index of the last instance (-1 if not found)
 */
int last_pos_before(const string * const str, char c, int before)
{
    int i = before - 1;

    while (i < str->length) {
        if (str->characters[i] == c)
            return (i);
        --i;
    }

    return (-1);
}












