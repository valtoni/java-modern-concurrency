package org.github.valtoni.concurrency.structuredscope;

import java.util.concurrent.StructuredTaskScope;

public class TaskScopeSuccessExample {
    public static void main(String[] args) throws Exception {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            var t1 = scope.fork(() -> {
                Thread.sleep(200);
                return "Resultado A";
            });

            var t2 = scope.fork(() -> {
                Thread.sleep(100);
                return "Resultado B";
            });

            scope.join();
            System.out.println("Resultado escolhido: " + scope.result());
        }
    }
}