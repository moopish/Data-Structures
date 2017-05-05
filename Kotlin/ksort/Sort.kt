package ksort

import kstructs.ArrayQueue
import java.util.*

/**
 * <p>
 * === Sort ===
 * </p><p>
 * Date : May 04, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */

@Suppress("NOTHING_TO_INLINE")
private inline fun <T> swap(arr: Array<T>, i: Int, j: Int) {
    val t = arr[i]
    arr[i] = arr[j]
    arr[j] = t
}

fun <T> idkSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    idkSort(arr, 0, arr.size - 1, c)
}

/** DOESNt work, why would it? **/
fun <T> idkSort(arr: Array<T>, low: Int, high: Int, c: (a: T, b: T) -> Int) {
    if (high - low <= 1) return

    idkSort(arr, low, low + (high - low)/2, c)
    idkSort(arr, low + (high - low)/2, high, c)

    var sorted: Boolean

    do {
        sorted = true
        var i: Int = low
        var j: Int = high
        while (i < j) {
            if (c(arr[i], arr[j]) <= 0) {
                ++i
            } else {
                swap(arr, i++, j--)
                sorted = false
            }
        }
    } while (!sorted)
}

fun <T> bubbleSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    do {
        var swapped = false
        for (i in 1 until arr.size) {
            if (c(arr[i - 1], arr[i]) > 0) {
                swap(arr, i - 1, i)
                swapped = true
            }
        }
    } while (swapped)
}

fun <T> oddEvenSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    var sorted: Boolean
    do {
        sorted = true
        for (i in 1 until arr.size - 1 step 2) {
            if (c(arr[i], arr[i + 1]) > 0) {
                swap(arr, i, i + 1)
                sorted = false
            }
        }

        for (i in 0 until arr.size - 1 step 2) {
            if (c(arr[i], arr[i + 1]) > 0) {
                swap(arr, i, i + 1)
                sorted = false
            }
        }
    } while (!sorted)
}

fun <T> optimizedBubbleSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    var n:Int  = arr.size
    var newn: Int
    do {
        newn = 0
        for (i in 1 until n) {
            if (c(arr[i - 1], arr[i]) > 0) {
                swap(arr, i - 1, i)
                newn = i
            }
        }
        n = newn
    } while (n != 0)
}

fun <T> cycleSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    for (cycleStart in 0 until arr.size) {
        var item = arr[cycleStart]
        var pos = cycleStart

        for (i in (cycleStart + 1)..(arr.size - 1))
            if (c(arr[i], item) < 0)
                ++pos

        if (pos == cycleStart) continue

        while (c(item, arr[pos]) == 0)
            ++pos

        var swap = arr[pos]
        arr[pos] = item
        item = swap

        while (pos != cycleStart) {
            pos = cycleStart
            for (i in (cycleStart + 1)..(arr.size - 1))
                if (c(arr[i], item) < 0)
                    ++pos

            while (c(item, arr[pos]) == 0)
                ++pos

            swap = arr[pos]
            arr[pos] = item
            item = swap
        }
    }
}

fun <T> gnomeSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    var pos = 0
    while (pos < arr.size) {
        if (pos == 0 || c(arr[pos], arr[pos - 1]) >= 0) {
            ++pos
        } else {
            swap(arr, pos, pos - 1)
            --pos
        }
    }
}

fun <T> insertionSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    for (j in 1 until arr.size) {
        var key = arr[j]
        var i = j - 1
        while (i >= 0 && c(arr[i], key) > 0)
            arr[i + 1] = arr[i--]
        arr[i + 1] = key
    }
}

fun <T> selectionSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    for (j in 0..(arr.size - 2)) {
        var min = j
        for (i in (j + 1)..(arr.size - 1))
            if (c(arr[i], arr[min]) < 0)
                min = i
        if (min != j)
            swap(arr, j, min)
    }
}

private inline fun <T> merge(arr0: Array<T>, arr1: Array<T>, arr: Array<T>, c: (a: T, b: T) -> Int) {
    var i0: Int = 0
    var i1: Int = 0

    for (i: Int in 0 until arr.size) {
        when {
            i0 == arr0.size -> arr[i] = arr1[i1++]
            i1 == arr1.size -> arr[i] = arr0[i0++]
            c(arr0[i0], arr1[i1]) < 0 -> arr[i] = arr0[i0++]
            else -> arr[i] = arr1[i1++]
        }
    }
}

private inline fun <T> mergeInPlace(low0: Int, high0: Int, low1: Int, high1: Int, arr: Array<T>, c: (a: T, b: T) -> Int) {
    var i0 = 0
    var i1 = 0
    var size0 = high0 - low0
    var size1 = high1 - low1
    for (i: Int in 0..(size0 + size1 - 1)) {
        when {
            i0 == size0 -> ++i1
            i1 == size1 -> ++i0
            c(arr[low0 + i0], arr[low1 + i1]) <= 0 -> ++i0
            else -> {
                val temp = arr[low0 + i0]
                arr[low0 + i0] = arr[low1 + i1]
                arr[low1 + i1] = temp
                ++i0
            }
        }
    }
}

fun <T> iterativeMergeSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    val queue = ArrayQueue<Array<T>>()
    for (i in 0 until arr.size) {
        val insert: Array<T> = arrayOfNulls<Any?>(1) as Array<T>
        insert[0] = arr[i]
        queue.add(insert)
    }
    while (queue.size() > 1) {

    }
}

fun <T> mergeSort(arr: Array<T>, c: (a: T, b: T) -> Int) {
    if (arr.size <= 1) return
    val arr0 = Arrays.copyOfRange(arr, 0, arr.size/2)
    val arr1 = Arrays.copyOfRange(arr, arr.size/2, arr.size)
    mergeSort(arr0, c)
    mergeSort(arr1, c)
    merge(arr0, arr1, arr, c)
}

fun <T> mergeSortInPlace(arr: Array<T>, c: (a: T, b: T) -> Int) {
    mergeSortInPlace(arr, 0, arr.size, c);
}

/** DOES NOT WORK JUST AN IDEA **/
private fun <T> mergeSortInPlace(arr: Array<T>, low: Int, high: Int, c: (a: T, b: T) -> Int) {
    val len: Int = high - low;
    val mod: Int = len and 1;
    if (len <= 1) {
        System.out.println(arr[low])
        return
    }
    mergeSortInPlace(arr, low, low + len/2, c);
    mergeSortInPlace(arr, low + len/2, low + len, c);
    mergeInPlace(low, low + len/2, low + len/2, len, arr, c);

    for (i in 0 until len) {
        System.out.print(arr[low + i])
        System.out.print(" ")
    }
    System.out.println()
}