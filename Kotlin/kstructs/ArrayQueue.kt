package kstructs

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
public class ArrayQueue<E> : Queue<E> {

    private var array: Array<E?> = arrayOfNulls<Any?>(0) as Array<E?>
    private var size: Int = 0
    private var pos: Int = 0

    override fun size() = size

    override fun add(item: E): Boolean {
        if (size + 1 > array.size) resize()
        array[(pos + size++) % array.size] = item
        return (true)
    }
    //override fun iterator(): Iterator<E> = ArrayQueueIterator(this)
    override fun remove(): E? =
        if (size != 0) {
            val ret = array[pos]
            pos = (pos + 1) % array.size
            if (array.size >= 3 * --size) resize()
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