package com.slowmo.concurrency.deadlock;

import com.slowmo.concurrency.deadlock.exception.InsufficientFundsException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class Transfer implements Callable<Boolean> {

    private final static int LOCK_WAITING_TIMEOUT = 3;//in seconds

    private String threadName;
    private Account accountFrom;
    private Account accountTo;
    private Integer amount;

    public Boolean call() throws Exception {
        Thread.currentThread().setName(threadName);
        return transfer(accountFrom, accountTo, amount);
    }

    private static Boolean transfer(Account acc1, Account acc2, int amount) throws InterruptedException, InsufficientFundsException {
        log("transfer() started...");

        if (acc1.getBalance() < amount) {
            throw new InsufficientFundsException(String.format("There is not enough money to transfer on account {%s}", acc1));
        }

        log(String.format("Try locking {%s}", acc1));

        if (acc1.getLock().tryLock(LOCK_WAITING_TIMEOUT,TimeUnit.SECONDS)) {
            log(String.format("Locked {%s}", acc1));
            log(String.format("Try locking {%s}", acc2));
            try {
                Integer lockWaitRandomTimeout = Math.abs(new Random().nextInt(LOCK_WAITING_TIMEOUT));
                log(String.format("lockWaitRandomTimeout - %d seconds", lockWaitRandomTimeout));

                if (acc2.getLock().tryLock(lockWaitRandomTimeout, TimeUnit.SECONDS)) {
                    log(String.format("Locked {%s}", acc2));
                    try {
                        acc1.withdraw(amount);
                        acc2.deposit(amount);
                        Integer timeout = Math.abs(new Random().nextInt(5));
                        log(String.format("Do transfer - %d seconds", timeout));
                        Thread.sleep(timeout * 1000);
                        log(String.format("%d was successfully transferred from %s to %s", amount, acc1, acc2));

                    }
                    finally {
                        acc2.getLock().unlock();
                        log(String.format("Unlocked {%s}", acc2));
                    }
                }
                else {
                    acc1.incFailedTransferCounter();
                    log(String.format("It was not possible to lock accounts %s & %s for transfer", acc1, acc2));
                    return false;
                }
            }
            finally {
                acc1.getLock().unlock();
                log(String.format("Unlocked {%s}", acc1));
            }
        }
        else {
            acc1.incFailedTransferCounter();
            log(String.format("It was not possible to lock accounts %s & %s for transfer", acc1, acc2));
            return false;
        }

        log(acc1.toString());
        log(acc2.toString());
        log("transfer() finished...");
        return true;
    }

    private static void log(String text){
        System.out.println(String.format("Thread-%s: %s", Thread.currentThread().getName(), text));
    }
}
