package kstructs

import kstructs.List

/**
 * <p>
 * === DoublyLinkedList ===
 * </p><p>
 * Date : May 03, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class DoublyLinkedList<E> : List<E> {

    private var size: Int = 0
    private var dummy: Node<E> = Node(null as E)

    override fun size() = size

    private fun getNode(index: Int): Node<E> {
        var ret: Node<E>
        if (index < size/2) {
            ret = dummy.next
            for (j in 0..(index-1))
                ++ret
        } else {
            ret = dummy
            for (j in size downTo (index+1))
                --ret
        }

        return (ret)
    }

    override fun get(index: Int): E =
            if (index in 0..(size-1))
                getNode(index).data
            else throw IndexOutOfBoundsException()


    override fun set(index: Int, item: E): E =
            if (index in 0..(size-1))
                getNode(index).set(item)
            else throw IndexOutOfBoundsException()

    override fun add(index: Int, item: E) {
        if (index in 0..size) {
            getNode(index).addBefore(item)
            ++size
        } else throw IndexOutOfBoundsException()
    }

    override fun remove(index: Int): E {
        if (index in 0..(size-1)) {
            val ret = getNode(index).remove().data
            --size
            return ret
        } else throw IndexOutOfBoundsException()
    }

    private class Node<E>(var data: E) {
        var next: Node<E> = this
        var prev: Node<E> = this

        constructor(data: E, next: Node<E>, prev: Node<E>) : this(data) {
            this.next = next
            this.prev = prev
        }

        fun set(data: E): E {
            var old = this.data
            this.data = data
            return (old)
        }

        fun addBefore(data: E) {
            val new_node = Node(data, this, this.prev)
            new_node.prev.next = new_node
            new_node.next.prev = new_node
        }

        /** remove node, make the next be next for prev and prev be prev for next**/
        fun remove(): Node<E> {
            prev.next = next
            next.prev = prev
            return (this)
        }

        /** Get next node **/
        operator fun inc(): Node<E> = next

        /** Get previous node **/
        operator fun dec(): Node<E> = prev

    }
}