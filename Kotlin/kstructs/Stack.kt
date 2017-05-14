package kstructs

/**
 * <p>
 * === Stack ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface Stack<E> : Collection<E> {

    /**
     * Looks that the first element on the stack. Does not remove that element.
     */
    fun peek(): E

    /**
     *  Retrieves the last item to be pushed to the stack and removes it.
     * @return the first item on the top of the stack
     */
    fun pop(): E

    /**
     *  Pushes the given [item] to the top of the stack.
     * @param [item] an element to add to the stack.
     */
    fun push(item: E)
}