package adventuregame.util;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Base class for the bindable event

public class BindableEvent {
    private Map<EventSignal, EventCallback> connectionList = new HashMap<>();
    private ExecutorService eventDispatchService = Executors.newCachedThreadPool();

    public EventSignal Event()
    {
        return new EventSignal()
        {
            EventConnection connection;
            private boolean isOnce = false;
            private boolean isWaiting = false;

            @Override
            public EventConnection Connect(EventCallback callback) {
                connectionList.put(this, callback);
                connection = new EventConnection() {
                    boolean isConnected = true;

                    @Override
                    public void Disconnect()
                    {
                        connectionList.remove(this);
                    }

                    @Override
                    public boolean isConnected()
                    {
                        return isConnected;
                    }
                };
                return  connection;
            }

            @Override
            public void Once(EventCallback callback) {
                connectionList.put(this, callback);
                connection = new EventConnection() {
                    boolean isConnected = true;

                    @Override
                    public void Disconnect()
                    {
                        connectionList.remove(this);
                    }

                    @Override
                    public boolean isConnected()
                    {
                        return isConnected;
                    }
                };
                isOnce = true;
            }

            public boolean getIsWaiting() {return isWaiting;}
            public boolean getIsOnce() {return isOnce;}
            public EventConnection getConnection() {return connection;}
        };
    }

    public void Fire(Object... args)
    {

        for(EventSignal signal  : connectionList.keySet())
        {
            EventCallback callback = connectionList.get(signal);
            eventDispatchService.execute(() -> {
                try {
                    callback.onEvent(args);
                    if(signal.getIsOnce())
                    {
                        signal.getConnection().Disconnect();
                    }
                } catch(Exception e)
                {
                    System.err.println("Event ernror encountered: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }

    public void shutdown()
    {
        eventDispatchService.shutdown();
    }
}
