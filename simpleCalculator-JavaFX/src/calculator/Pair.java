package calculator;

/**
 * A generic immutable pair class.
 * @param <E> as the element pair type
 */
public class Pair<E> {
    private E first;
    private E second;

    public Pair(E first, E second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }
}
