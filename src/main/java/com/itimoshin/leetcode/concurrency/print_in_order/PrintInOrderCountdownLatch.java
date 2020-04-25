package com.itimoshin.leetcode.concurrency.print_in_order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Suppose we have a class:
 * <p>
 * public class Foo {
 * public void first() { print("first"); }
 * public void second() { print("second"); }
 * public void third() { print("third"); }
 * }
 * The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call
 * second(), and thread C will call third().
 * Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is
 * executed after second().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: "firstsecondthird"
 * Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(),
 * thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
 * Example 2:
 * <p>
 * Input: [1,3,2]
 * Output: "firstsecondthird"
 * Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second().
 * "firstsecondthird" is the correct output.
 * <p>
 * <p>
 * Note:
 * <p>
 * We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seems
 * to imply the ordering. The input format you see is mainly to ensure our tests' comprehensiveness.
 */

public class PrintInOrderCountdownLatch {

    private final Runnable r1 = () -> System.out.println("first");
    private final Runnable r2 = () -> System.out.println("second");
    private final Runnable r3 = () -> System.out.println("third");

    private final CountDownLatch cdl1 = new CountDownLatch(1);
    private final CountDownLatch cdl2 = new CountDownLatch(1);


    public PrintInOrderCountdownLatch() {

    }

    public static void main(String[] args) {
        System.out.println("Args: " + Arrays.toString(args));
        PrintInOrderCountdownLatch instance = new PrintInOrderCountdownLatch();
        Map<String, Thread> map = new HashMap<>();
        map.put("1", new Thread() {
            @Override
            public void run() {
                try {
                    instance.first();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        map.put("2", new Thread() {
            @Override
            public void run() {
                try {
                    instance.second();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        map.put("3", new Thread() {
            @Override
            public void run() {
                try {
                    instance.third();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        for (String arg : args) {
            map.get(arg).start();
        }
    }

    public void first() throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        r1.run();
        cdl1.countDown();
    }

    public void second() throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        cdl1.await();
        r2.run();
        cdl2.countDown();
    }

    public void third() throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        cdl2.await();
        r3.run();
    }
}
