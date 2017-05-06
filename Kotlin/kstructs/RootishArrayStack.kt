package kstructs

import kstructs.list.List
import kstructs.list.array.ArrayStack

/**
 * <p>
 * === RootishArrayStack ===
 * </p><p>
 * Date : May 05, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class RootishArrayStack<E>: List<E> {

    private val blocks: List<Array<Any?>> = ArrayStack()
    private var size: Int = 0

    private fun i2b(i: Int): Int = Math.ceil((-3.0 + Math.sqrt(9.0 + i * 8.0)) / 2.0).toInt()

    override fun add(index: Int, item: E) {
        val r = blocks.size()
        if (r * (r + 1) / 2 < size + 1) grow()
        ++size
        for (j in (size - 1) downTo (index + 1))
            set(j, get(j - 1))
        set(index, item)
    }

    override fun get(index: Int): E {
        val b = i2b(index)
        val j = index - b * (b + 1) / 2
        @Suppress("UNCHECKED_CAST")
        return (blocks[b][j] as E)
    }

    private fun grow() {
        blocks.add(arrayOfNulls(blocks.size() + 1))
    }

    override fun remove(index: Int): E {
        val x = get(index)
        for (j in index until (size - 1))
            set(j, get(j + 1))
        --size
        val r = blocks.size()
        if ((r - 2) * (r - 1)/2 >= size) shrink()
        return (x)
    }

    override fun set(index: Int, item: E): E {
        val b = i2b(index)
        val j = index - b * (b + 1) / 2
        @Suppress("UNCHECKED_CAST")
        val y = blocks[b][j] as E
        blocks[b][j] = item
        return (y)
    }

    private fun shrink() {
        var r = blocks.size()
        while (r > 0 && (r - 2) * (r - 1)/2 >= size) {
            blocks.removeLast()
            --r
        }
    }

    override fun size(): Int = size
}