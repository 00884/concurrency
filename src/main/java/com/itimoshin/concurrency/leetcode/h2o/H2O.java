package com.itimoshin.concurrency.leetcode.h2o;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules. There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, and they must be able to immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.
 *
 * In other words:
 *
 * If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
 * If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
 * We don’t have to worry about matching the threads up explicitly; that is, the threads do not necessarily know which other threads they are paired up with. The key is just that threads pass the barrier in complete sets; thus, if we examine the sequence of threads that bond and divide them into groups of three, each group should contain one oxygen and two hydrogen threads.
 *
 * Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.
 *
 * Example 1:
 *
 * Input: "HOH"
 * Output: "HHO"
 * Explanation: "HOH" and "OHH" are also valid answers.
 * Example 2:
 *
 * Input: "OOHHHH"
 * Output: "HHOHHO"
 * Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.
 *
 * Constraints:
 *
 * Total length of input string will be 3n, where 1 ≤ n ≤ 20.
 * Total number of H will be 2n in the input string.
 * Total number of O will be n in the input string.
 */

class H2O {

    private final Semaphore oSemaphore = new Semaphore(2);
    private final Semaphore hSemaphore = new Semaphore(0);
    private AtomicInteger count = new AtomicInteger(0);

    private static String INPUT_1 = "HHHHHHHHHHHHHHHHOHHHHHHHHHHHHHHHHHHHHHOHHOHHHHOOHHHOOOOHHOOHOHHHHHOOHOHHHOOOOOOHHHHHHHHHHOHHOHHHHOOHHHOOOOHHOOHOHHHHHHHHHHHHHHHOHHOHHHHOOHHHOOOOHHOOHOHHHHHOOHOHHHOOOOOOHHHHHHHHHOOHOHHHOOOOOOHHHHHHHHHOHHHHOOHHHOOOOHHOOHOHHHHHOOHOHHHOOOOOOHHHHHHHHHHHHHOHHOHHHHOOHHHOOOOHHOOHOHHHHHOOHHHHHHHHHHHOHHOHHHHOOHHHOOOOHHOOHOHHHHHOOHOHHHOOOOOOHHHHHHHHHOHHHOOOOOOHHHHHHHHH";

    public static void main(String[] args) {
        new H2O().start(INPUT_1);
    }

    private void print(Character character) {
        System.out.print(character);
        if (count.incrementAndGet() % 3 == 0) {
            System.out.println(": " + count.get() / 3);
        }
    }

    public void start(String input) {
        List<Runnable> releaseHydrogen =
                input.chars().filter(ch -> ch == 'H').mapToObj((it) -> (Runnable) () -> print('H'))
                        .collect(Collectors.toList());
        List<Runnable> releaseOxygen =
                input.chars().filter(ch -> ch == 'O').mapToObj((it) -> (Runnable) () -> print('O'))
                        .collect(Collectors.toList());
        if (!(releaseHydrogen.size() == releaseOxygen.size() * 2 && releaseHydrogen.size() + releaseOxygen.size() == input.length())) {
            throw new IllegalArgumentException();
        }

        releaseHydrogen.forEach(x -> new Thread(() -> hydrogen(x)).start());
        releaseOxygen.forEach(x -> new Thread(() -> oxygen(x)).start());

    }

    @SneakyThrows
    public void hydrogen(Runnable releaseHydrogen) {
        hSemaphore.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        oSemaphore.release();
    }

    @SneakyThrows
    public void oxygen(Runnable releaseOxygen) {
        oSemaphore.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hSemaphore.release(2);
    }
}
