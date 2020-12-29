import java.io.PrintStream;

public class TodoApp {
    private final Console console;
    public final ItemRepository itemRepository = new MemoryItemRepository();

    public TodoApp(PrintStream out) {
        console = new Console(out);
    }

    public void run(String... args) {
        String cmd = args[0];
        if ("add".equals(cmd)) {
            Item item = Item.newItem(args[1]);
            itemRepository.add(item);
            console.printItems(itemRepository.findUnFinishedItems());
            console.println("Item %s added", item.name());
        } else if ("done".equals(cmd)) {
            int itemIdx = Integer.valueOf(args[1]);
            Item item = itemRepository.getByIndex(itemIdx - 1);
            item.done();
            console.println("Item %d done", itemIdx);
        } else if ("list".equals(cmd)) {
            console.printItems(itemRepository.findUnFinishedItems());
        }
    }

}
