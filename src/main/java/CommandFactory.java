public class CommandFactory {
    private CommandFactory() {

    }

    public static Command createCommand(String cmd) {
        switch (cmd) {
            case "add":
                return new AddItemCommand();
            case "list":
                return new ListUnFinishedItemsCommand();
            case "done":
                return new MarkItemDoneCommand();
            default:
                throw new IllegalArgumentException("unsupported command.");
        }
    }
}
