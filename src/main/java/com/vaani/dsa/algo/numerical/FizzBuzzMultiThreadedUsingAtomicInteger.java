package com.vaani.dsa.algo.numerical;

import java.util.ConcurrentModificationException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class FizzBuzzMultiThreadedUsingAtomicInteger {
    private final AtomicInteger counter;

    private final int n;

    private void updateToNext(int count) {
        if (!counter.compareAndSet(count, count + 1)) {
            throw new ConcurrentModificationException();
        }
    }

    public FizzBuzzMultiThreadedUsingAtomicInteger(int n) {
        this.n = n;
        counter = new AtomicInteger(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        int count;
        while ((count = counter.get()) <= n) {
            if (count % 3 == 0 && count % 5 != 0) {
                printFizz.run();
                updateToNext(count);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        int count;
        while ((count = counter.get()) <= n) {
            if (count % 3 != 0 && count % 5 == 0) {
                printBuzz.run();
                updateToNext(count);
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        int count;
        while ((count = counter.get()) <= n) {
            if (count % 15 == 0) {
                printFizzBuzz.run();
                updateToNext(count);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        int count;
        while ((count = counter.get()) <= n) {
            if (count % 3 != 0 && count % 5 != 0) {
                printNumber.accept(count);
                updateToNext(count);
            }
        }
    }
}
