#include "ArrayStack.hpp"
#include "ArrayQueue.hpp"

#include <iostream>
int main(int argc, char** argv)
{
    ArrayStack<uint32_t> test;
    ArrayQueue<uint32_t> queue;

    queue.add(123);
    queue.add(423);

    for (int i=0; i<queue.size(); ++i) {
        std::cout << queue.remove() << std::endl;
    }

    return (0);
}
