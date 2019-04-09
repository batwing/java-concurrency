package com.slowmo.concurrency.deadlock;


import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/***
 * A classic example of deadlock in java
 *
 * jps - to get the list of run processes
 * jStack <process-id> - to diagnose a deadlock
 * jConsole <process-id> - to diagnose a deadlock
 * jMeter
 ******************/
public class Operations {


    public static void main(String[] args) {
        try {
            Thread.currentThread().setName("Thread-1");
            final Account a = new Account(1, 1000, new ReentrantLock());
            final Account b = new Account(2, 2000, new ReentrantLock());
            ExecutorService service = Executors.newFixedThreadPool(2);
            Future<Boolean> result1 = service.submit(new Transfer("Transfer-1", a, b, 500));
            Future<Boolean> result2 = service.submit(new Transfer("Transfer-2", b, a, 300));
            System.out.println("Result of transfer-1:" + result1.get());
            System.out.println("Result of transfer-2:" + result2.get());
            service.shutdown();
            service.awaitTermination(10, TimeUnit.SECONDS);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
