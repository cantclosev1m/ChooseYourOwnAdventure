package adventuregame.util;

import java.util.function.Consumer;

public interface Event<T> {

    void Connect(Consumer<T> listener) throws NullPointerException;
    void Disconnect(Consumer<T> listener) throws NullPointerException;

    void Fire(T t);

    default void Fire()
    {
        Fire(null);
    }
}
