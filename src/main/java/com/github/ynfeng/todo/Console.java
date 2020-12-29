package com.github.ynfeng.todo;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.IntStream;

public class Console {
    private final PrintStream out;

    public Console(OutputStream out) {
        this.out = new PrintStream(out);
    }

    public void println(String patten, Object... args) {
        out.println(String.format(patten, args));
    }

    void printItems(List<Item> items) {
        IntStream.range(1, items.size() + 1)
            .forEach(index -> println(items.get(index - 1).toString(index)));
    }

    public void printSummary(List<Item> allItems) {
        int total = allItems.size();
        int numOfFinished = (int) allItems.stream().filter(Item::isDone).count();
        println("Total %d items, %d item done", total, numOfFinished);
    }
}
