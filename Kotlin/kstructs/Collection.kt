package kstructs

/**
 * <p>
 * === Collection ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface Collection<E> {
    fun size(): Int
    fun isEmpty(): Boolean = size() == 0
}