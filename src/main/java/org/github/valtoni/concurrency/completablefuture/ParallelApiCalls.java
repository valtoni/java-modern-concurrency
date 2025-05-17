package org.github.valtoni.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ParallelApiCalls {
    public static void main(String[] args) {
        CompletableFuture<String> primary = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "Resposta da API principal";
        });

        CompletableFuture<String> fallback = CompletableFuture.supplyAsync(() -> {
            sleep(100);
            return "Resposta do fallback";
        });

        primary.applyToEither(fallback, r -> "Usando: " + r)
               .thenAccept(System.out::println);
    }

    private static void sleep(long ms) {
        try { TimeUnit.MILLISECONDS.sleep(ms); } catch (InterruptedException ignored) {}
    }
}