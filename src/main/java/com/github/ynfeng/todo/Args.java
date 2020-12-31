package com.github.ynfeng.todo;

public class Args {
    private final String[] args;

    private Args(String[] args) {
        this.args = args;
    }

    public static Args of(String... args) {
        return new Args(args);
    }

    public String cmd() {
        return args[0];
    }
}
