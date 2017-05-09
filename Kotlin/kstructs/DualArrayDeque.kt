package kstructs

/**
 * <p>
 * === DualArrayDeque ===
 * </p><p>
 * Date : May 08, 2017
 * </p><p>
 * TODO Description here
 * </p>
 * @author Michael van Dyk
 */
class DualArrayDeque<E>: List<E> {

    private fun new_stack() = ArrayDeque<E>()

    private var front = new_stack()
    private var back = new_stack()

    override fun size() = front.size() + back.size()

    override fun get(index: Int): E =
            if (index < front.size())
                front[front.size() - index - 1]
            else back[index - front.size()]

    override fun set(index: Int, item: E): E =
            if (index < front.size())
                front.set(front.size() - index - 1, item)
            else back.set(index - front.size(), item)

    override fun add(index: Int, item: E) {
        if (index < front.size())
            front.add(front.size() - index, item)
        else back.add(index - front.size(), item)
        System.err.println(size())
        balance()
    }

    override fun remove(index: Int): E {
        System.err.println(size())
        val ret = if (index < front.size())
            front.remove(front.size() - index)
        else back.remove(index - front.size())
        balance()
        return (ret)
    }

    private fun balance() {
        val n = size()
        if (3 * front.size() < back.size()) {
            val s = n/2 - front.size()
            val l1 = new_stack()
            val l2 = new_stack()
            l1.addAll(back.subListView(0, s))
            l1.reverse()
            l1.addAll(front)
            l2.addAll(back.subListView(s, back.size()))
            front = l1
            back = l2
        } else if (3 * back.size() < front.size()){
            val s = front.size() - n/2
            val l1 = new_stack()
            val l2 = new_stack()
            l1.addAll(front.subListView(s, front.size()))
            l2.addAll(front.subListView(0, s))
            l2.reverse()
            l2.addAll(back)
            front = l1
            back = l2
        }
        System.err.println(size())
    }
}