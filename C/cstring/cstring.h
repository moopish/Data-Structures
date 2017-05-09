#include <stdint.h>

typedef struct str string;

struct str {
    const int          length;
    const char * const characters;
};

int compare_strings(const string * const a, const string * const b);
int first_pos(const string * const str, char c);
int first_pos_after(const string * const str, char c, int after);
int last_pos(const string * const str, char c);
int last_pos_before(const string * const str, char, int before);

string * concat_strings(string * a, string * b, string ** ab);
string * reverse_string(string * str, string ** reversed);
string * substring(string * str, int start, int end, string ** sub);

int length_of_string(const char * const chars);
string * create_string(const char * const chars, string ** str);
string * new_string(const char * const chars, int length, string ** str);
void delete_string(string * str);
string * initialize_string(const char * const chars, int length, string * str);
void finalize_string(string * str);
