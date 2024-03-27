package adventuregame.util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class BindableEvent<T> implements Event<T>
{
    private final List<Consumer<T>> listeners = new CopyOnWriteArrayList<>();

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

    @Override
    public void Disconnect(Consumer<T> listener) throws NullPointerException {
        synchronized (listeners)
        {
            listeners.remove(listener);
        }
    }

    @Override
    public void Fire(T t) {
        listeners.forEach(listener -> listener.accept(t));
    }
}
