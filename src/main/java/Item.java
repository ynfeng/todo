public class Item {
    private final String name;
    private Status status;

    private Item(String name) {
        this.name = name;
        status = Status.UnFinish;
    }

    public static Item newItem(String name) {
        return new Item(name);
    }

    public String name() {
        return name;
    }

    public void done() {
        status = Status.Done;
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
        UnFinish, Done;
    }
}
