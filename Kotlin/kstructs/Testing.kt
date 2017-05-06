package kstructs

import kstructs.list.List
import kstructs.list.array.ArrayStack

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
    tester(ArrayStack<Int>(), 100, 10000)
    tester(StackWrapper<Int>(java.util.LinkedList<Int>()), 100, 10000)
}

private class ListWrapper<E>(val list: java.util.ArrayList<E>): List<E>
{
    override fun get(index: Int): E = list[index]
    override fun set(index: Int, item: E): E = list.set(index, item)
    override fun add(index: Int, item: E) { list.add(index, item) }
    override fun remove(index: Int): E = list.removeAt(index)
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

private fun tester(c: Collection<Int>, times: Int, size: Int) {
    print(c.javaClass.name)
    println(" testing")
    when (c) {
        is List -> list_tester(c, times, size)
        is Deque -> deque_tester(c, times, size)
        is Stack -> stack_tester(c, times, size)
        is Queue -> queue_tester(c, times, size)
        else -> throw RuntimeException()
    }
}

private fun list_tester(list: List<Int>, times: Int, size: Int) {
    if (!list.isEmpty()) throw RuntimeException("Stack should be empty for test")

    deque_tester(list, times, size)

    println("\nList Tester: $size elements, $times iterations\n")

    var total: Long
    var total_1: Long = 0
    var total_2: Long = 0
    var total_3: Long = 0
    var total_4: Long = 0
    var time_sub: Long
    var time: Long = System.nanoTime()

/*    operator fun get(index: Int): E
    operator fun set(index: Int, item: E): E
    fun add(index: Int, item: E)
    fun remove(index: Int): E*/

    for (i in 0 until times) {
        time_sub = System.nanoTime()
        (0 until size).forEach { list.add((Math.random() * list.size()).toInt(), it) }
        total_1 += System.nanoTime() - time_sub

        time_sub = System.nanoTime()
        (0 until size).forEach { list[(Math.random() * list.size()).toInt()] }
        total_2 += System.nanoTime() - time_sub

        time_sub = System.nanoTime()
        (0 until size).forEach { list[(Math.random() * list.size()).toInt()] = it }
        total_3 += System.nanoTime() - time_sub

        time_sub = System.nanoTime()
        (0 until size).forEach { list.remove((Math.random() * list.size()).toInt()) }
        total_4 += System.nanoTime() - time_sub
    }
    total = System.nanoTime() - time
    println("overall $size elements                 : ${total / times}")
    println("average add(int) of $size elements     : ${total_1 / times}")
    println("average get of $size elements          : ${total_2 / times}")
    println("average set of $size elements          : ${total_3 / times}")
    println("average remove(int) of $size elements  : ${total_4 / times}")

    val compare: java.util.ArrayList<Int> = java.util.ArrayList<Int>()

    print("correctness test... ")

    for (i in 0 until times) {
        (0 until size).forEach {
            list.add(it)
            compare.add(it)
        }
        for (j in size until (size * 2)) {
            val index = (Math.random() * compare.size).toInt()
            when ((Math.random() * 4).toInt()) {
                0 -> {
                    list.add(index, j)
                    compare.add(index, j)
                }
                1 -> {
                    if (list[index] != compare[index]) {
                        println("FAILED")
                        return
                    }
                }
                2 -> {
                    list[index] = j
                    compare[index] = j
                }
                3 -> {
                    if (list.remove(index) != compare.removeAt(index)) {
                        println("FAILED")
                        return
                    }
                }
            }
        }
        while (!list.isEmpty()) {
            if (list.remove(0) != compare.removeAt(0)) {
                println("FAILED")
                return
            }
        }
    }
    println("PASSED")
}

private fun deque_tester(deque: Deque<Int>, times: Int, size: Int) {
    if (!deque.isEmpty()) throw RuntimeException("Stack should be empty for test")

    queue_tester(deque, times, size)
    stack_tester(deque, times, size)

    println("\nDeque Tester: $size elements, $times iterations\n")

    var total: Long
    var total_1: Long = 0
    var total_2: Long = 0
    var total_3: Long = 0
    var total_4: Long = 0
    var time_sub: Long
    var time: Long = System.nanoTime()

    for (i in 0 until times) {
        time_sub = System.nanoTime()
        (0 until size).forEach { j -> deque.addFirst(j) }
        total_1 += System.nanoTime() - time_sub

        time_sub = System.nanoTime()
        (0 until size).forEach { j -> deque.addLast(j) }
        total_2 += System.nanoTime() - time_sub

        time_sub = System.nanoTime()
        (0 until size).forEach { j -> deque.removeFirst() }
        total_3 += System.nanoTime() - time_sub

        time_sub = System.nanoTime()
        (0 until size).forEach { j -> deque.removeLast() }
        total_4 += System.nanoTime() - time_sub
    }
    total = System.nanoTime() - time
    println("overall add/remove $size elements     : ${total / times}")
    println("average addFirst of $size elements    : ${total_1 / times}")
    println("average addLast of $size elements     : ${total_2 / times}")
    println("average removeFirst of $size elements : ${total_3 / times}")
    println("average removeLast of $size elements  : ${total_4 / times}")

    time = System.nanoTime()
    for (i in 0 until times) {
        (0 until size).forEach { deque.add(it) }
        for (j in 0 until size) {
            when ((Math.random() * 4).toInt()) {
                0 -> deque.addFirst(j)
                1 -> deque.addLast(j)
                2 -> deque.removeFirst()
                3 -> deque.removeLast()
            }
        }
        while (!deque.isEmpty()) deque.remove()
    }
    total = System.nanoTime() - time
    println("random actions on $size elements      : ${total / times}")

    val compare: java.util.Deque<Int> = java.util.LinkedList()

    print("correctness test... ")

    for (i in 0 until times) {
        for (j in 0 until size) {
            compare.addFirst(j)
            deque.addFirst(j)
        }
        for (j in size until (size * 2)) {
            when ((Math.random() * 4).toInt()) {
                0 -> {
                    deque.addFirst(j)
                    compare.addFirst(j)
                    if (deque.size() != compare.size) {
                        println("FAILED")
                        return
                    }
                }
                1 -> {
                    deque.addLast(j)
                    compare.addLast(j)
                    if (deque.size() != compare.size) {
                        println("FAILED")
                        return
                    }
                }
                2 -> {
                    if (deque.removeFirst() != compare.removeFirst()) {
                        println("FAILED")
                        return
                    }
                }
                3 -> {
                    if (deque.removeLast() != compare.removeLast()) {
                        println("FAILED")
                        return
                    }
                }
            }
        }
        while (!deque.isEmpty()) {
            if (deque.removeLast() != compare.removeLast()) {
                println("FAILED")
                return
            }
        }
    }
    println("PASSED")
}

private fun stack_tester(stack: Stack<Int>, times: Int, size: Int) {
    if (!stack.isEmpty()) throw RuntimeException("Stack should be empty for test")

    println("\nStack Tester: $size elements, $times iterations\n")

    var total: Long
    var total_1: Long = 0
    var total_2: Long = 0
    var time_sub: Long
    var time: Long = System.nanoTime()

    for (i in 0 until times) {
        time_sub = System.nanoTime()
        (0 until size).forEach { j -> stack.push(j) }
        total_1 += System.nanoTime() - time_sub

        time_sub = System.nanoTime()
        (0 until size).forEach { j -> stack.pop() }
        total_2 += System.nanoTime() - time_sub
    }
    total = System.nanoTime() - time

    println("push then pop $size elements   : ${total / times}")
    println("average push of $size elements : ${total_1 / times}")
    println("average pop of $size elements  : ${total_2 / times}")

    time = System.nanoTime()
    for (i in 0 until times) {
        for (j in 0 until size) {
            if ((Math.random() * 3).toInt() == 1 && stack.size() > 0) {
                stack.pop()
            } else {
                stack.push(j)
            }
        }
        while (!stack.isEmpty()) stack.pop()
    }
    total = System.nanoTime() - time

    println("2:1 push:pop of $size elements : ${total / times}")

    time = System.nanoTime()
    for (i in 0 until times) {
        (0 until size).forEach { j -> stack.push(j) }
        for (j in 0 until size) {
            if ((Math.random() * 3).toInt() == 1) {
                stack.push(j)
            } else {
                stack.pop()
            }
        }
        while (!stack.isEmpty()) stack.pop()
    }
    total = System.nanoTime() - time

    println("1:2 push:pop of $size elements : ${total / times}")

    time = System.nanoTime()
    for (i in 0 until times) {
        (0 until size).forEach { j -> stack.push(j) }
        for (j in 0 until size) {
            if ((Math.random() * 2).toInt() == 1) {
                stack.push(j)
            } else {
                stack.pop()
            }
        }
        while (!stack.isEmpty()) stack.pop()
    }
    total = System.nanoTime() - time

    println("1:1 push:pop of $size elements : ${total / times}")

    val compare: java.util.Deque<Int> = java.util.LinkedList()

    print("correctness test... ")
    for (i in 0 until size) {
        stack.push(i)
        compare.push(i)
    }
    for (i in 0 until size) {
        if (stack.pop() != compare.pop()) {
            println("FAILED")
            return
        }
    }
    for (i in 0 until times) {
        for (j in 0 until size) {
            stack.push(j)
            compare.push(j)
        }
        for (j in size until (size * 2)) {
            if ((Math.random() * 2).toInt() == 1) {
                stack.push(j)
                compare.push(j)
            } else {
                if (stack.pop() != compare.pop()) {
                    println("FAILED")
                    return
                }
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != compare.pop()) {
                println("FAILED")
                return
            }
        }
    }
    println("PASSED")
}

private fun queue_tester(queue: Queue<Int>, times: Int, size: Int) {
    if (!queue.isEmpty()) throw RuntimeException("Queue should be empty for test")

    println("\nQueue Tester: $size elements, $times iterations\n")

    var total: Long
    var total_1: Long = 0
    var total_2: Long = 0
    var time_sub: Long
    var time: Long = System.nanoTime()

    for (i in 0 until times) {
        time_sub = System.nanoTime()
        (0 until size).forEach { j -> queue.add(j) }
        total_1 += System.nanoTime() - time_sub

        time_sub = System.nanoTime()
        (0 until size).forEach { j -> queue.remove() }
        total_2 += System.nanoTime() - time_sub
    }
    total = System.nanoTime() - time

    println("add then remove $size elements   : ${total / times}")
    println("average add of $size elements    : ${total_1 / times}")
    println("average remove of $size elements : ${total_2 / times}")

    time = System.nanoTime()
    for (i in 0 until times) {
        for (j in 0 until size) {
            if ((Math.random() * 3).toInt() == 1 && queue.size() > 0) {
                queue.remove()
            } else {
                queue.add(j)
            }
        }
        while (!queue.isEmpty()) queue.remove()
    }
    total = System.nanoTime() - time

    println("2:1 add:rem of $size elements    : ${total / times}")

    time = System.nanoTime()
    for (i in 0 until times) {
        (0 until size).forEach { j -> queue.add(j) }
        for (j in 0 until size) {
            if ((Math.random() * 3).toInt() == 1) {
                queue.add(j)
            } else {
                queue.remove()
            }
        }
        while (!queue.isEmpty()) queue.remove()
    }
    total = System.nanoTime() - time

    println("1:2 add:rem of $size elements    : ${total / times}")

    time = System.nanoTime()
    for (i in 0 until times) {
        (0 until size).forEach { j -> queue.add(j) }
        for (j in 0 until size) {
            if ((Math.random() * 2).toInt() == 1) {
                queue.add(j)
            } else {
                queue.remove()
            }
        }
        while (!queue.isEmpty()) queue.remove()
    }
    total = System.nanoTime() - time

    println("1:1 add:rem of $size elements    : ${total / times}")

    val compare: java.util.Queue<Int> = java.util.LinkedList()

    print("correctness test... ")
    for (i in 0 until size) {
        queue.add(i)
        compare.add(i)
    }
    for (i in 0 until size) {
        if (queue.remove() != compare.remove()) {
            println("FAILED")
            return
        }
    }
    for (i in 0 until times) {
        for (j in 0 until size) {
            queue.add(j)
            compare.add(j)
        }
        for (j in size until (size * 2)) {
            if ((Math.random() * 2).toInt() == 1) {
                queue.add(j)
                compare.add(j)
            } else {
                if (queue.remove() != compare.remove()) {
                    println("FAILED")
                    return
                }
            }
        }
        while (!queue.isEmpty()) {
            if (queue.remove() != compare.remove()) {
                println("FAILED")
                return
            }
        }
    }
    println("PASSED")
}
