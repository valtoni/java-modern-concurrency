package org.github.valtoni.concurrency.varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class ArrayVarHandleExample {
    public static void main(String[] args) {
        int[] array = new int[5];
        VarHandle handle = MethodHandles.arrayElementVarHandle(int[].class);
        handle.set(array, 2, 42);
        System.out.println("Valor em [2]: " + handle.get(array, 2));
    }
}