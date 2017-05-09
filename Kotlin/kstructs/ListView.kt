package kstructs

/**
 * <p>
 * === ListView ===
 * </p><p>
 * Date : May 08, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class ListView<E>(
        val list:List<E>,
        val low: Int,
        val high: Int
): List<E> {
    override fun get(index: Int): E = list[low + index]
    override fun set(index: Int, item: E) = list.set(index, item)

    override fun add(index: Int, item: E) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(index: Int): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size() = high - low - 1

}