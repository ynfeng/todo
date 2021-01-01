package com.github.ynfeng.todo;

public class Item {
    private final String name;
    private Status status;

    private Item(String name) {
        this.name = name;
        status = Status.UnFinished;
    }

    public static Item newItem(String name) {
        return new Item(name);
    }

    public String name() {
        return name;
    }

    public Item done() {
        status = Status.Done;
        return this;
    }

    public boolean isDone() {
        return status == Status.Done;
    }

    public String toString(int index) {
        if (isDone()) {
            return String.format("%d. [Done] %s", index, name);
        }
        return String.format("%d. %s", index, name);
    }

    enum Status {
        UnFinished, Done
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }

        Item item = (Item) o;

        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
