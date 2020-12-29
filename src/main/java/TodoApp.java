import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {
    private final Console console;
    public final List<Item> itemsList = new ArrayList<>();

    public TodoApp(PrintStream out) {
        console = new Console(out);
    }

    public void run(String... args) {
        String cmd = args[0];
        if ("add".equals(cmd)) {
            Item item = Item.newItem(args[1]);
            itemsList.add(item);
            printUnFinishedItems();
            console.println("Item %s added", item.name());
        } else if ("done".equals(cmd)) {
            int itemIdx = Integer.valueOf(args[1]);
            Item item = itemsList.get(itemIdx - 1);
            item.done();
            console.println("Item %d done", itemIdx);
        } else if ("list".equals(cmd)) {
            printUnFinishedItems();
        }
    }

    private void printUnFinishedItems() {
        int id = 1;
        for (int i = 0; i < itemsList.size(); i++) {
            Item item = itemsList.get(i);
            if (!item.isDone()) {
                console.println("%d. %s", id++, item.name());
            }
        }
    }
}
