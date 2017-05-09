package kstructs

/**
 * <p>
 * === View ===
 * </p><p>
 * Date : May 05, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
abstract class CollectionView<E>(val low: Int, val high: Int) {
    abstract operator fun get(index: Int): E?
    abstract operator fun set(index: Int, item: E): E
}