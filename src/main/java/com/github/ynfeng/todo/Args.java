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
        if (!hasCommand()) {
            throw new TodoApplicationException("command not found.");
        }
        return args[0].trim();
    }

    private boolean hasCommand() {
        return !(args == null || args.length == 0 || args[0] == null);
    }

    public String getByIndexOrThrowException(int index, String message) {
        try {
            return args[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TodoApplicationException(message);
        }
    }

    public boolean has(String name) {
        return Arrays.asList(args).contains(name);
    }
}
