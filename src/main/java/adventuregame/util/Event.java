package adventuregame.util;

import java.util.function.Consumer;

/**
 * Defines a generic interface for event handling, allowing consumers to connect, disconnect, and trigger events.
 * @param <T> The type of the parameter that listeners accept during the event firing.
 */
public interface Event<T> 
{

    /**
     * Registers a listener to this event. The listener will be notified whenever the event is fired.
     * @param listener The consumer function to be added as a listener.
     * @throws NullPointerException if the provided listener is null.
     */
    void Connect(Consumer<T> listener) throws NullPointerException;

    /**
     * Unregisters a listener from this event, preventing it from receiving further notifications.
     * @param listener The consumer function to be removed.
     * @throws NullPointerException if the provided listener is null.
     */
    void Disconnect(Consumer<T> listener) throws NullPointerException;

    /**
     * Triggers the event, notifying all registered listeners with the provided data.
     * @param t The data to be passed to the listeners.
     */
    void Fire(T t);

    /**
     * Triggers the event without any data. This is a convenience method that effectively calls {@link #Fire(Object)} with null.
     */
    default void Fire() 
    {
        Fire(null);
    }
}
