package jstructs;

/**
 * <p>
 * === Stack ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public interface Stack<E> {
    boolean push(E item);
    E pop();
}
