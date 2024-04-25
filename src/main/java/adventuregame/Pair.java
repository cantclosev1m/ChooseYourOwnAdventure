package adventuregame;

/**
 * A generic class for holding a pair of objects.
 * This class serves as a simple container for two related objects, which can be accessed publicly.
 * @param <T> the type of the first element in the pair
 * @param <U> the type of the second element in the pair
 */
public class Pair<T, U> {
    /**
     * The first element of the pair.
     */
    public T first;

    /**
     * The second element of the pair.
     */
    public U second;

    /**
     * Constructs a new pair with specified first and second elements.
     * @param first the first element of the new pair
     * @param second the second element of the new pair
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // Additional methods can be added below if necessary
}
