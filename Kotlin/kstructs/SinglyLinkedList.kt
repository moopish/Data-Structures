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

class SinglyLinkedList<E> : kstructs.list.List<E> {

    private val dummy: Node<E?> = Node(null, null)
    private var head: Node<E?> = dummy
    private var tail: Node<E?> = dummy
    private var size: Int = 0

    override fun add(item: E) {
        val node = Node(item, dummy)
        if (size++ > 0) {
            tail.next = node
            tail = node
        } else {
            tail = node
            head = node
        }
    }

    override fun add(index: Int, item: E) {
        when (index) {
            !in 0..size -> throw IndexOutOfBoundsException()
            0 -> addFirst(item)
            size -> addLast(item)
            else -> {
                val before_node = getNode(index - 1)
                val new_node = Node(item, before_node.next)
                before_node.next = new_node
                ++size
            }
        }
    }

    override fun addFirst(item: E) = push(item)

    override fun addLast(item: E) = add(item)

    override fun get(): E = head.data ?: throw NoSuchElementException()

    override fun get(index: Int): E =
        when (index) {
            !in 0..size -> throw IndexOutOfBoundsException()
            0 -> get()
            size -> getLast()
            else -> getNode(index).data ?: throw NoSuchElementException()
        }

    override fun getFirst(): E = get()

    override fun getLast(): E = tail.data ?: throw NoSuchElementException()

    private fun getNode(index: Int): Node<E?> {
        var curr = head
        for (h in 1 until index) ++curr
        return (curr)
    }

    override fun peek(): E = get()

    override fun pop(): E {
        val x = get()
        head = head.next
        if (--size == 0) tail = dummy
        return (x)
    }

    override fun push(item: E) {
        head = Node(item, head)
        if (size++ == 0) {
            tail = head
        }
    }

    override fun remove(): E = pop()

    override fun remove(index: Int): E =
        when (index) {
            !in 0..size -> throw IndexOutOfBoundsException()
            0 -> removeFirst()
            size -> removeLast()
            else -> {
                val before_node = getNode(index - 1)
                val ret = before_node.next.data
                before_node.next = before_node.next.next
                --size
                ret ?: throw NoSuchElementException()
            }
        }

    override fun removeFirst(): E = pop()

    override fun removeLast(): E {
        val ret = getLast()

        if (--size == 0) {
            head = dummy
            tail = dummy
        } else {
            val second_last = getNode(size)
            second_last.next = dummy
            tail = second_last
        }
        return (ret)
    }

    override fun set(index: Int, item: E): E {
        val ret: E?
        when (index) {
            !in 0 until size -> throw IndexOutOfBoundsException()
            0 -> {
                ret = head.data
                head.data = item
            }
            size - 1 -> {
                ret = tail.data
            }
            else -> {
                val node = getNode(index)
                ret = node.data
                node.data = item
            }
        }
        return (ret ?: throw NoSuchElementException())
    }

    override fun size(): Int = size

    private class Node<E>(var data: E, next: Node<E>?) {
        var next: Node<E> = next ?: this
        operator fun inc() = next
    }
}