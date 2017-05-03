package jstructs;

/**
 * <p>
 * === Queue ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public interface Queue<E> {
    boolean add(E item);
    E remove();
}
