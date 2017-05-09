#ifndef ARRAY_QUEUE_HPP
#define ARRAY_QUEUE_HPP

#include <stdint.h>

template < typename T >
class ArrayQueue {
    public:
        ArrayQueue();
        ~ArrayQueue();

        void     add(T item);
        T        remove();
        uint32_t size();

    private:
        T*       _arr;
        uint32_t _arr_size;
        uint32_t _pos;
        uint32_t _size;

        void resize();
};

template < typename T >
ArrayQueue<T>::ArrayQueue()
{
    _arr_size = _size = 0;
    _arr = NULL;
}

template < typename T >
ArrayQueue<T>::~ArrayQueue()
{
    if (_arr != NULL)
        delete [] _arr;
}

template < typename T >
void ArrayQueue<T>::add(T item)
{
    if (_size + 1 > _arr_size) resize();
    _arr[(_pos + _size++) & (_arr_size - 1)] = item;
}

template < typename T >
T ArrayQueue<T>::remove()
{
    T ret = _arr[_pos++];
    if (_pos == _arr_size) _pos = 0;
    if (4 * --_size - 1 < _arr_size) resize();
    return (ret);
}

template < typename T >
uint32_t ArrayQueue<T>::size()
{
    return (_size);
}

template < typename T >
void ArrayQueue<T>::resize()
{
    T* new_arr = new T[(16 > _size * 2) ? 16 : _size * 2];
    if (_pos + _size <= _arr_size) {
        memcpy(new_arr, _arr + _pos, _size * sizeof(T));
    } else {
        uint32_t temp = _arr_size - _pos;
        memcpy(new_arr, _arr + _pos, temp * sizeof(T));
        memcpy(new_arr + temp, _arr, (_size - temp) * sizeof(T));
    }
    _arr = new_arr;
    _pos = 0;
}

#endif
