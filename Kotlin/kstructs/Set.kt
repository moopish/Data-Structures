package kstructs

/**
 * <p>
 * === Set ===
 * </p><p>
 * Date : May 08, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface Set<E>: Collection<E> {
    fun add(item: E): Boolean
    fun find(item: E): E?
    fun remove(item: E): E?
}