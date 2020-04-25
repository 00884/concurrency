package com.itimoshin.leetcode.concurrency.zero_even_odd;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Suppose you are given the following code:
 * <p>
 * class ZeroEvenOdd {
 * public ZeroEvenOdd(int n) { ... }      // constructor
 * public void zero(printNumber) { ... }  // only output 0's
 * public void even(printNumber) { ... }  // only output even numbers
 * public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 * <p>
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: "0102030405"
 */

class ZeroEvenOdd {
    private int n;
    private volatile int currentNum = 0;
    private final Semaphore zeroSemaphore = new Semaphore(1);
    private final Semaphore oddSemaphore = new Semaphore(0);
    private final Semaphore evenSemaphore = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        ZeroEvenOdd instance = new ZeroEvenOdd(10);
        Thread zeroThread = new Thread(() -> {
            try {
                instance.zero(new PrintIntConsumer());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        zeroThread.setName("Zero thread");
        Thread evenThread = new Thread(() -> {
            try {
                instance.even(new PrintIntConsumer());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        evenThread.setName("Even thread");
        Thread oddThread = new Thread(() -> {
            try {
                instance.odd(new PrintIntConsumer());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        oddThread.setName("Odd thread");
        zeroThread.start();
        evenThread.start();
        oddThread.start();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroSemaphore.acquire();
            printNumber.accept(0);
            currentNum++;
            if (currentNum % 2 == 0) {
                evenSemaphore.release();
            } else {
                oddSemaphore.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        if (n < 2) {
            return;
        }
        int iterationsCount = n / 2;
        for (int i = 0; i < iterationsCount; i++) {
            evenSemaphore.acquire();
            printNumber.accept(currentNum);
            zeroSemaphore.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int iterationsCount = (n % 2 == 0) ? n / 2 : n / 2 + 1;
        for (int i = 0; i < iterationsCount; i++) {
            oddSemaphore.acquire();
            printNumber.accept(currentNum);
            zeroSemaphore.release();
        }
    }

    static class PrintIntConsumer implements IntConsumer {
        @Override
        public void accept(int value) {
            System.out.println(Thread.currentThread().getName() + ": " + value);
        }
    }
}


