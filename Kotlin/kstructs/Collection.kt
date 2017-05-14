package kstructs

/**
 * <p>
 * === Collection ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
interface Collection<E> {

    /**
     * @return the number of elements stored in the collection
     */
    fun size(): Int

    /**
     *  This returns if the collection is empty or not. Unless anything special needs to
     * be done in an implementing class this will not require to be overridden as it uses
     * the size method to determine this.
     *
     * @return if the collection is empty
     */
    fun isEmpty(): Boolean = size() == 0

}