package kstructs

import kstructs.list.List

/**
 * <p>
 * === Testing ===
 * </p><p>
 * Date : May 04, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */

fun main(args: Array<String>) {

}

private class ListWrapper<E>(val list: java.util.List<E>): List<E>
{
    override fun get(index: Int): E = list.get(index)
    override fun set(index: Int, item: E): E = list.set(index, item)
    override fun add(index: Int, item: E) { list.add(index, item) }
    override fun remove(index: Int): E = list.remove(index)
    override fun size(): Int = list.size
}

private class DequeWrapper<E>(val deque: java.util.Deque<E>): Deque<E>
{
    override fun addFirst(item: E) { deque.addFirst(item) }
    override fun getFirst(): E = deque.first
    override fun removeFirst(): E = deque.removeFirst()
    override fun addLast(item: E) { deque.addLast(item) }
    override fun getLast(): E = deque.last
    override fun removeLast(): E = deque.removeLast()
    override fun size(): Int = deque.size
}

private class StackWrapper<E>(val stack: java.util.Deque<E>): Stack<E>
{
    override fun peek(): E = stack.peek()
    override fun pop(): E = stack.pop()
    override fun push(item: E) { stack.push(item) }
    override fun size(): Int = stack.size
}

private class QueueWrapper<E>(val queue: java.util.Queue<E>): Queue<E>
{
    override fun add(item: E) { queue.add(item) }
    override fun get(): E = queue.peek()
    override fun remove(): E = queue.remove()
    override fun size() = queue.size
}

private fun queue_tester(queue: Queue<Int>, times: Int, size: Int) {
    if (!queue.isEmpty()) throw RuntimeException("Queue should be empty for test")

    for (i in 0 until times) {
        //TODO add test
        (0 until size).forEach { j -> queue.add(j) }
        //TODO remove test
        (0 until size).forEach { j -> queue.remove() }
        //TODO random add remove test

    }
    //TODO correctness
}
