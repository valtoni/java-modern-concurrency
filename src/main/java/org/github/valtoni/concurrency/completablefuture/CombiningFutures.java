package org.github.valtoni.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CombiningFutures {
    public static void main(String[] args) {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 20);

        CompletableFuture<Integer> combined = f1.thenCombine(f2, Integer::sum);
        combined.thenAccept(result -> System.out.println("Soma: " + result));
    }
}