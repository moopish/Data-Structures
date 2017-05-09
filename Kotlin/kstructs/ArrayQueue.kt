package kstructs

import java.util.*

/**
 * <p>
 * === ArrayQueue ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class ArrayQueue<E> : Queue<E> {
    @Suppress("UNCHECKED_CAST")
    override fun get(): E = array[pos] as E

    private var array: Array<Any?> = arrayOfNulls<Any?>(16)
    private var size: Int = 0
    private var pos: Int = 0

    override fun size() = size

    override fun add(item: E) {
        if (size + 1 > array.size) resize()
        array[(pos + size++) and (array.size - 1)] = item
    }

    override fun remove(): E =
        if (size == 0) throw NoSuchElementException()
        else {
            val ret = get()
            pos = (pos + 1) and (array.size - 1)
            if (4 * --size - 1 < array.size) resize()
            ret
        }


    private fun resize() {
        val new_arr: Array<Any?> = arrayOfNulls<Any?>(Math.max(16, size * 2)) // TODO 16 an issue?
        if (pos + size in pos..array.size) { // TODO < array.size
            System.arraycopy(array, pos, new_arr, 0, size)
        } else {
            val temp = array.size - pos
            System.arraycopy(array, pos, new_arr, 0, temp)
            System.arraycopy(array, 0, new_arr, temp, size - temp)
        }
        array = new_arr
        pos = 0
    }
}