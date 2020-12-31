package com.github.ynfeng.todo;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.IntStream;

public final class Console {
    private static PrintStream ps = System.out;

    @SuppressWarnings("unused")
    private Console() {
    }

    public static void out(PrintStream out) {
        ps = out;
    }

    public static void println(String patten, Object... args) {
        ps.printf(patten + "%n", args);
    }

    public static void printItems(List<Item> items) {
        IntStream.range(1, items.size() + 1)
            .forEach(index -> println(items.get(index - 1).toString(index)));
    }

    public static void printSummary(List<Item> allItems) {
        int total = allItems.size();
        int numOfFinished = (int) allItems.stream().filter(Item::isDone).count();
        println("Total %d items, %d item done", total, numOfFinished);
    }
}