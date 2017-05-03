package kstructs.list

/**
 * <p>
 * === ReadOnlyList ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class ReadOnlyList<E>(private val list: List<E>): List<E> {
    override fun size(): Int = list.size()

    override fun get(index: Int): E = list[index]

    override fun set(index: Int, item: E): E {
        throw UnsupportedOperationException()
    }

    override fun add(index: Int, item: E) {
        throw UnsupportedOperationException()
    }

    override fun remove(index: Int): E {
        throw UnsupportedOperationException()
    }
}