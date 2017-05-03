package kstructs.list

import kstructs.Deque

/**
 * <p>
 * === List ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface List<E> : Deque<E> {
    operator fun get(index: Int): E?
    operator fun set(index: Int, item: E): E?
    fun add(index: Int, item: E): Boolean
    fun remove(index: Int): E?

    override fun addFirst(item: E) = add(0, item)
    override fun removeFirst() = remove(0)
    override fun addLast(item: E) = add(size(), item)
    override fun removeLast() = remove(size() - 1)
}