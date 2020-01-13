package com.java.memory.model;

public class Visibility {
    private static int sum = 0;
    private static boolean onSumChanged = false;

    public static void main(String args[]){
        Thread.currentThread().setName("MainThread-");
        new Thread(() -> {
                logCurrentState();
                try {
                    while (!onSumChanged) {
                        System.out.println(String.format("Thread-%s: will sleep for 1 sec", Thread.currentThread().getName()));
                        Thread.sleep(1000);
                    }
                    logCurrentState();
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }).start();
        sum = 100;
        onSumChanged = true;
        logCurrentState();
    }

    public static void logCurrentState(){
        System.out.println(String.format("Thread-%s: sum = %s, onSumChanged = %s", Thread.currentThread().getName(), sum, String.valueOf(onSumChanged)));
    }
}
