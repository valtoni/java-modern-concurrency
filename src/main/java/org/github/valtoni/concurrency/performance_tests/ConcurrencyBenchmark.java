package org.github.valtoni.concurrency.performance_tests;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.concurrent.locks.ReadWriteLock;

public class ConcurrencyBenchmark {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final StampedLock stampedLock = new StampedLock();
    private static int counter = 0;

    public void testRWLock() {
        rwLock.writeLock().lock();
        try {
            counter++;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void testStampedLock() {
        long stamp = stampedLock.writeLock();
        try {
            counter++;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) {
        var bench = new ConcurrencyBenchmark();
        long start = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) bench.testRWLock();
        long rwTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) bench.testStampedLock();
        long stampedTime = System.nanoTime() - start;

        System.out.printf("Locks achieved %d | RWLock: %dms | StampedLock: %dms%n", counter, rwTime/1_000_000, stampedTime/1_000_000);
    }
}