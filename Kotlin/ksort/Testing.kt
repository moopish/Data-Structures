package ksort

import java.util.*

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

private interface SortingFunctionTest {
    operator fun invoke(array: Array<Int>, c: (a: Int, b: Int) -> Int)
    fun name(): String
}

private object JavaSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        Arrays.sort(array, c)
    }

    override fun name(): String = "Java Sort"
}

private object BubbleSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        bubbleSort(array, c)
    }

    override fun name() = "Bubble Sort"
}

private object CycleSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        cycleSort(array, c)
    }

    override fun name() = "Cycle Sort"
}

private object GnomeSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        gnomeSort(array, c)
    }

    override fun name() = "Gnome Sort"
}

private object InsertionSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        insertionSort(array, c)
    }

    override fun name() = "Insertion Sort"
}

private object MergeSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        mergeSort(array, c)
    }

    override fun name() = "Merge Sort"
}

private object OddEvenSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        oddEvenSort(array, c)
    }

    override fun name() = "Odd-Even Sort"
}

private object OptimizedBubbleSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        optimizedBubbleSort(array, c)
    }

    override fun name() = "Optimized Bubble Sort"
}

private object SelectionSort : SortingFunctionTest {
    override fun invoke(array: Array<Int>, c: (Int, Int) -> Int) {
        selectionSort(array, c)
    }

    override fun name() = "Selection Sort"
}

private fun sortRandomArrays(method: SortingFunctionTest, size: Int, rounds: Int) {
    var time: Long
    var total: Long = 0

    for (i in 0 until rounds) {
        val arr = Array(size, { (Math.random() * size).toInt() })
        time = System.nanoTime()
        method(arr, { a, b -> (b - a) })
        total += System.nanoTime() - time
    }

    System.out.print("    Random array test       : ")
    System.out.println(total / rounds)
}

private fun sortSemiSortedArrays(method: SortingFunctionTest, size: Int, rounds: Int) {
    var time: Long
    var total: Long = 0

    for (i in 0 until rounds) {
        val arr = Array(size, { i -> i })

        for (j in 1..5)
            swap(arr, (Math.random() * arr.size).toInt(), (Math.random() * arr.size).toInt())

        time = System.nanoTime()
        method(arr, { a, b -> (b - a) })
        total += System.nanoTime() - time
    }

    System.out.print("    Semi Sorted array test  : ")
    System.out.println(total / rounds)
}

private fun sortSortedArrays(method: SortingFunctionTest, size: Int, rounds: Int) {
    var time: Long
    var total: Long = 0

    for (i in 0 until rounds) {
        val arr = Array(size, { i -> i })
        time = System.nanoTime()
        method(arr, { a, b -> (b - a) })
        total += System.nanoTime() - time
    }

    System.out.print("    Sorted array test       : ")
    System.out.println(total / rounds)
}

private fun sortReversedArrays(method: SortingFunctionTest, size: Int, rounds: Int) {
    var time: Long
    var total: Long = 0

    for (i in 0 until rounds) {
        val arr = Array(size, { i -> size - 1 - i })
        time = System.nanoTime()
        method(arr, { a, b -> (b - a) })
        total += System.nanoTime() - time
    }

    System.out.print("    Reversed array test     : ")
    System.out.println(total / rounds)
}

private fun sortTest(tester: SortingFunctionTest, magnitude: Int, rounds: Int) {
    System.out.print("Testing: ")
    System.out.println(tester.name())
    System.out.println()

    for (i in 0..magnitude) {
        val size = Math.pow(10.0, i.toDouble()).toInt()
        System.out.print("   size: ")
        System.out.print(size)
        System.out.println(" elements")
        sortSortedArrays(tester, size, rounds)
        sortSemiSortedArrays(tester, size, rounds)
        sortRandomArrays(tester, size, rounds)
        sortReversedArrays(tester, size, rounds)
        System.out.println()
    }
}

fun main(args: Array<String>) {
    val arr: Array<SortingFunctionTest> = arrayOf(BubbleSort,
            CycleSort, GnomeSort, InsertionSort, MergeSort,
            OddEvenSort, OptimizedBubbleSort, SelectionSort,
            JavaSort)

    for (i in arr)
        sortTest(i, 4, 1)
}