package kstructs

/**
 * <p>
 * === WrappedArrayView ===
 * </p><p>
 * Date : May 05, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class WrappedArrayView<E>(arr: Array<E?>, low: Int, high: Int, val start: Int) : ArrayView<E>(arr, low, high) {

    private fun adjustedIndex(index: Int) = (start + index) % arr.size

    override operator fun get(index: Int): E? = super.get(index)
    override operator fun set(index: Int, item: E?) = super.set(index, item)
}