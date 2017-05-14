package kstructs

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
    operator fun get(index: Int): E
    operator fun set(index: Int, item: E): E
    fun add(index: Int, item: E)
    fun remove(index: Int): E

    fun subListView(high: Int) = subListView(0, high)
    fun subListView(low: Int, high: Int): ListView<E> = ListView(this, low, high)

    //TODO does not work p sure
    fun reverse() {
        if (size() <= 1) return
        for (i in 0..(size()/2))
            set(size() - i - 1, set(i, get(size() - i - 1)))
    }

    // TODO no testing done
    fun addAll(other: List<E>) {
        for (i in 0 until other.size())
            add(other[i])
    }

    override fun addFirst(item: E) = add(0, item)
    override fun getFirst() = get(0)
    override fun removeFirst() = remove(0)
    override fun addLast(item: E) = add(size(), item)
    override fun getLast() = get(size() - 1)
    override fun removeLast() = remove(size() - 1)
}