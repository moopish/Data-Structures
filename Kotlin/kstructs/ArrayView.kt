package kstructs

/**
 * <p>
 * === ArrayView ===
 * </p><p>
 * Date : May 04, 2017
 * </p><p>
 * TODO Description here
 *
 * [low, high)
 * </p>
 * @author Michael van Dyk
 */
open class ArrayView<E>(
     val arr: Array<E?>,
     low: Int,
     high: Int
) : CollectionView<E>(low, high) {
    init {
        if (low >= high || low !in 0..arr.size || high !in 0..arr.size)
            throw RuntimeException()
    }

    constructor(arr: Array<E?>, high: Int) : this(arr, 0, high)

    protected fun inRange(index: Int) = index in 0..(high - low)

    override operator fun get(index: Int): E? =
            if (!inRange(index))
                throw RuntimeException()
            else
                arr[index]

    override operator fun set(index: Int, item: E?) =
            if (!inRange(index))
                throw RuntimeException()
            else
                arr[index] = item
}