#ifndef ARRAYSTACK_H
#define ARRAYSTACK_H

/**
    May 3, 2017
    TODO doesn't work
*/

#include <cstring>
#include <stdint.h>

template < typename T >
class ArrayStack
{
    public:
        ArrayStack();
        ~ArrayStack();

        void add(uint32_t index, T item);
        T get(uint32_t index);
        T remove(uint32_t index);
        T set(uint32_t index, T item);
        uint32_t size();

    private:
        T*       arr;
        uint32_t _arr_size;
        uint32_t _size;

        void resize();
};

template < typename T >
ArrayStack<T>::ArrayStack()
{
    _arr_size = _size = 0;
    arr = 0;
}

template < typename T >
ArrayStack<T>::~ArrayStack()
{
    if (arr != 0)
        delete [] arr;
}

template < typename T >
void ArrayStack<T>::add(uint32_t index, T item)
{
    if (_size + 1 > _arr_size) resize();
    std::memmove(arr + index, arr + index + 1, (_size - index) * sizeof(T));
    arr[index] = item;
    ++_size;
}

template < typename T >
T ArrayStack<T>::get(uint32_t index)
{
    return (arr[index]);
}

template < typename T >
T ArrayStack<T>::remove(uint32_t index)
{
    T ret = arr[index];
    std::memmove(arr + index + 1, arr + index, (_size - index) * sizeof(T));
    if (_arr_size >= 3 * --_size) resize();
    return (ret);
}

template < typename T >
T ArrayStack<T>::set(uint32_t index, T item)
{
    T ret = arr[index];
    arr[index] = item;
    return (ret);
}

template < typename T >
uint32_t ArrayStack<T>::size()
{
    return (_size);
}

template < typename T >
void ArrayStack<T>::resize()
{
    _arr_size = (1 > _size*2 ? 1 : _size*2);
    T* new_arr = new T[_arr_size];
    memcpy(new_arr, arr, _size * sizeof(T));
    if (arr != 0)
        delete [] arr;
    arr = new_arr;
}

#endif // ARRAYSTACK_H
