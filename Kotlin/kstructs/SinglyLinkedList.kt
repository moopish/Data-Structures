package kstructs

import kstructs.List
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

class SinglyLinkedList<E> : List<E> {

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
            0 -> push(item)
            size -> add(item)
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

    override fun get(index: Int): E = getNode(index).data ?: throw NoSuchElementException()

    override fun getFirst(): E = head.data ?: throw NoSuchElementException()

    override fun getLast(): E = tail.data ?: throw NoSuchElementException()

    private fun getNode(index: Int): Node<E?> =
            when (index) {
                0 -> head
                size - 1 -> tail
                else -> {
                    var curr = head
                    for (h in 1 .. index) ++curr
                    curr
                }
            }

    override fun peek(): E = head.data ?: throw NoSuchElementException()

    override fun pop(): E {
        val x = get()
        head = head.next
        if (--size == 0) tail = dummy
        return (x)
    }

    fun print() {
        var curr = head
        for (i in 1..size) {
            print(" ${curr.data}")
            ++curr
        }
        println()
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
            !in 0 until size -> throw IndexOutOfBoundsException()
            0 -> removeFirst()
            size - 1 -> removeLast()
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

        if (size == 1) {
            head = dummy
            tail = dummy
        } else {
            val second_last = getNode(size - 2)
            second_last.next = dummy
            tail = second_last
        }
        --size
        return (ret)
    }

    override fun set(index: Int, item: E): E {
        val node = getNode(index)
        val ret = node.data
        node.data = item
        return (ret ?: throw NoSuchElementException())
    }

    override fun size(): Int = size

    private class Node<E>(var data: E, next: Node<E>?) {
        var next: Node<E> = next ?: this
        operator fun inc() = next
    }
}