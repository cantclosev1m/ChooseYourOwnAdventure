package adventuregame.util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * A thread-safe implementation of the Event interface for managing and triggering event listeners.
 *
 * @param <T> The type of the parameter that listeners accept.
 */
public class BindableEvent<T> implements Event<T>
{
    private final List<Consumer<T>> listeners = new CopyOnWriteArrayList<>();

    /**
     * Connects a new listener to this event.
     * @param listener The consumer function to be added as a listener.
     * @throws NullPointerException if the provided listener is null.
     */
    @Override
    public void Connect(Consumer<T> listener) throws NullPointerException {
        if(listener == null)
        {
            throw new NullPointerException("The listener is null!");
        }

        synchronized (listeners)
        {
            listeners.add(listener);
        }
    }

    /**
     * Disconnects a listener from this event.
     * @param listener The consumer function to be removed.
     * @throws NullPointerException if the listener is null.
     */
    @Override
    public void Disconnect(Consumer<T> listener) throws NullPointerException {
        synchronized (listeners)
        {
            listeners.remove(listener);
        }
    }

    /**
     * Triggers the event, calling all connected listeners with the given parameter.
     * @param t The parameter to pass to all listeners.
     */
    @Override
    public void Fire(T t) {
        listeners.forEach(listener -> listener.accept(t));
    }
}
