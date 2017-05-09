package kstructs

import kstructs.List

/**
 * <p>
 * === NoModulusArrayDeque ===
 * </p><p>
 * Date : May 06, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class NoModulusArrayDeque<E> : List<E> {
    private var array: Array<Any?> = arrayOfNulls<Any?>(16)
    private var size: Int = 0
    private var pos: Int = 8

    override fun size(): Int = size

    @Suppress("UNCHECKED_CAST")
    override fun get(index: Int): E =
            if (index in 0..(size - 1))
                array[pos + index] as E
            else
                throw IndexOutOfBoundsException()

    override fun set(index: Int, item: E): E {
        val ret = get(index)
        array[pos + index] = item
        return (ret)
    }

    override fun add(index: Int, item: E) {
        //println("add $index $item - $size")
        if (index in 0..size) {
            if (index < size / 2) {
                if (pos + index == 0) rebuild()
                System.arraycopy(array, pos, array, pos - 1, index)
                --pos
                array[pos + index] = item
            } else {
                if (pos + index == array.size) rebuild()
                System.arraycopy(array, pos + index, array, pos + index + 1, size - index)
                array[pos + index] = item
            }
            ++size

            //for (i in 0 until array.size)
            //    print(" ${array[i]} ")
            //println()

        } else
            throw IndexOutOfBoundsException()
    }

    override fun remove(index: Int): E {
        //println("rem $index - $size")

        val ret = set(index, null as E)

        if (index < size / 2) {
            System.arraycopy(array, pos, array, pos + 1, index)
            ++pos
        } else {
            System.arraycopy(array, pos + index, array, pos + index - 1, size - index + 1)
        }
        --size

        if (size * 6 < array.size) rebuild()

        //for (i in 0 until array.size)
            //print(" ${array[i]} ")
        //println()

        return (ret)
    }

    private fun rebuild() {
        val new_arr: Array<Any?> = arrayOfNulls<Any?>(Math.max(16, size * 3))
        val new_pos = new_arr.size/2 - size/2
        System.arraycopy(array, pos, new_arr, new_pos, size)
        pos = new_pos
        array = new_arr
    }

    private fun resize() {
        val new_arr: Array<E?> = arrayOfNulls<Any?>(Math.max(1, size * 2)) as Array<E?>
        if (pos + size in pos..array.size) {
            System.arraycopy(array, pos, new_arr, 0, size)
        } else {
            System.arraycopy(array, pos, new_arr, 0, array.size - pos)
            System.arraycopy(array, 0, new_arr, array.size - pos, size - (array.size - pos) )
        }
        //array = new_arr
        pos = 0
    }
}