#include "ArrayStack.h"

#include <iostream>
int main(int argc, char** argv)
{
    ArrayStack<uint32_t> test;

    test.add(0, 123);
    test.add(0, 423);

    for (int i=0; i<test.size(); ++i) {
        std::cout << test.get(i) << std::endl;
    }

    return (0);
}
