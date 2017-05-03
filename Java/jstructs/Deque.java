package jstructs;

/**
 * <p>
 * === Deque ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public interface Deque<E> extends Queue<E>, Stack<E> {
    boolean addFirst(E item);
    E removeFirst();
    boolean addLast(E item);
    E removeLast();

    @Override
    default boolean add(E item) {
        return (addLast(item));
    }

    @Override
    default E remove() {
        return (removeFirst());
    }

    @Override
    default boolean push(E item) {
        return (addFirst(item));
    }

    @Override
    default E pop() {
        return (removeFirst());
    }
}
