/**
 * cstring.h - header for a string struct and related functions
 * @since  May 9th, 2017
 * @author Michael van Dyk
 */

#ifndef _CSTRUCT_STRING_H_
#define _CSTRUCT_STRING_H_ 1

#include <stdbool.h>
#include <stdint.h>

/**
 * Represents a string.
 */
typedef struct str string;

/**
 * length: the length of the string
 * characters: the characters of the string
 * 
 * [0, length) are the non null characters, null is at index length.
 * length + 1 characters are stored including the null terminating character
 */
struct str {
    const int          length;
    const char * const characters;
};

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
string * create_string(const char * const chars, string ** str);

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
string * new_string(const char * const chars, int length, string ** str);

/**
 *  Initializes a string from a given c-string. 'str' but be allocated 
 * before passing.
 * 
 * @param  chars  (in)  the c-string to create a string from
 * @param  length (in)  the length of the c-string
 * @param  str    (out) the initialized string
 * @return              the initialized string
 */
string * initialize_string(const char * const chars, int length, string * str);

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
void delete_string(string * str);

/**
 *  Cleans up a string structure. Frees the character array. Does not deal
 * with the memory for the string if the string was dynamically allocated.
 *
 * @param str (in) the string to finalize
 */
void finalize_string(string * str);

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
int length_of_string(const char * const chars);

/**
 *  Concatinate two strings together.
 *
 * @param  a  (in)  the first half of the new string
 * @param  b  (in)  the second half of the new string
 * @param  ab (out) the concatinated string
 * @return          the concatinated string
 */
string * concat_strings(string * a, string * b, string ** ab);

/**
 *  Concatinate multiple string into on string, in the order that 
 * they are given in the array.
 *
 * @param  strs   (in)  an array of string to concatinate
 * @param  len    (in)  the length of the string array
 * @param  result (out) the concatinated string
 * @return              the concatinated string
 */
string * concat_strings_multiple(string ** strs, int len, string ** result);

/**
 *  Reverse the given string.
 *
 * @param  str      (in)  the string to reverse
 * @param  reversed (out) the reversed string
 * @return                the reversed string
 */
string * reverse_string(string * str, string ** reversed);

/**
 *  Get a substring of the given string.
 *
 * @param  str   (in)  the string to get a substring from
 * @param  start (in)  the start index of the substring (inclusive)
 * @param  end   (in)  the end index of the substring (exclusive)
 * @param  sub   (out) the substring
 * @return
 */
string * substring(string * str, int start, int end, string ** sub);

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
string * rotate_string_left  (string * str, int amount, string ** result);

/**
 *  Rotates the string right by the given amount. The rotated string is a 
 * new string stored in the param result.
 *
 * @param  str    (in)     the string to rotate
 * @param  amount (in)     the amount to rotate right by
 * @param  result (in/out) the rotated string
 * @return                 the rotated string
 */
string * rotate_string_right (string * str, int amount, string ** result);

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
string * to_upper_case (string * str, string ** upper);

/**
 *  Creates an lower case version of the given string.
 *
 * @param  str   (in)  the string to convert to lower case
 * @param  lower (out) an lower case version of the string
 * @return             an lower case version of the string
 */
string * to_lower_case (string * str, string ** lower);

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
int  compare_strings (const string * const a, const string * const b);

/**
 *  Compares two given strings ignoring case.
 * 
 * @param  a (in) one string to compare
 * @param  b (in) the other string to compare
 * @return        less than, zero or greater than zero 
 *                 if a < b, a = b, a > b respectively
 */
int  compare_strings_no_case (const string * const a, const string * const b);

/**
 *  Determines if two given strings are equal.
 * 
 * @param  a (in) one string to compare
 * @param  b (in) the other string to compare
 * @return        true if equal, false if not
 */
bool equal_strings(const string * const a, const string * const b);

/**
 *  Determines if two given strings are equal ignoring case.
 * 
 * @param  a (in) one string to compare
 * @param  b (in) the other string to compare
 * @return        true if equal, false if not
 */
bool equal_strings_no_case(const string * const a, const string * const b);

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
int first_pos(const string * const str, char c);

/**
 *  Finds the first instance of a character in a given string. Starting 
 * the search after a given index.
 *
 * @param  str   (in) the string to search
 * @param  c     (in) the character to look for
 * @param  after (in) the search starts at after + 1
 * @return            the index of the first instance (-1 if not found)
 */
int first_pos_after(const string * const str, char c, int after);

/**
 *  Finds the last instance of a character in a given string.
 *
 * @param  str (in) the string to search
 * @param  c   (in) the character to look for
 * @return          the index of the last instance (-1 if not found)
 */
int last_pos(const string * const str, char c);

/**
 *  Finds the last instance of a character in a given string. Starting 
 * the search before a given index.
 * @param  str    (in) the string to search
 * @param  c      (in) the character to look for
 * @param  before (in) the search starts at before - 1
 * @return             the index of the last instance (-1 if not found)
 */
int last_pos_before(const string * const str, char c, int before);

#endif
