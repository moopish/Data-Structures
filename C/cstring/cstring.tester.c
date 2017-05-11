#include "cstring.h"

#include <stdbool.h>
#include <stdio.h>

int correct = 0;
int total = 0;

void test_bool(const char * const pre, bool expected, bool actual);
void test_int(const char * const pre, int expected, int actual);
void test_string(const char * const pre, const string * const expected, const string * const actual);

int main(void)
{
    string   statically;
    string * dynamic;
    string * created;
    
    initialize_string("statically ALLocated", length_of_string("statically ALLocated"), &statically);
    new_string("DyNaMiCaLlY aLlOcAtEd", length_of_string("DyNaMiCaLlY aLlOcAtEd"), &dynamic);
    create_string("created string", &created);
    
    //
    // Utility functions
    /////////////////////
    test_int("character_instances(\"alpha\", 'a')", 2, character_instances("alpha", 'a'));
    test_int("character_instances(\"alpha\", 'l')", 1, character_instances("alpha", 'l'));
    test_int("character_instances(\"alpha\", 'z')", 0, character_instances("alpha", 'z'));
   
    test_int("character_instances_string(&statically, 'a')", 3, character_instances_string(&statically, 'a'));
    test_int("character_instances_string(&statically, 'l')", 2, character_instances_string(&statically, 'l'));
    test_int("character_instances_string(&statically, 'z')", 0, character_instances_string(&statically, 'z'));
   
    test_int("character_instances_string(dynamic, 'a')", 3, character_instances_string(dynamic, 'a'));
    test_int("character_instances_string(dynamic, 'l')", 2, character_instances_string(dynamic, 'l'));
    test_int("character_instances_string(dynamic, 'z')", 0, character_instances_string(dynamic, 'z'));
   
    test_int("character_instances_string(created, 'a')", 1, character_instances_string(created, 'a'));
    test_int("character_instances_string(created, 'l')", 0, character_instances_string(created, 'l'));
    test_int("character_instances_string(created, 'z')", 0, character_instances_string(created, 'z'));

    test_int("length_of_string(\"alpha\")", 5, length_of_string("alpha"));
    test_int("length_of_string(statically.characters)", 20, length_of_string(statically.characters));
    test_int("length_of_string(dynamic->characters)", 21, length_of_string(dynamic->characters));
    test_int("length_of_string(created->characters)", 14, length_of_string(created->characters));

    //test_string("concat_strings(&statically, &statically)", 
  
    //
    // Postions functions
    //////////////////////
    test_int("first_pos(statically, 's')", 0, first_pos(&statically, 's'));
    test_int("first_pos(dynamic, 'D')", 0, first_pos(dynamic, 'D'));
    test_int("first_pos(created, 'c')", 0, first_pos(created, 'c'));
    
    test_int("first_pos(statically, 'd')", 19, first_pos(&statically, 'd'));
    test_int("first_pos(dynamic, 'd')", 20, first_pos(dynamic, 'd'));
    test_int("first_pos(created, 'g')", 13, first_pos(created, 'g'));
    
    test_int("first_pos(statically, 't')", 1, first_pos(&statically, 't'));
    test_int("first_pos(dynamic, 't')", 18, first_pos(dynamic, 't'));
    test_int("first_pos(created, 't')", 4, first_pos(created, 't'));
    
    test_int("first_pos(statically, 'z')", -1, first_pos(&statically, 'z'));
    test_int("first_pos(dynamic, 'z')", -1, first_pos(dynamic, 'z'));
    test_int("first_pos(created, 'z')", -1, first_pos(created, 'z'));
    
    test_int("last_pos(statically, 's')", -1, last_pos(&statically, 's'));
    test_int("last_pos(dynamic, 'D')", -1, last_pos(dynamic, 'D'));
    test_int("last_pos(created, 'c')", -1, last_pos(created, 'c'));
    //TODO NOT DONE
    test_int("last_pos(statically, 'd')", 19, last_pos(&statically, 'd'));
    test_int("last_pos(dynamic, 'd')", 20, last_pos(dynamic, 'd'));
    test_int("last_pos(created, 'g')", 13, last_pos(created, 'g'));
    
    test_int("last_pos(statically, 't')", 1, last_pos(&statically, 't'));
    test_int("last_pos(dynamic, 't')", 18, last_pos(dynamic, 't'));
    test_int("last_pos(created, 't')", 4, last_pos(created, 't'));
    
    test_int("last_pos(statically, 'z')", -1, last_pos(&statically, 'z'));
    test_int("last_pos(dynamic, 'z')", -1, last_pos(dynamic, 'z'));
    test_int("last_pos(created, 'z')", -1, last_pos(created, 'z'));

    finalize_string(&statically);
    delete_string(dynamic);
    delete_string(created);

    printf("\n=====\nSTATS\n=====\n");
    printf("correct : %d\ntotal   : %d\npercent : %f\n", correct, total, (double)correct/total);

    
    return (0);
}

void test_bool(const char * const pre, bool expected, bool actual)
{
    printf("%s\nexpected value: %s\nactual value: %s\n%s\n\n",
           pre,
           expected ? "true" : "false",
           actual ? "true" : "false",
           expected == actual ? "true" : "false");
    ++total;
    correct += (expected == actual) ? 1 : 0;
}

void test_int(const char * const pre, int expected, int actual)
{
    printf("%s\nexpected value: %d\nactual value: %d\n%s\n\n",
           pre,
           expected,
           actual,
           expected == actual ? "true" : "false");
    ++total;
    correct += (expected == actual) ? 1 : 0;
}

void test_string(const char * const pre, const string * const expected, const string * const actual)
{
    printf("%s\nexpected value: %s\nactual value: %s\n%s\n\n",
           pre,
           expected->characters,
           actual->characters,
           compare_strings(expected, actual) ? "true" : "false");
    ++total;
    correct += (compare_strings(expected, actual)) ? 1 : 0;
}
