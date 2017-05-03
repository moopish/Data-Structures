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

    override fun get(index: Int): E? =
        if (index in 0..(size - 1))
            array[(pos + index) % array.size]
        else null

    override fun set(index: Int, item: E): E? =
        if (index in 0..(size - 1)){
            val ret = array[(pos + index) % array.size]
            array[(pos + index) % array.size] = item
            ret
        } else null

    override fun add(index: Int, item: E): Boolean =
            if (index in 0..size) {
                if (size + 1 > array.size) resize()
                if (index < size / 2) {
                    pos = if (pos == 0) array.size - 1 else pos - 1
                    for (k in 0..(index - 1))
                        array[(pos + k) % array.size] = array[(pos + k + 1) % array.size]
                } else {
                    for (k in size downTo (index + 1))
                        array[(pos + k) % array.size] = array[(pos + k - 1) % array.size]
                }
                array[(pos + index) % array.size] = item
                ++size
                true
            } else false

    override fun remove(index: Int): E? =
        if (index in 0..size) {
            val ret = array[(pos + index) % array.size]
            if (index < size / 2) {
                for (k in index downTo 1)
                    array[(pos + k) % array.size] = array[(pos + k - 1) % array.size]
                pos = (pos + 1) % array.size
            } else {
                for (k in index..(size-2))
                    array[(pos + k) % array.size] = array[(pos + k + 1) % array.size]
            }
            if (3 * --size < array.size) resize()
            ret
        } else null

    private fun resize() {
        val new_arr: Array<E?> = arrayOfNulls<Any?>(Math.max(1, size * 2)) as Array<E?>
        for (k in 0..(size-1))
            new_arr[k] = array[(pos + k) % array.size]
        array = new_arr
        pos = 0
    }
}