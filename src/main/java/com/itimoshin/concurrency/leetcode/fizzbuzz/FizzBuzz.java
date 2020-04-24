package com.itimoshin.concurrency.leetcode.fizzbuzz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

/**
 * Write a program that outputs the string representation of numbers from 1 to n, however:
 * <p>
 * If the number is divisible by 3, output "fizz".
 * If the number is divisible by 5, output "buzz".
 * If the number is divisible by both 3 and 5, output "fizzbuzz".
 * For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
 * <p>
 * Suppose you are given the following code:
 * <p>
 * class FizzBuzz {
 * public FizzBuzz(int n) { ... }               // constructor
 * public void fizz(printFizz) { ... }          // only output "fizz"
 * public void buzz(printBuzz) { ... }          // only output "buzz"
 * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 * public void number(printNumber) { ... }      // only output the numbers
 * }
 * Implement a multithreaded version of FizzBuzz with four threads. The same instance of FizzBuzz will be passed to four different threads:
 * <p>
 * Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
 * Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
 * Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
 * Thread D will call number() which should only output the numbers.
 */

class FizzBuzz {
    public static void main(String[] args) {
        new FizzBuzz(25).start();
    }

    public void start() {
        Thread fizzThread = new Thread("Fizz thread") {
            @Override
            public void run() {
                try {
                    fizz(() -> System.out.println("Fizz"));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread buzzThread = new Thread("Buzz thread") {
            @Override
            public void run() {
                try {
                    buzz(() -> System.out.println("Buzz"));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread fizzBuzzThread = new Thread("FizzBuzz thread") {
            @Override
            public void run() {
                try {
                    fizzbuzz(() -> System.out.println("FizzBuzz"));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread numThread = new Thread("Num thread") {
            @Override
            public void run() {
                try {
                    number(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
                        }
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numThread.start();
    }

    private int n;
    private int currentNum = 0;
    private CyclicBarrier entranceBarrier = new CyclicBarrier(4, () -> {
        currentNum++;
    });


    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (currentNum < n) {
            try {
                entranceBarrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            if (currentNum % 3 == 0 && currentNum % 5 != 0) {
                printFizz.run();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (currentNum < n) {
            try {
                entranceBarrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            if (currentNum % 5 == 0 && currentNum % 3 != 0) {
                printBuzz.run();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (currentNum < n) {
            try {
                entranceBarrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            if (currentNum % 5 == 0 && currentNum % 3 == 0) {
                printFizzBuzz.run();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (currentNum < n) {
            try {
                entranceBarrier.await();
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            if (currentNum % 3 != 0 && currentNum % 5 != 0) {
                printNumber.accept(currentNum);
            }
        }
    }
}
