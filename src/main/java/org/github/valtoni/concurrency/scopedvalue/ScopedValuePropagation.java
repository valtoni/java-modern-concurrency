package org.github.valtoni.concurrency.scopedvalue;

import java.lang.ScopedValue;

public class ScopedValuePropagation {
    private static final ScopedValue<String> USER_ID = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(USER_ID, "user").run(() -> {
            Thread.startVirtualThread(() ->
                System.out.println("Scoped USER_ID: " + USER_ID.get())
            );
        });
    }
}