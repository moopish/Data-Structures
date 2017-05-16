package kstructs

/**
 * <p>
 * === Deque ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface Deque<E> : Queue<E>, Stack<E>  {

    /**
     *  Adds [item] to the front of the deque.
     * @param item the element to add to the collection.
     */
    fun addFirst(item: E)
    fun getFirst(): E
    fun removeFirst(): E
    fun addLast(item: E)
    fun getLast(): E
    fun removeLast(): E

    /**
     * @inherit
     */
    override fun add(item: E) = addLast(item)
    override fun get() = getFirst()
    override fun remove() = removeFirst()
    override fun peek() = getFirst()
    override fun pop() = removeFirst()
    override fun push(item: E) = addFirst(item)
}