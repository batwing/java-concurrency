package com.java.concurrency.deadlock;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Account {

    private int  id;//an account with lower id should be locked first

    @Getter
    private volatile Integer balance;

    @Getter
    private Lock lock;

    @Getter
    private volatile AtomicInteger failTransferCounter = new AtomicInteger(0);

    public Account(int id, Integer balance, Lock lock) {
        this.id = id;
        this.balance = balance;
        this.lock = lock;
    }

    public void withdraw(Integer amount){
        balance = balance - amount;
    }

    public void deposit(Integer amount){
        balance = balance + amount;
    }

    public void incFailedTransferCounter(){
        failTransferCounter.incrementAndGet();

    }

    @Override
    public String toString() {
        return String.format("Account{id=%d, balance=%d, failedTransferCounter=%d}", id, balance, failTransferCounter.get());
    }
}
