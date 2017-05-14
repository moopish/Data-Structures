package kstructs

/**
 *  Represents a collection of elements that are added and removed.
 * The queue decides which element is removed. A standard queue (or
 * at least the way it should be represented when implementing this
 * interface) is FIFO (first-in-first-out), meaning that the next
 * element removed should be the oldest element in the collection.
 *
 * @since May 02, 2017
 * @param E the type that this queue stores.
 * @see Collection
 * @author Michael van Dyk
 */
interface Queue<E>: Collection<E> {

    /**
     * Adds [item] to the end of the queue.
     * @param [item] the item to add
     */
    fun add(item: E)

    /**
     * Returns the first item in the queue. Does not remove the queue.
     * @return the first element in the queue
     */
    fun get(): E

    /**
     * Removes and returns the first queue in the queue.
     * @return the first element in the queue
     */
    fun remove(): E
}