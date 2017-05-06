package kstructs

import kstructs.list.List

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

    private var head: Node<E>? = null
    private var tail: Node<E>? = null
    private var size: Int = 0


    override fun get(index: Int): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(index: Int, item: E): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(index: Int, item: E) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(index: Int): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size(): Int = size

    private class Node<E>(val data: E, var next: Node<E>?)
}