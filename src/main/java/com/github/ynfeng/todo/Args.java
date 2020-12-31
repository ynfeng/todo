package com.github.ynfeng.todo;

import java.util.Arrays;

public class Args {
    private final String[] args;

    private Args(String[] args) {
        this.args = args;
    }

    public static Args of(String... args) {
        return new Args(args);
    }

    public String cmd() {
        if (args == null || args.length == 0 || args[0] == null) {
            throw new IllegalArgumentException("command not found.");
        }
        return args[0];
    }

    public String get(int index) {
        return args[index];
    }

    public boolean has(String name) {
        return Arrays.asList(args).contains(name);
    }
}
