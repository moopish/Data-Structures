package kstructs

import java.util.*

/**
 * <p>
 * === SinglyLinkedList ===
 * </p><p>
 * Date : May 05, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class SinglyLinkedList<E> : Queue<E>, Stack<E> {

    private val dummy: Node<E?> = Node(null, null)
    private var head: Node<E?> = dummy
    private var tail: Node<E?> = dummy
    private var size: Int = 0

    override fun add(item: E) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun peek(): E = head.data ?: throw NoSuchElementException()

    override fun pop(): E {
        val x = head.data ?: throw NoSuchElementException()
        head = head.next
        if (size > 0) if (--size == 0) tail = dummy
        return (x)
    }

    override fun get(): E = head.data ?: throw NoSuchElementException()

    override fun push(item: E) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size(): Int = size

    private class Node<E>(val data: E, next: Node<E>?) {
        var next: Node<E> = next ?: this
    }
}