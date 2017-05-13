package kstructs

/**
 * <p>
 * === SortedSet ===
 * </p><p>
 * Date : May 08, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface SortedSet<E>: Set<E>, Comparable<E> {
    override fun find(item: E): E?
}