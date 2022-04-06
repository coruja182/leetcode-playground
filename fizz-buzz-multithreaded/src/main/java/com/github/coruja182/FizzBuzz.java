package com.github.coruja182;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private final int n;

    // counter starts at 1
    // using AtomicInteger as this class is being used by multiple threads concurrently
    private final AtomicInteger counter;

    public FizzBuzz(int n) {
        this.n = n;
        this.counter = new AtomicInteger(1);
    }

    // "Fizz" if i is divisible by 3 and not 5,
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        int i;
        while ((i = counter.get()) <= n) {
            if (i % 3 == 0 && i % 5 != 0) {
                printFizz.run(); // outputs "fizz".
                counter.getAndIncrement();
                notifyAll(); // bounces other threads
            } else {
                wait();
            }
        }
    }

    // "Buzz" if i is divisible by 5 and not 3
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        int i;
        while ((i = counter.get()) <= n) {
            if (i % 5 == 0 && i % 3 != 0) {
                printBuzz.run(); // outputs "buzz".
                counter.getAndIncrement();
                notifyAll(); // bounces other threads
            } else {
                wait();
            }
        }
    }

    // "FizzBuzz" if i is divisible by 3 and 5, (then 15!)
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        int i;
        while ((i = counter.get()) <= n) {
            if (i % 15 == 0) {
                printFizzBuzz.run(); // outputs "fizzbuzz".
                counter.getAndIncrement();
                notifyAll(); // bounces other threads
            } else {
                wait();
            }
        }
    }

    // // outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        int i;
        while ((i = counter.get()) <= n) {
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i); // outputs "x", where x is an integer.
                counter.getAndIncrement();
                notifyAll(); // bounces other threads
            } else {
                wait();
            }
        }
    }
}
