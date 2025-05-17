package org.github.valtoni.concurrency.structuredscope;

import java.util.concurrent.StructuredTaskScope;

public class TaskScopeFailureExample {
    public static void main(String[] args) throws Exception {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var t1 = scope.fork(() -> { throw new RuntimeException("Erro"); });
            var t2 = scope.fork(() -> "Valor");

            scope.join();
            scope.throwIfFailed();
        }
    }
}