package kstructs

/**
 * <p>
 * === ArrayDeque ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class ArrayDeque<E> : kstructs.list.List<E> {

    private var array: Array<E?> = arrayOfNulls<Any?>(0) as Array<E?>
    private var size: Int = 0
    private var pos: Int = 0

    override fun size(): Int = size

    override fun get(index: Int): E =
        if (index in 0..(size - 1))
            array[(pos + index) and (array.size - 1)] as E
        else
            throw IndexOutOfBoundsException()

    override fun set(index: Int, item: E): E =
        if (index in 0..(size - 1)){
            val ret = array[(pos + index) and (array.size - 1)] as E
            array[(pos + index) and (array.size - 1)] = item
            ret
        } else
            throw IndexOutOfBoundsException()

    override fun add(index: Int, item: E) {
        if (index in 0..size) {
            if (size + 1 > array.size) resize()
            if (index < size / 2) {

                pos = if (pos == 0) array.size - 1 else pos - 1
                for (k in 0..(index - 1))
                    array[(pos + k) and (array.size - 1)] = array[(pos + k + 1) and (array.size - 1)]
            } else {
                if (pos + size in pos..(array.size - 1)) {
                    val i = pos + index
                    System.arraycopy(array, i, array, i + 1, size - i)
                } else {
                    for (k in size downTo (index + 1))
                        array[(pos + k) and (array.size - 1)] = array[(pos + k - 1) and (array.size - 1)]
                    //val i = (pos + index) and (array.size - 1)
                    //System.arraycopy(array, i, array, i + 1, size - i)
                }
            }
            array[(pos + index) and (array.size - 1)] = item
            ++size
        } else
            throw IndexOutOfBoundsException()
    }

    override fun remove(index: Int): E =
        if (index in 0..(size-1)) {
            val ret = array[(pos + index) and (array.size - 1)] as E
            if (index < size / 2) {
                for (k in index downTo 1)
                    array[(pos + k) and (array.size - 1)] = array[(pos + k - 1) and (array.size - 1)]
                pos = (pos + 1) and (array.size - 1)
            } else {
                if (pos + size in pos..array.size) {
                    for (k in index..(size-2))
                        array[(pos + k) and (array.size - 1)] = array[(pos + k + 1) and (array.size - 1)]
                } else {
                    for (k in index..(size-2))
                        array[(pos + k) and (array.size - 1)] = array[(pos + k + 1) and (array.size - 1)]
                    //System.arraycopy(array, pos, new_arr, 0, array.size - pos)
                    //System.arraycopy(array, 0, new_arr, array.size - pos, size - (array.size - pos) )
                }
                //for (k in index..(size-2))
                //    array[(pos + k) and (array.size - 1)] = array[(pos + k + 1) and (array.size - 1)]
            }
            if (4 * --size - 1 < array.size) resize()
            ret
        } else
            throw IndexOutOfBoundsException("0 .. " + (size - 1) + " index = " + index)

    private fun resize() {
        val new_arr: Array<E?> = arrayOfNulls<Any?>(Math.max(1, size * 2)) as Array<E?>
        if (pos + size in pos..array.size) {
            System.arraycopy(array, pos, new_arr, 0, size)
        } else {
            System.arraycopy(array, pos, new_arr, 0, array.size - pos)
            System.arraycopy(array, 0, new_arr, array.size - pos, size - (array.size - pos) )
        }
        array = new_arr
        pos = 0
    }
}