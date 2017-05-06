package kstructs.list.array

/**
 * <p>
 * === ArrayStack ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class ArrayStack<E> : kstructs.list.List<E> {

    private var array: Array<Any?> = kotlin.arrayOfNulls<Any?>(1)
    private var size: Int = 0

    override fun size(): Int = size

    override fun get(index: Int): E =
        if (index in 0..(size - 1))
            array[index] as E
        else
            throw IndexOutOfBoundsException()

    override fun set(index: Int, item: E): E =
        if (index in 0..(size - 1)) {
            val ret = array[index] as E
            array[index] = item
            ret
        } else
            throw IndexOutOfBoundsException()

    override fun add(index: Int, item: E) {
        if (index in 0..size) {
            if (size + 1 > array.size) resize()
            System.arraycopy(array, index, array, index + 1, size - index)
            array[index] = item;
            ++size
        } else
            throw IndexOutOfBoundsException()
    }

    override fun remove(index: Int): E =
        if (index in 0..(size - 1)) {
            val ret = array[index] as E
            System.arraycopy(array, index + 1, array, index, size - index - 1)
            if (array.size >= 3 * (--size)) resize()
            ret
        } else
            throw IndexOutOfBoundsException()

    private fun resize() {
        val new_arr = arrayOfNulls<Any?>(Math.max(size*2, 1))
        System.arraycopy(array, 0, new_arr, 0, size)
        array = new_arr
    }
}