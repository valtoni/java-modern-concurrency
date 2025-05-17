package org.github.valtoni.concurrency.virtualthreads;

public class MassiveVirtualThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        System.out.println("100.000 threads virtuais iniciadas!");
    }
}