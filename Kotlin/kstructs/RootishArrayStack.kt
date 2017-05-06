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
    private val blocks: List<Array<E>> = ArrayStack()
    private var size: Int = 0

    private fun i2b(i: Int): Int = Math.ceil((-3.0 + Math.sqrt(9.0 + i * 8.0)) / 2.0).toInt()

    override fun add(index: Int, item: E) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(index: Int): E {
        val b = i2b(index)
        val j = index - b * (b + 1) / 2
        return (blocks[b][j])
    }

    override fun remove(index: Int): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(index: Int, item: E): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size(): Int = size
}