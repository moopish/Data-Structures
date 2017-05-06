package kstructs

/**
 * <p>
 * === Stack ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface Stack<E> : Collection<E> {
    fun peek(): E
    fun pop(): E
    fun push(item: E)
}