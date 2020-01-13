package com.java.recruitment.gridgain;

import java.util.Queue;
import java.util.concurrent.*;

public class ThreadPool implements Executor {

    private final Queue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
    private volatile boolean isRunning;

    public ThreadPool(int threadsCount){
        for (int i=0; i< threadsCount; i++){
            new Thread(new Worker()).start();
        }
        isRunning = true;
    }

    public void execute(Runnable command){
        if (isRunning) {
            taskQueue.offer(command);
        }
    }

    public Runnable getTask(){
        return taskQueue.poll();
    }

    private final class Worker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                new Thread(getTask()).start();
            }
        }
    }
}
