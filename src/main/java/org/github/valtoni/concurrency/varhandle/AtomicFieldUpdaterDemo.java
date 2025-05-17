package org.github.valtoni.concurrency.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class AtomicFieldUpdaterDemo {
    private volatile int value = 0;
    private static final VarHandle VALUE_HANDLE;

    static {
        try {
            VALUE_HANDLE = MethodHandles.lookup()
                .findVarHandle(AtomicFieldUpdaterDemo.class, "value", int.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean increment() {
        return VALUE_HANDLE.compareAndSet(this, 0, 1);
    }

    public static void main(String[] args) {
        var demo = new AtomicFieldUpdaterDemo();
        System.out.println("Incrementado: " + demo.increment());
        System.out.println("Incrementado novamente: " + demo.increment());
    }
}