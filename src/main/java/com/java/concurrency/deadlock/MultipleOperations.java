package com.java.concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MultipleOperations {
    public static void main(String[] args) {
        try {

            final Account a = new Account(1, 1000, new ReentrantLock());
            final Account b = new Account(2, 2000, new ReentrantLock());

            ExecutorService service = Executors.newFixedThreadPool(3);
            Random random = new Random();

            for(int i=0; i<10; i++){
                service.submit(new Transfer(String.format("Transfer-%d",i), a, b, random.nextInt(400)));
            }

            service.shutdown();
            service.awaitTermination(100, TimeUnit.SECONDS);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
