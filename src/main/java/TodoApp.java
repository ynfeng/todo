import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {
    private final Console console;
    public final List<String> items = new ArrayList<>();

    public TodoApp(PrintStream out) {
        console = new Console(out);
    }

    public void run(String... args) {
        String cmd = args[0];
        if ("add".equals(cmd)) {
            String item = args[1];
            items.add(item);
            for (int i = 0; i < items.size(); i++) {
                console.println("%d. %s", i + 1, items.get(i));
            }
            console.println("Item %s added", item);
        } else if ("done".equals(cmd)) {
            int itemIdx = Integer.valueOf(args[1]);
            console.println("Item %d done", itemIdx);
        }
    }
}
