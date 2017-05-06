package kstructs

/**
 * <p>
 * === Queue ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface Queue<E> : Collection<E> {
    fun add(item: E)
    fun get(): E
    fun remove(): E
}