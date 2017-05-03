package jstructs;

/**
 * <p>
 * === List ===
 * </p><p>
 * Date : May 02, 2017
 * </p><p>
 * TODO Description here
 * </p>
 *
 * @author Michael van Dyk
 */
public interface List<E> extends Deque<E> {

    int size();
    E get(int index);
    boolean set(int index, E item);
    boolean add(int index, E item);
    E remove(int index);

    @Override
    default boolean addFirst(E item) {
        return (add(0, item));
    }

    @Override
    default E removeFirst() {
        return (remove(0));
    }

    @Override
    default boolean addLast(E item) {
        return (add(size(), item));
    }

    @Override
    default E removeLast() {
        return (remove(size() - 1));
    }
}
