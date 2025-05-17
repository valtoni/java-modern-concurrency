package org.github.valtoni.concurrency.realworld_loom;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;

public class PriceAggregator {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            var p1 = scope.fork(() -> simulateApi("API-A", 300, true));
            var p2 = scope.fork(() -> simulateApi("API-B", 150, false));
            var p3 = scope.fork(() -> simulateApi("API-C", 100, false));

            scope.join();
            System.out.println("Melhor resultado: " + scope.result());
        }
    }

    private static String simulateApi(String name, long delay, boolean shouldFail) throws Exception {
        Thread.sleep(delay);
        if (shouldFail) throw new TimeoutException(name + " falhou");
        return name + " retornou preço";
    }
}