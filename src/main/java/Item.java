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

    enum Status {
        UnFinish, Done;
    }
}
