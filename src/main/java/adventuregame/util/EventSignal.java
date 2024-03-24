package adventuregame.util;

public interface EventSignal {
    boolean isWaiting = false;
    boolean isOnce = false;

    EventConnection connection = null;

    EventConnection Connect(EventCallback callback);
    void Once(EventCallback callback);

    boolean getIsWaiting();
    boolean getIsOnce();

    EventConnection getConnection();
}
