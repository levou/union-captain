package com.xishui.union.captain.common.pubsub;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

//单利，只实例化一次，如果
public class PSFactory<T> {
    private static Object lock = new Object();
    private final BlockingQueue<T> dataQueue;

    private final ExecutorService executorService;
    //有多少监听着
    private Set<Subscriber<T>> subscribers = new HashSet<>();

    private final static AtomicBoolean started = new AtomicBoolean(false);

    public PSFactory(BlockingQueue<T> dataQueue, ExecutorService executorService) {
        this.dataQueue = dataQueue;
        this.executorService = executorService;
    }

    private static final class PSFactoryInstance {
        private static PSFactory psFactory = null;

        public static void init(final PSConfig psConfig) {
            synchronized (lock) {
                if (null == psFactory) {
                    psFactory = new PSFactory<>(new LinkedBlockingQueue(psConfig.getQueueSize()), psConfig.getExecutorService());
                }
            }
        }

        public static <T> PSFactory<T> get() {
            synchronized (lock) {
                if (null == psFactory) {
                    PSConfig psConfig = new PSConfig();
                    psConfig.setExecutorService(Executors.newFixedThreadPool(2));
                    psConfig.setQueueSize(512);
                    PSFactoryInstance.init(psConfig);
                }
            }
            return psFactory;
        }
    }

    public void init() {
        //可重入
        synchronized (lock) {
            if (started.get()) {
                return;
            }
            started.set(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (started.get()) {
                            final T data = dataQueue.take();
                            executorService.submit(new TaskRunnable(data));
                        }
                    } catch (Exception e) {
                        //todo
                        started.set(false);
                    }
                }
            }).start();
        }
    }

    public boolean isStarted() {
        return started.get();
    }

    public static <T> PSFactory<T> factory() {
        return PSFactoryInstance.get();
    }

    public void pub(final T t) {
        this.dataQueue.add(t);
    }


    public void addSubscriber(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }


    protected class TaskRunnable implements Runnable {
        private T data;

        public TaskRunnable(T data) {
            this.data = data;
        }

        @Override
        public void run() {
            if (subscribers.size() <= 0) {
                //todo
            }
            subscribers.forEach(subscriber -> subscriber.subscriber(data));
        }
    }
}
