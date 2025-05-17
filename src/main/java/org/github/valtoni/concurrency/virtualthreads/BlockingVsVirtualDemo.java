package org.github.valtoni.concurrency.virtualthreads;

public class BlockingVsVirtualDemo {
    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        Thread platform = new Thread(() -> sleep(500));
        platform.start();
        platform.join();

        long platformTime = System.nanoTime() - start;

        start = System.nanoTime();
        Thread virtual = Thread.startVirtualThread(() -> sleep(500));
        virtual.join();

        long virtualTime = System.nanoTime() - start;

        System.out.printf("Plataforma: %dms | Virtual: %dms%n",
            platformTime / 1_000_000, virtualTime / 1_000_000);
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}